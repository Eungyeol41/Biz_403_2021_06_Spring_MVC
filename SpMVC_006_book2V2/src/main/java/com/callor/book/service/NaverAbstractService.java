package com.callor.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.ParseException;

import com.callor.book.config.NaverSecret;

/*
 * Abstract 키워드를 추가 -> 추상클래스
 * 
 * 추상 클래스
 * 일반적인 클래스와 인터페이스의 성질을 모두 포함하는 클래스
 * 
 * 목적
 * 1. 상속되는 부모 클래스의 역할
 * 		- 스스로는 객체로 생성될 수 없다.
 * 2. 추상메서드를 포함하는데 상속받은 곳에서는 반드시 추상메서드를 구현해야 한다
 * 3. 상속받은 클래스들에 필요한 공통 처리 method는 일반 클래스처럼 자체적으로 구현코드를 가지고 잇다.
 * 4. 공통 처리 method도 필요에 따라 상속받는 곳에서 재정의할 수 있다.
 * 5. 인터페이스의 역할을 수행
 * 		- 여러 클래스의 원형(prototype)의 역할을 수행하면서
 * 		- 상속받는 클래스들에 공통으로 필요한 코드를 미리 제공
 * 
 * 객체 생성하는 방법
 * 
 * 추상클래스 객체 = new 상속받은 클래스()
 * 인터페이스 객체 = new impl 클래스()
 * 추상클래스 객체 = mew 추상클래스() --> 사용 불가
 */
public abstract class NaverAbstractService<T> {

	public abstract String queryURL(String search) throws UnsupportedEncodingException;

	public String getJsonString(String queryURL) throws IOException {
		URL url = new URL(queryURL);
		HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();

		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);

		int httpStatusCode = httpConn.getResponseCode();

		InputStreamReader is = null;
		if (httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream());
		} else {
			is = new InputStreamReader(httpConn.getErrorStream());
		}

		BufferedReader buffer = null;
		buffer = new BufferedReader(is);

		StringBuffer sBuffer = new StringBuffer();
		while (true) {
			String reader = buffer.readLine();
			if (reader == null) {
				break;
			}
			sBuffer.append(reader);
		}
		return sBuffer.toString();

	}

	public abstract List<T> getNaverList(String jsonString) throws ParseException;

}
