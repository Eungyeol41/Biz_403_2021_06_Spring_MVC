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
public class SubjectAndScoreDTO {

	private String ss_code ; // sb_code
	private String ss_stname ; // sb_name
	private String ss_prof ; // sb_prof
	private String ss_stnum ; // sc_stnum
	private int ss_score ; // sc_score
	

}
