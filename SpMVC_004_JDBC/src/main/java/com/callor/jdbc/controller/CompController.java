package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.persistence.CompDao;
import com.callor.jdbc.service.CompService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value ="/comp")
public class CompController {

	protected final CompDao compDao;
	protected final CompService compService;
	public CompController(CompDao compDao, CompService compService) {
		this.compDao = compDao;
		this.compService = compService;
	}
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String comp() {
		return "comp/list";
	}
	
	// localhost:8080/jdbc/comp/insert로 호출되는 함수
	@RequestMapping(value ="/insert", method = RequestMethod.GET)
	public String insert() {
		
		// WEB-INF/views/comp/input.jsp를 열어라
		return "comp/input";
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(CompVO cmVO) {
		
		log.debug("Company VO {}", cmVO.toString());
		compService.insert(cmVO);
		
		return "redirect:/insert";
		
	}
//	
//	@RequestMapping(value ="/update", method = RequestMethod.GET)
//	public String update() {
//		
//		return "comp/input";
//		
//	}
//	
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	// Web에서 cpcode로 데이터 전송되어서 오면 delete method에서는 code로 받아라
//	public String delete(@RequestParam("cpcode") String code) {
//		
//		compDao.delete(code);
//		
//		return "redirect:/";
//	}
}
