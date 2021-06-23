package com.callor.score.model;

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
public class SubjectVO {

	private String sb_code; // 과목코드
	private String sb_name; // 과목명
	private String sb_prof; // 담당교수

}
