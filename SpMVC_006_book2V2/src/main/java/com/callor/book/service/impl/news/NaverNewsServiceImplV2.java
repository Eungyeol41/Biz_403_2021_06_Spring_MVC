package com.callor.book.service.impl.news;

import java.awt.PageAttributes.MediaType;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callor.book.config.NaverQulifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.NaverParentDTO;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQulifier.NAVER_NEWS_SERVICE_V1)
public class NaverNewsServiceImplV2 extends NaverAbstractService<NewsDTO>{

	@Override
	public List<NewsDTO> getNaverList(String queryURL) throws Exception {
		// TODO Auto-generated method stub
	
		// Spring framework 3.2에서 처음 도입된 클래스
		RestTemplate resTemp = new RestTemplate();
		ResponseEntity<NaverParentDTO> resList = null;
		
		/*
		 * json-simple, Gson 등으로 parsing을 수행할 때는 URL 클래스를 사용하여 naver에 요청하고  결과를 jsonStirng으로 받았다
		 * 
		 * URI 클래스를 사용하여 naver에 요청하는 정보를 생성하기
		 * 내부적으로 인코딩 등의 문제를 없애기 위해 URL을 사용하지 않고 URI를 사용한다
		 */
		URI restURI = new URI(queryURL);
		
		/*
		 * Naver에 요청할 때 사용할 인증정보를 http protocol의 header에 부착하기
		 */
		HttpHeaders header = new HttpHeaders();
		header.set("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
		header.set("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
		
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>("parameter", header);
		
		resList = resTemp.exchange(restURI, HttpMethod.GET, entity, NaverParentDTO.class);
		List<NewsDTO> movies;
		
		return movies;
	}
}
