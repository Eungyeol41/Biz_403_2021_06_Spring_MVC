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
public class ScoreVO {

	private String sc_seq; 		// SEQ
	private String sc_stnum; 	// 학번
	private String sc_sbcode; 	// 과목이름
	private int		sc_score; 	// 점수

}
