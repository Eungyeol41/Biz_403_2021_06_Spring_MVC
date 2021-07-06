package com.callor.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

	private Long	file_seq; 		// 	일련번호
	private Long	file_gseq; 		// 	갤러리 ID
	private String	file_original; 	// 	원래 파일 이름
	private String	file_upname; 	// 	생성된 파일 이름

}
