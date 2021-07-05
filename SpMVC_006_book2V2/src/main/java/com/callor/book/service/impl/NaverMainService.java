package com.callor.book.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.book.config.NaverQulifier;
import com.callor.book.model.BookDTO;
import com.callor.book.model.MovieDTO;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service(NaverQulifier.NAVER_MAIN_SERVICE_V1)
public class NaverMainService {

	@Qualifier(NaverQulifier.NAVER_BOOK_SERVICE_V2)
	protected final NaverAbstractService<BookDTO> nBookService;

	@Qualifier(NaverQulifier.NAVER_MOVIE_SERVICE_V1)
	protected final NaverAbstractService<MovieDTO> nMovieService;

	@Qualifier(NaverQulifier.NAVER_NEWS_SERVICE_V1)
	protected final NaverAbstractService<NewsDTO> nNewsService;

	public void NaverGetData(String cat, String search, Model model)
			throws MalformedURLException, IOException, ParseException {
		if (search != null & !search.equals("")) {

			if (cat.equalsIgnoreCase("BOOK")) {
				//도서 검색 서비스

				String queryURL = nBookService.queryURL(search);
				String jSonString = nBookService.getJsonString(queryURL);
				List<BookDTO> bookList = nBookService.getNaverList(jSonString);
				model.addAttribute("BOOKS", bookList);
				//            컨트롤러가 할 일을 IMPL에서 대신하기

			} else if (cat.equalsIgnoreCase("NEWS")) {
				//뉴스 검색 서비스

				String queryURL = nNewsService.queryURL(search);
				String jsonString = nNewsService.getJsonString(queryURL);

				log.debug("JsonString : {}", jsonString);

				List<NewsDTO> newsList = nNewsService.getNaverList(jsonString);
				model.addAttribute("NEWS_LIST", newsList);

			} else if (cat.equalsIgnoreCase("MOVIE")) {
				//영화 검색 서비스

				String queryURL = nMovieService.queryURL(search);
				String jsonString = nMovieService.getJsonString(queryURL);

				log.debug("JsonString : {}", jsonString);

				List<MovieDTO> movieList = nMovieService.getNaverList(jsonString);
				model.addAttribute("MOVIES", movieList);

			}
		}

	}

	public String naverGetJsonString(String cat, String search) throws Exception {
		// TODO Auto-generated method stub

		String queryURL = nNewsService.queryURL(search); // add throws 한 후에 UnsupportedEncoding 지움.
		String jsonString = nNewsService.getJsonString(queryURL);
		
		return jsonString;
		
	}
}