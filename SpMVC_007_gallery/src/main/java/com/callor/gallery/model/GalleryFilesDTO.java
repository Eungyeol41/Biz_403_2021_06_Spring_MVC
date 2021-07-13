package com.callor.gallery.model;

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
public class GalleryFilesDTO {

	/*
	 * tbl_gallery와 tbl_files table을 JOIN하여 select한 데이터는 실제로는 1 : N의 관계로 생성된 결과이다
	 * 
	 *  하지만 실제 보여지는 list는 마치 tbl_gallery가 여러 개 있는 것처럼 보인다
	 *  
	 *  Ex) 갤러리 1개 데이터에 파일 N개 있다는 가정에서 .. (여기서 N은 3)
	 *  gallery 1개 데이터에 file 3개가 첨부된 경우에 보여지는 list는 다음과 같다
	 *   ================================
	 *  ㅣ	1번 gallery		1번 파일	ㅣ
	 *  ㅣ	1번 gallery		1번 파일	ㅣ
	 *  ㅣ	1번 gallery		1번 파일	ㅣ
	 *   ================================
	 *  
	 *  실제로 gallery는 1개이지만 추출된 데이터는 전체가 List이므로 전체 view를 담을 DTO를 선언하고 List<DTO>형 변수에 데이터를 담았다
	 *  
	 *  실제 데이터와 상관없이 g_seq 등등 gallery 정보를 담을 변수들은 1개만 있으면 될 것을 file 개수만큼 강제로 생성하는 결과가 된다 
	 */
	
	private Long g_seq; 		// bigint
	private String g_writer; 	// varchar(125)
	private String g_date; 		// varchar(10)
	private String g_time; 		// varchar(10)
	private String g_subject; 	// varchar(50)
	private String g_content; 	// varchar(1000)
	
//	---- ↑ 1개		↓ N개 ----	가 만들어지는 게 맞지만
//	---- ↑ N개		↓ N개 ----	가 만들어진다..
	
	private Long f_seq; 		// bigint
	private String f_original; 	// varchar(256)
	private String f_upname; 	// varchar(256)

}
