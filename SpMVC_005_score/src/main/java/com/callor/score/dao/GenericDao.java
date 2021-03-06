package com.callor.score.dao;

import java.util.List;

/*
 * Generic interface
 * 
 * 아직 매개변수, return type이 정해지지 않은 interface
 * 같은 기능의 method를 갖는 interface를 설계할 때 복사, 붙이기 등을 하지 않고
 * 	 공통된method를 쉽게 구현하기 위하여 표준 parent형 interface를 만든 것
 */
public interface GenericDao<VO, PK> {

	public List<VO> selectAll();
	
	public VO findById(PK pk);
	
	public int insert(VO vo);
	public int update(VO vo);
	public int delete(PK pk);
	
}
