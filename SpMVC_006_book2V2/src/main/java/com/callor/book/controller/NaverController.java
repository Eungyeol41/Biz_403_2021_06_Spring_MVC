package com.callor.book.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.config.NaverQulifier;
import com.callor.book.model.BookDTO;
import com.callor.book.service.BookService;
import com.callor.book.service.impl.NaverMainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/naver")
@RequiredArgsConstructor
public class NaverController {
	
	@Qualifier(NaverQulifier.NAVER_MAIN_SERVICE_V1)
	protected final NaverMainService nService;
	
//	protected final BookService myBookService;

	/*
	 * web client에서 서버로 Request를 할 때 어떤 데이터를 보내는 방법
	 * 
	 * ?변수 == 값 : GET method 방법으로 queryString으로 데이터 보내기 ?username=callor&pw
	 * 
	 * 2. request body에 담아 보내는 방법 <form method=POST><input username>
	 * 
	 * 3. url path(Path Variable) 방식 https://localhost:8080/book/naver/korea
	 * https://localhost:8080/book/naver/callor/12345
	 * 
	 * 
	 */
//	@RequestMapping(value = "/{url}", method = RequestMethod.GET)
//	public String home(@PathVariable(name = "url") String url, Model model) {
//
//		log.debug("URL {}", url);
//
//		return "home";
//	}

	@RequestMapping(value = "/{CAT}", method = RequestMethod.GET)
	public String home(@PathVariable(name = "CAT") String cat,
			@RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) throws MalformedURLException, IOException, ParseException {

		log.debug("CAT:{}", cat);
		model.addAttribute("CAT", cat);
		
//		List<BookDTO> myBookList = myBookService.selectAll();
//		model.addAttribute("MYBOOKS", myBookList);
		
		nService.NaverGetData(cat, search, model);

		return "home";

	}

}
