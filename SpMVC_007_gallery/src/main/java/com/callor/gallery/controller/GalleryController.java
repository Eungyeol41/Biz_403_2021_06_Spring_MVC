package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.MemberVO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Qualifier("galleryServiceV2")
	protected final GalleryService gService;

	/*
	 * 주소창에 직접 입력 후 Enter로 요청을 할 때
	 * 		localhost:8080/rootPath/gallery/dumy로 요청할 때 Request를 처리
	 * 
	 * a tag를 클릭했을 때
	 * 		<a href="{rootPath}/gallery/dumy>열기</a>
	 * 
	 * JS
	 * 		location.href = "$[rootPath}/gallery/dumy"가 실행됬을 때
	 */
	@RequestMapping(value = "/dumy", method = RequestMethod.GET)
	public String dumy() {
		return "home";
	}
	
	/*
	 * <form action="${rootPath}/dumy" method="POST">
	 * 		<input name="str">
	 * 		<button type="submit">전송</button>
	 * </form>
	 */
	@RequestMapping(value = "/dumy", method = RequestMethod.POST)
	public String dumy(String str) {
		return "home";
	}
	
	// localhost:8080/rootPath/gallery/		또는	localhost:8080/rootPath/gallery로 요청
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		List<GalleryDTO> gList = gService.selectAll();
		model.addAttribute("GALLERYS", gList);
		
		model.addAttribute("BODY", "G-LIST");
		
		return "home";
	}
	
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model, HttpSession session) {

		MemberVO vo = (MemberVO) session.getAttribute("MEMBER");
		if(vo == null){
			return "redirect:/member/login";
		}
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");

		String curDate = sd.format(date);
		String curTime = st.format(date);

		GalleryDTO gDTO = GalleryDTO.builder().g_date(curDate).g_time(curTime).g_writer("en").build();

		model.addAttribute("CMD", gDTO);
		model.addAttribute("BODY", "G-INPUT");
		
		return "home";
	}

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String input(GalleryDTO gDTO, MultipartFile one_file, MultipartHttpServletRequest m_file, Model model) throws Exception {

		log.debug("갤러리 정보 {}", gDTO.toString());
		log.debug("싱글 파일 {}", one_file.toString());
		log.debug("멀티 파일 {}", m_file.toString());

		gService.input(gDTO, one_file, m_file);
		
		return "redirect:/gallery";
	}
	
	@RequestMapping(value = "/detail/{seq}", method = RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model) {
		
		Long g_seq = 0L;
		
		try {
			g_seq = Long.valueOf(seq);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return "redirect:/gallery";
		}
		
		List<GalleryFilesDTO> gfList = gService.findByIdGalleryFiles(g_seq);
		
		model.addAttribute("GFLIST", gfList);
		model.addAttribute("BODY", "G-DETAIL");
		
		return "home";
	}
	
	@RequestMapping(value = "/detail2/{seq}", method = RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model, HttpSession session) {
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			
			log.debug("갤러리 ID값 오류");
			return "redirect:/";
		}
		
		GalleryDTO galleryDTO = gService.findByIdGallery(g_seq);
		
		model.addAttribute("GALLERY", galleryDTO);
		model.addAttribute("BODY", "G-DETAILV2");
		
		return "home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("g_Seq") String seq, HttpSession session) {
		
		/*
		 *  삭제를 요구하면
		 *  1. 로그인이 되었는 지 확인
		 */
		MemberVO memVO = (MemberVO) session.getAttribute("MEMBER");
		// 로그인이 되지 않음
		if(memVO == null) {
			return "redirect:/member/login";
		} 
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 SEQ 오류");
			return "redirect:/gallery";
		}
		
		int ret = gService.delete(g_seq);

		return "redirect:/gallery";
	}
	
}
