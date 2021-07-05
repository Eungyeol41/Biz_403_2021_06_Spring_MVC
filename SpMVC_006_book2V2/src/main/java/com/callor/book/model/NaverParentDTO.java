package com.callor.book.model;

import java.util.List;

/*
 * Naver News 정보를 담는 NewsDTO 클래스를 List로 선언한 부모 DTO로 만든다.
 */
public class NaverParentDTO {

	public String lastBuildDate; // Mon, 05 Jul 2021 09:34:23 +0900,
	public String total; // 440111,
	public String start; // 1,
	public String display; // 10,
	public List<NewsDTO> items; // [
	            
}
