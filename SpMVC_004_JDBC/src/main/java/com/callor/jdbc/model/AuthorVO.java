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
public class AuthorVO {

	private String au_code;		//	저자코드
	private String au_name;		//	저자명
	private String au_tel;		//	전화번호
	private String au_addr;		//	주소
	private String au_genre;	//	주요장르

}
