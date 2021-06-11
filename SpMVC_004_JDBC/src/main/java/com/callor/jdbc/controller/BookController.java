package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/books")
@Controller
public class BookController {

	/* 
	 * @RequestMapping(value = {"/",""})
	 * 		=> /를 넣거나 넣지 않거나
	 * 
	 * localhost:8080/jdbc/books/
	 * localhost:8080/jdbc/books
	 */
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String books() {
		
		log.debug("Books Root");
		
		// /WEB-INF/views/books/list.jsp로 가라
		return "books/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "books/input";
	}
}
