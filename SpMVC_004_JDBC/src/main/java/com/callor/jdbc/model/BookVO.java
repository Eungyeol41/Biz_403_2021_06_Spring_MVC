package com.callor.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {

	private String 	bk_isbn;	 	// ISBN
	private String 	bk_title;		// 도서명
	private String 	bk_ccode; 		// 출판사코드
	private String 	bk_acode;		// 저자코드
	private String 	bk_date; 		// 출판일
	
	/*
	 * VO(DTO)를 설계할 때 숫자형 변수는 PK나 특별히 조회할 때 사용하는 칼럼은 class형 (Integer, Long, Float)로 선언하는 것이 좋다.
	 * 이 칼럼이 NULL인 경우에 조건을 부여하여 연산하는 데 유리하다.
	 * 
	 * 단, class형으로 선언을 했을 때는 VO(DTO)에 해당변수를 0으로 선언해주는 것이 편리할 때가 있다.
	 * 일반적이 숫자형 변수는 primitive형으로 선언하는 것이 좋다.
	 * 
	 * VO(DTO)클래스의 변수를 primitive로 선언하면 자동으로 0으로 초기화가 된다.
	 * 
	 * DB에 INSERT를 수행할 때 해당 칼럼이 Not NULL로 되어있을 때 별다른 처리를 하지 않아도 SQL Exception이 발생하지 않는다.
	 */
	private int bk_price; 	// 가격
	private int	bk_pages;	// 페이지
	
}
