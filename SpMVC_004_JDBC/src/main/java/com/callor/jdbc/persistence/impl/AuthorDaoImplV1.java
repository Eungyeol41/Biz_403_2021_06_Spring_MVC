package com.callor.jdbc.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.persistence.AuthorDao;

public class AuthorDaoImplV1 implements AuthorDao{

	/*
	 *  일반적인 Spring Framework에서 다른 bean을 연결하기
	 *  
	 *  @Autowired는 이미 bean으로 생성되어 Spring Controller에 보관중인 객체를 여기에 주입해달라
	 *  	(@Autowired를 사용할 때는 protected 뒤에 final을 붙이지 않는다.)
	 *  
	 *  @Inject
	 *  Java에서 기본적으로 제공하는 DI(Dependency Inject)를 수행하는 Annotation
	 *  일부에서 @Inject를 사용하자는 말이 있지만 SpringFramework를 사용할 때는 굳이 그러지 말자!(는 입장)
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Override
	public List<AuthorVO> selectAll() {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_author ";
		List<AuthorVO> authorList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		
		return authorList;
	}

	@Override
	public AuthorVO findById(String au_code) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_code = ?";

		AuthorVO author 
		= (AuthorVO) jdbcTemplate.query(sql, new Object[] {au_code},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return author;
	}

	@Override
	public int insert(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuthorVO> findByAName(String aname) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_title LIKE CONCAT('%', ? , '%') ";

		List<AuthorVO> authorList 
		=  jdbcTemplate.query(sql, new Object[] {aname},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return authorList;
		
	}

	@Override
	public List<AuthorVO> findByATel(String atel) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_tel =  ? ";

		/*
		 * 전화번호로 조회를 하면 1개의 데이터만 추출될 것이다
		 * 하지만 DB조회에서 PK를 기준으로 조회하는 경우를 제외하고는
		 * 모두 List type으로 데이터가 추출된다고 생각해야 한다.
		 */
		List<AuthorVO> authorList 
		=  jdbcTemplate.query(sql, new Object[] {atel},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return authorList;
		
	}
}
