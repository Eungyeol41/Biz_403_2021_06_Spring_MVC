package com.callor.book.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class NaverBookController {
	
	@Qualifier("naverServiceV1")
	protected final NaverService<BookDTO> nBookService;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home(@RequestParam(name="search", required = false, defaultValue = "") String search, Model model) throws MalformedURLException, IOException, ParseException {
	
		if(search != null && !search.equals("")) {
			String queryURL = nBookService.queryURL(search.trim());
			String jsonString = nBookService.getJsonString(queryURL);
			List<BookDTO> bookList = nBookService.getNaverList(jsonString);
			
			model.addAttribute("BOOKS", bookList);
		}
		
		model.addAttribute("pHolder", "도서명");
		
		return "home";
	}
	
}
