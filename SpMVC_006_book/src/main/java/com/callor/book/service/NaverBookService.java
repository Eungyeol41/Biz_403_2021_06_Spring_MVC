package com.callor.book.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.callor.book.model.BookDTO;

/*
 * Naver의 Open API를 통하여 데이터를 검색하는 service
 * 
 * 도서정보, 뉴스정보, 영화정보를 가져올텐데 Naver에서 제공하는 데이터가 조금씩 차이가 난다
 * 
 * OpenAPI를 통해서 데이터를 가져올 때 받을 데이터에 대한 VO(DTO)를 생성해야 한다
 * 
 * Naver에 받을 데이터에 대한 VO를 만들어야 하는데 각각 도서, 뉴스, 영화 데이터가 조금씩 달라서 3개의 VO를 생성해야 한다
 * 그래서 인터페이스에 Generic을 선언하여 VO별로 필요에 따라 클래스를 만들 수 있도록 한다.
 */
public interface NaverBookService extends NaverGenericService<BookDTO>{

	
}
