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
public class CompVO {

	private String cp_code; 	//	출판사코드
	private String cp_title;	//	출판사명
	private String cp_ceo; 		//	대표
	private String cp_tel; 		//	전화번호
	private String cp_addr; 	//	주소
	private String cp_genre; 	//	주요장르

}
