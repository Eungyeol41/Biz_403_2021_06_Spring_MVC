package com.callor.jdbc.persistence;

import java.util.List;

/*
 * Generic
 * Interface의 부모역할을 하는 super interface
 * Dao와 같은 Interface를 설계할 때 table마다 Dao Interface를 만들고 Interface를 상속받아 클래스를 선언하는 데 이 때 대부분의 Dao에는 같은 이름의 method가 존재한다.
 * 
 * 그런데, 이 method들이 return type과 매개변수가 달라서 어쩔 수 없이 비슷한(거의 같은) method를 갖는 Interface를 중복 작성해야 한다
 * 
 * Generic을 Interface에 설정하고 임의의 이름을 지정해준다
 * 	여기에서는 VO, PK라는 이름을 임의로 지정함.
 * 
 * 그리고 method를 선언할 때 Generic으로 저장된 이름을 사용하여 return type과 공통매개변수를 사용하였다.
 * 
 * 이렇게 Generic을 갖는 Interface를 만들어두고 이 Generic Interface를 상속받아서 사용한다.
 * 
 * 상속받는 Interface는 상속되는 곳에 자신의 VO, primary key 칼럼의 PK type을 지정해두면 공통된 method를 다시 만들 필요가 없어진다.
 */
public interface GenericDao<VO, PK> {

	public List<VO> selectAll();
	public VO findById(PK pk); 
	public int insert(VO vo);
	public int update(VO vo);
	public int delete(PK pk);
	
}
