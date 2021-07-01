package com.callor.book.model;

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
public class NewsDTO {

	private String title; // 개별 검색 결과
	private String originallink; // 검색 결과 문서의 제공 언론사 하이퍼텍스트 link
	private String link; // 검색 결과 문서의 제공 네이버 하이퍼텍스트 link
	private String description; // 검색 결과 문서의 내용을 요약한 패시지 정보
	private String pubDate; // 검색 결과 문서가 네이버에 제공된 시간
	
}
