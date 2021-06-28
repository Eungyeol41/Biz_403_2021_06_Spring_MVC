package com.callor.score.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.dao.ext.StudentDao;
import com.callor.score.dao.ext.SubjectDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreInputVO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.model.SubjectVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("studentServiceV1")
public class StudentServiceImplV1 implements StudentService{

	protected final StudentDao stDao;
	protected final ScoreDao scDao;
	protected final SubjectDao sbDao;
	
	@Override
	public List<StudentVO> selectAll() {
		// TODO Auto-generated method stub
		
		List<StudentVO> stList = stDao.selectAll();
		List<ScoreVO> scList = scDao.selectAll();
		List<SubjectVO> sbList = sbDao.selectAll();
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		
		log.debug("Service Student {}", stList.toString());
		log.debug("Service Score {}", scList.toString());
		log.debug("Service Subject {}", sbList.toString());
		log.debug("Service ScoreView {}", scViewList.toString());
		
		return stDao.selectAll();
	}

	@Override
	public Map<String, Object> selectMaps() {
		// TODO Auto-generated method stub
		
		List<StudentVO> stList = stDao.selectAll();
		List<ScoreVO> scList = scDao.selectAll();
		List<SubjectVO> sbList = sbDao.selectAll();
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		
		Map<String, Object> maps = new HashMap<String, Object>();
		
		maps.put("학생", stList);
		maps.put("점수", scList);
		maps.put("과목", sbList);
		maps.put("View", scViewList);
		
		return maps;
		
	}

	@Override
	public String makeStNum() {
		// TODO 현재 날짜에서 연도를 추출하여 학번 만들기
		
		// 현재 날짜에서 연도 문자열 생성하기
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		
		String curYear = sd.format(date);
		String newStNum = this.makeStNum(curYear);
		log.debug("현재 연도 {}, 생성된 학번 {} ", curYear, newStNum);
		
		return newStNum;
	}

	@Override
	public String makeStNum(String prefix) {
		// TODO Auto-generated method stub
		
		String stNum = stDao.getMaxStNum();
		/*
		 *  prefix만큼의 문자열을 건너뛰고 나머지 부분 추출하기
		 *  
		 *  stNum = "20210010" / prefix = "2021"
		 *  stSeq = stNum.substring(4) 형식의 코드를 생성하여 stSeq에는 0010만 남게 만든다.
		 *  
		 */
		String stSeq = stNum.substring(prefix.length());
		log.debug("학번 seq {}", stSeq);
		
		Integer intSeq = Integer.valueOf(stSeq) + 1;
		String newStNum = String.format("%s%04d", prefix, intSeq);
		log.debug("새로 생성된 학번 {}", newStNum);
		
		return newStNum;
	}

	@Override
	public int insert(StudentVO stVO) {
		// TODO Auto-generated method stub
		
		/*
		 * INSERT를 수행하는 시점에서 학번을 ㅁ나들고 싶으면
		 * 
		 * String newStNum = this.makeStNum();
		 * stVO.setSt_num(newStNum);
		 */
		
		return stDao.insert(stVO);
		
	}

	@Override
	public int update(StudentVO stVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String detail(Model model, String st_num) {
		// TODO Auto-generated method stub
		
		String ret = null;
		
		List<SubjectAndScoreDTO> ssList = scDao.selectSubjectAndScore(st_num);
		StudentVO stVO = stDao.findById(st_num);
		
		Integer scoreCount = scDao.scoreCount(st_num);
		Integer scoreSum = scDao.scoreSum(st_num);
		
		ret = ssList != null ? "OK" : "SS_FAIL";
		ret += stVO != null ? "OK" : "ST_FAIL";
		
		model.addAttribute("SC_COUNT", scoreCount);
		model.addAttribute("SC_SUM", scoreSum);
		model.addAttribute("SSLIST", ssList);
		model.addAttribute("STD", stVO);
		
		return ret;
	}

	/*
	 * Transaction의 조건
	 * 
	 * 다수의 CURD는 한 개의 프로세스다
	 * 다수의 CRUD가 모두 정상적으로 완료되어야만 업무가 정상으로 수행된다.
	 * 
	 * 업무가 수행되는 동안 한 곳이라도 CRUD에서 오류가 발생하면
	 *  그 중 CUD가 진행되는 동안 문제가 발생하고 데이터에 오류가 저장될 것이다
	 * 
	 * 이런 상황을 방지하기 위하여 업무 단위를 Transaction이라는 단위로 묶고 
	 * 	모든 업무가 완료되면 데이터를 COMMIT(실제 저장)하고 그렇지 않으면 Rollback ALL(모두 취소)하는 처리
	 */
	@Transactional
	@Override
	public String scoreInput(ScoreInputVO scInputVO) {
		// TODO Auto-generated method stub
		
		log.debug("Service RCV {}", scInputVO.toString());
		
		int size = scInputVO.getSubject().size();
		String st_num = scInputVO.getSt_num();
		
		// 학생, 과목별 성적을 과목별로 개별 INSERT 수행하기
//		for(int i = 0; i < size; i++) {
//			scDao.insertOrUpdate(scInputVO.getSt_num(), scInputVO.getSubject().get(i), scInputVO.getScore().get(i));
//			
//		}
		
		// Dao에 보낼 데이터 변경하기
		// 과목코드와 점수의 List를 담을 변수 선언
		// List<Map<String, String>> scoreMaps = new ArrayList<>();
		List<Map<String, String>> scoreMaps = new ArrayList<Map<String,String>>();
		
		for(int i = 0; i < size; i++) {
			
			String subject = scInputVO.getSubject().get(i);
			String score = scInputVO.getScore().get(i);
			
			Map<String, String> subjectScore = new HashMap<String, String>();
			subjectScore.put("subject", subject);
			subjectScore.put("score", score);
			
			scoreMaps.add(subjectScore);
			
		}
		
		scDao.insertOrUpdateForList(st_num, scoreMaps);
		
		/*
		 * @Transactional로 선언된 method에서 모든 데이터를 insertOrupdate를 수행한 다음 강제로 exception 발생시켰다.
		 * 
		 * 그랬더니 TransactionManager에 의해 모든 insert or update가 Rollback 되어버렸다.
		 */
		// 이유 불문하고 무조건 RuntimeException을 발생시켜라
		// throw new RuntimeException();
		
		return null;
	}

}
