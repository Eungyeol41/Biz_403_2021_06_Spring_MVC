package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.score.model.ScoreInputVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

	protected final StudentService stService;
//	protected final ScoreService scService;
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String studentList(Model model) {
		
		List<StudentVO> stList = stService.selectAll();
		
		model.addAttribute("BODY", "STUDENT_LIST");
		model.addAttribute("STLIST", stList);
		
		log.debug("stList[]", stList.toString());
		return "home";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		
		StudentVO stVO = new StudentVO();
		stVO.setSt_num(stService.makeStNum());
		
		// stService.makeStNum("2021");
		// stService.makeStNum();
		
		model.addAttribute("STD", stVO);
		model.addAttribute("BODY", "STUDENT_INPUT");
		
		return "home";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.POST)
//	public String detail(
//			@RequestParam(name="subject") List<String> subject,
//			@RequestParam(name="score") List<String> score) {
	public String detail(ScoreInputVO scInputVO) {
//		log.debug("Subject {}", subject.toString());
//		log.debug("Score {}", score.toString());
		
		log.debug("Score Input {}", scInputVO.toString());
		String ret = stService.scoreInput(scInputVO);
		return "home";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST )
	public String insert(StudentVO studentVO, Model model) {
		
		log.debug("Req 학생정보 : {}", studentVO.toString());

		int ret = stService.insert(studentVO);
	    model.addAttribute("BODY","STUDENT_INPUT");

	    return "redirect:/student";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String st_num, Model model) {
		
//		List<SubjectAndScoreDTO> ssList = scService.selectScore(st_num);
		
		// StudentVO stVO = stService.findById();
		
		String ret = stService.detail(model, st_num);
		
//		model.addAttribute("SSLIST", ssList);
		model.addAttribute("BODY", "STUDENT_DETAIL");
		
		return "home";
	}
	
}
