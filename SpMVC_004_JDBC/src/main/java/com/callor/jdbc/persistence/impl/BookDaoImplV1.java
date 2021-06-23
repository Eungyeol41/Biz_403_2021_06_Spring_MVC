package com.callor.jdbc.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.BookVO;
import com.callor.jdbc.persistence.BookDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("bookDaoV1") // xml에 등록할 id
public class BookDaoImplV1 implements BookDao{
	
	// Console로 log를 찍기 위하여 log 객체 생성
	// lombok @Slf4j를 사용하여 아래 코드를 대신한다
	//		private static Logger log = LoggerFactory.getLogger("SERVICE");
	
	// jdbc-context.xml에 선언된 jdbcTemplate bean 사용하기
	protected final JdbcTemplate JdbcTemplate;
	public BookDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.JdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<BookVO> selectAll() {
		// TODO Auto-generated method stub
		
		String sql = " SELECT ";
		
		sql += " bk_isbn, ";
		sql += " bk_title, ";
		sql += " CONCAT( '(', bk_ccode , ')', C.cp_title ) AS bk_ccode , ";
		sql += " CONCAT( '(', A.au_name , ')' ) AS bk_acode, ";
		sql += " bk_date, ";
		sql += " bk_price, ";
		sql += " bk_pages ";
		
		sql += " FROM tbl_books B ";
		sql += " LEFT JOIN tbl_author A ";
		sql += " ON B.bk_acode = A.au_code ";
		sql += " LEFT JOIN tbl_company C ";
		sql += " ON B.bk_ccode = C.cp_code ";
		
		// 데이터가 VO 형태로 되어 있으니 받아라 / BookVO에 잘 담아놓아라 / List 형태로 만들어서 나에게 줘라!
		List<BookVO> books = JdbcTemplate.query(sql, new BeanPropertyRowMapper<BookVO>(BookVO.class));
		
		log.debug("SELECT {}", books.toString());
		
		return books;
	}

	@Override
	public BookVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByComp(String comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insert(BookVO vo) {
		// TODO Auto-generated method stub
		
		String sql = " INSERT INTO tbl_books ( ";
		sql += " bk_isbn, bk_title, bk_ccode, bk_acode, bk_date, bk_price, bk_pages ) ";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ? ) ";
		
		Object[] params = new Object[] {
				
				vo.getBk_isbn(),
				vo.getBk_title(),
				vo.getBk_ccode(),
				vo.getBk_acode(),
				vo.getBk_date(),
				vo.getBk_price(),
				vo.getBk_pages()
		};
		
		// insert, update, delete는 update method 사용
		return JdbcTemplate.update(sql, params);
		
	}

	@Override
	public int update(BookVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
