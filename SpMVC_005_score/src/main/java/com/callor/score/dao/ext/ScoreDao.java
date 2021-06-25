package com.callor.score.dao.ext;

import java.util.List;

import com.callor.score.dao.GenericDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.SubjectAndScoreDTO;

public interface ScoreDao extends GenericDao<ScoreVO, Long>{

	public List<ScoreDTO> selectViewAll();
	public List<ScoreDTO> findByStNum(String st_num);
	public List<SubjectAndScoreDTO> selectSubjectAndScore(String st_num);

	/*
	 * Mybatis에서 SQL SELECT의 결과가
	 * 1. 숫자일 경우 resultType으로 wrapper class를 지정
	 * 		int: Integer, long : Long
	 * 
	 * 2. 문자열의 경우 resultType으로 String을 반드시 지정
	 * 		String : String
	 * 
	 * Dao method의 return type도 wrapper class로 지정
	 */
	public Integer scoreCount(String st_num);
	public Integer scoreSum(String st_num);
	
}
