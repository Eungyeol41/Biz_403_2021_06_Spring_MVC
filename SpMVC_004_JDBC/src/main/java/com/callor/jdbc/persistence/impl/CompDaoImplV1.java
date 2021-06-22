package com.callor.jdbc.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.persistence.CompDao;

import lombok.extern.slf4j.Slf4j;

/*
 * @Copmopnent
 * 
 * 모든 Component를 대표하는 Annotation
 * Compile 과정에서 다소 비용이 많이 소요된다
 * 
 * Component Annotation => 
 * @Controller, @Service, @Repository와 같은 Annotation을 사용한다
 * 'Spring Container에게 이 표식이 부착된 클래스를 bean으로 생성하여 보관해 달라'는 지시어
 * 
 * CompVO c = new CompVO()
 * Object o = new CompVO() => X
 * 
 * CompServiceImplV1 cs = new CompServiceImplV1();
 * CompService cs1 = new CompServiceImplV1(); -> 이렇게 사용 
 */

@Slf4j
@Repository("compDaoV1")
public class CompDaoImplV1 implements CompDao{

	protected final JdbcTemplate jdbcTemplate;
	public CompDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<CompVO> selectAll() {
		// TODO 출판사 전체 조회
		String sql = " SELECT * FROM tbl_company ";
		
		List<CompVO> compList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CompVO>(CompVO.class));
		
		log.debug("Comp Select {}", compList.toString());
		
		return compList;
	}

	@Override
	public CompVO findById(String cp_code) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_company WHERE cp_code = ? ";
		Object[] params = new Object[] { cp_code };
		
		CompVO vo = (CompVO) jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<CompVO>(CompVO.class));
		
		return vo;
	}

	// tbl_company table에서 cpcode(출판사코드) 중 가장 큰 값을 추출하기
	@Override
	public String findByMaxCode() {
		// TODO tbl_company table에서 cpcode(출판사코드) 중 가장 큰 값을 추출하기
		
		String sql = " SELECT MAX( cp_code ) FROM tbl_company";
		
		String cpcode = (String) jdbcTemplate.queryForObject(sql, String.class);
		
		return cpcode;
	}

	@Override
	public int insert(CompVO vo) {
		// TODO 출판사 정보 추가하기
		
		String sql =  " INSERT INTO tbl_company " ;
		sql += " ( cp_code, cp_title, cp_ceo, cp_addr, cp_tel ) ";
		sql += " VALUES( ?, ?, ?, ?, ? ) " ;
		
		Object[] params = new Object[] {
				vo.getCp_code(),
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_addr(),
				vo.getCp_tel() 
		};

		return jdbcTemplate.update(sql, params);
		
	}

	@Override
	public int update(CompVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * jdbcTemplate를 사용하여 QUERY를 실행할 때 각 method에서 매개변수를 받아 QUERY에 전달할텐데
	 * 	이 때 매개변수는 primitive로 받으면 값을 변환시키는 어려움이 있다.
	 * 
	 * 권장사항으로 매개변수는 wrapper class type으로 설정
	 * 	즉, 숫자형일 경우 int, long 대신 Integer, Long 형으로 선언하는 것이 좋다.
	 * 
	 * vo에 담겨서 전달된 값은 Obejct[] 배열로 변환한 후 jdbcTemplate에 전달해주어야 한다.
	 * 
	 * 하지만, 1 ~ 2개 정도의 값을 전달할 때 Object[] 배열로 변환 후 전달을 하면 Object 객체 저장소가 생성되고 메모리를 사용한다
	 * 		-> cpcode,cpcode 식으로 연결해서 가능(1 ~ 2개 정도)
	 * 
	 * 이 때, 전달되는 변수가 wrapper class type이면 Object[] 배열로 만들지 않고 바로 주입할 수 있다.
	 */
	@Override
	public int delete(String cpcode) {
		// TODO 출판사 정보 삭제하기
		
		String sql = " DELETE FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		
		// cpcode가 String wrapper class type이므로 Object[] 배열로 변환하지 않고 바로 전달이 가능하다
		// Object[] params = new Object[] { cpcode };
		// cpcode가 class type이라서 params로 쓰지 않아도 상관 X
		jdbcTemplate.update(sql,cpcode);
		
		return 0;
	}

	// 출판사 이름으로 검색하기
	@Override
	public List<CompVO> findByCName(String cname) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_company ";
		//		WHERE cp_code LIKE '%' || '%' ---> Oracle
		sql += " WHERE cp_name LIKE CONCAT( '%', ? '%' ) "; // MYSQL
		
		// SELECT를 수행한 후 각각의 데이터를 CompVO에 담고 list에 add하여 return한 후 compList에 받기
		List<CompVO> compList = jdbcTemplate.query(sql, new Object[] { cname }, new BeanPropertyRowMapper<CompVO>(CompVO.class));
		
		return compList;
	}

	@Override
	public List<CompVO> findByTel(String tel) {

		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_tel LIKE CONCAT('%', ? '%' ) "; // mysql
		List<CompVO> compList = jdbcTemplate.query(sql, new Object[] { tel },
				new BeanPropertyRowMapper<CompVO>(CompVO.class));
		return compList;

	}

	@Override
	public List<CompVO> findByCeo(String ceo) {
		
		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_ceo LIKE CONCAT('%', ? '%' ) "; // mysql
		
		List<CompVO> compList = jdbcTemplate.query(sql, new Object[] { ceo },
				new BeanPropertyRowMapper<CompVO>(CompVO.class));
		
		return compList;
	}

}
