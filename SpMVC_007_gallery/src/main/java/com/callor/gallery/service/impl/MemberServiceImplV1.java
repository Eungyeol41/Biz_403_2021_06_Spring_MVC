package com.callor.gallery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.gallery.model.MemberVO;
import com.callor.gallery.persistance.ext.MemberDao;
import com.callor.gallery.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("memberServiceV1")
public class MemberServiceImplV1 implements MemberService{
	
	protected final MemberDao mDao;
	
	// autowired를 통해 자동 실행 시 spring을 속이는 trick
	@Autowired
	public void create_member_table(MemberDao dummy) {
		
		Map<String, String> maps = new HashMap<String, String>();
		mDao.create_table(maps);
		
	}

	/*
	 * 회원가입에서 최초로 가입된 membersms ADMIN
	 * 		- 회원테이블에 데이터가 있냐 없냐
	 * 
	 * ADMIN 권한을 갖는 최초의 가입자는 LEVEL : 0
	 * ADMIN이 아닌 일반 가입자는 기본 LEVEL이 9
	 * LEVEL6보다 큰 member는 이미지 보기만 가능
	 * 이미지 등록을 하려면 LEVEL이 6보다 작다
	 * 최초 가입한 MEMBER가 가입 승인이 되면 LEVEL을 6으로 설정
	 * 이미 가입된 member의 m_userid 정보가 JOIN을 통해서 전달되면 회원 정보를 UPDATE한다
	 */
	@Override
	public MemberVO join(MemberVO memberVO) {
		// TODO 회원가입
		
		List<MemberVO> members = mDao.selectAll();
		log.debug("Members {}", members.toString());
		
		// 아직 member table에 데이터가 하나도 없는 상태
		// 최초로 가입신청이 된 상태
		// 최초로 가입된 member의 LEVEL은 ADMIN
		// ADMIN은 LEVEL : 0
		if(members == null || members.size() < 1) {
			memberVO.setM_level(0);
		} else {
			memberVO.setM_level(9);
		}
		
		mDao.insertOrUpdate(memberVO);
		
		return memberVO;
	}

	@Override
	public MemberVO update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findById(String m_userid) {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = mDao.findById(m_userid.trim());
		
		if(memberVO == null) {
			// 가입되지 않은 사용자 ID
			log.debug("가입되지 않은 사용자 {}", m_userid);
		} else {
			log.debug("조회된 사용자 정보 : {}", memberVO.toString());
		}
		
		return memberVO;
	}

	@Override
	public MemberVO login(MemberVO memberVO, Model model) {
		// TODO 로그인
		
		/*
		 *  1. memberVO에서 m_userid를 getter한 후
		 *  
		 *  2. findById()를 통해서 ID 조회
		 *  
		 *  3. 만약 ID 조회 결과가 null이면 --- 가입되지 않은 ID
		 *  	결과가 null이 아니면 --- 가입 가능한 ID
		 *  
		 *  4. 비밀번호 일치 조회
		 *  	비밀번호 일치하지 않으면 --- 비밀번호 오류 로그인 거부
		 *				일치하면 --- 로그인 처리
		 */
		MemberVO findVO = mDao.findById(memberVO.getM_userid());
		
		if(findVO == null) {
			model.addAttribute("LOGIN_FAIL", "NOT_USER_ID");
			return null;
		}
		
		// 비밀번호 비교
		if(findVO.getM_password().equals(memberVO.getM_password())) {
			return findVO;
		}
		
		model.addAttribute("LOGIN_FAIL", "!EQ_PASS");
		
		return null;
	}

}
