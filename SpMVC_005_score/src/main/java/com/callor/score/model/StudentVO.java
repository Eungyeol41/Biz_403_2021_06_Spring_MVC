package com.callor.score.model;

import org.springframework.stereotype.Service;

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
public class StudentVO {
	
	private String	st_num; 	// 학번
	private String	st_name; 	// 학생이름
	private String	st_dept; 	// 전공이름
	private int 	st_grade; 	// 학년
	private String	st_tel; 	// 연락처
	private String	st_addr; 	// 주소


}
