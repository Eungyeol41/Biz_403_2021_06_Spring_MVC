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
public class ScoreDTO {

	private String sc_seq;
	private String sc_stnum;
	private String sc_stname;
	private String sc_sbcode;
	private String sc_Sbname;
	private int sc_sbscore;
}
