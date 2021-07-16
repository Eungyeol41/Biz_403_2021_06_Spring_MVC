package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.PageDTO;
import com.callor.gallery.persistance.ext.FileDao;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;
import com.callor.gallery.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * final로 선언된 Inject 변수의 초기화를 시키는 데 필요한 생성자를 자동으로 만들어주는 lombok의 기능이다
 * 
 * 클래스를 상속하면 @RequiredArgsConstructor는 상속받은 클래스(V2)에서는 사용 불가
 */
@Slf4j
@RequiredArgsConstructor
@Service("gallerServiceV1")
public class GalleryServiceImplV1 implements GalleryService {

	protected final GalleryDao gDao;
	protected final FileDao fDao;

	@Qualifier("fileServiceV2")
	protected final FileService fService;
	
	protected final PageService pService;

	/*
	 * @Autowired가 설정된 변수, method, 객체 등을 만나면 
	 * 	Spring framework는 변수를 초기화, 
	 * 		method를 실행하여 또 변수 초기화 
	 * 			이미 생성되어 준비된 객체에 주입 등을 수행한다
	 */
	@Autowired
	public void create_table() {

		Map<String, String> maps = new HashMap<String, String>();

		gDao.create_table(maps);
		fDao.create_table(maps);

	}

	@Override
	public int insert(GalleryDTO galleryDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void input(GalleryDTO gDTO, MultipartFile one_file, MultipartHttpServletRequest m_file) throws Exception {
		// TODO Auto-generated method stub

		// 대표이미지가 업로드 되면
		// 이미지를 서버에 저장하고 저장된 파일의 이름을 return받기
		String strUUID = fService.fileUp(one_file);
		gDTO.setG_image(strUUID);

		log.debug("INSERT 전 seq {} ", gDTO.getG_seq());
		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 INSERT
		// mapper에서 INSERT를 수행한 후 새로 생성된 g_seq 값을 selectKey하여 gDTO의 g_seq 변수에 담아놓은 상태
		gDao.insert(gDTO);

		log.debug("INSERT 후 seq {}", gDTO.getG_seq());

		// 갤러리 게시판에 seq값과 파일들을 묶음으로 INSERT하기 위한 준비하기
		Long g_seq = gDTO.getG_seq();

		List<FileDTO> files = new ArrayList<FileDTO>();
		
		List<MultipartFile> fileList = m_file.getFiles("m_file");
		
		// 업로드된 멀티파일을 서버에 업로드하고 원래 파일이름과 UUID가 첨가된 파일이름을 추출하여 FileDTO에 담고 다시 List에 담아놓는다
		for (MultipartFile file : fileList) {
			
			String fileOrName = file.getOriginalFilename();
			String fileUUName = fService.fileUp(file);

			FileDTO fDTO = FileDTO.builder().file_gseq(g_seq)// 갤러리 데이터의 PK 값
					.file_original(fileOrName).file_upname(fileUUName).build();
			files.add(fDTO);
			
		}
		log.debug("이미지들 {}", files.toString());
		
		fDao.insertWithList(files);
	}

	@Override
	public List<GalleryDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub

		List<GalleryDTO> gList = gDao.selectAll();
		log.debug("갤러리 리스트 {}", gList.toString());

		return gList;
	}

	@Override
	public List<GalleryFilesDTO> findByIdGalleryFiles(Long g_seq) {
		
		List<GalleryFilesDTO> gfList = gDao.findByIdGalleryFiles(g_seq);
		/*
		 * Dao로부터 select를 한 후 데이터 검증을 하기 위해서 사용하는 코드
		 * gfList가 데이터가 조회되지 않아 null이 발생할 수 있다.
		 */
		if(gfList != null && gfList.size() > 0) {
			log.debug("GFLIST : {}", gfList.toString());
		} else {
			log.debug(" * 조회된 데이터가 없음 * ");
		}
		
		return gfList;
	}

	@Override
	public GalleryDTO findByIdGallery(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int file_delete(Long g_seq) {
		// TODO Auto-generated method stub
		
		// 파일을 삭제하기 위하여 저장된 파일 정보를 SELECT하기
		FileDTO fDTO = fDao.findById(g_seq);
		
		// 업로드되어 저장된 파일을 삭제
		int ret = fService.delete(fDTO.getFile_upname());
		if(ret > 0) {
			// tbl_files에서 데이터 삭제
			ret = fDao.delete(g_seq);
		}
		
		return ret;
	}

	/*
	 * pageNum을 매개변수로 받아서 selectAll 한 데이터를 잘라내고 pageNum에 해당하는 list 부분만 return하기
	 * 
	 * 한 페이지에 보여줄 list는 10개
	 */
	@Override
	public List<GalleryDTO> selectAllPage(int pageNum) throws Exception {
		// TODO Auto-generated method stub
		
		// 1. 전체 데이터 SELECT하기
		List<GalleryDTO> gListAll = gDao.selectAll();
		
		// 2. if(pageNum == 1) => list에서 0번째 요소 ~ 9번째 요소까지 추출하기
		//		if(pageNum == 2) => list에서 10번재 요소 ~ 19번째 요소까지 추출해야 함.
		//		if(pageNum == 3) => list에서 20번재 요소 ~ 29번째 요소까지 추출해야 함.
		
		int totalCount = gListAll.size();
		
		int start = (pageNum -1) * 12;
		int end = start + 12;
		
		if(pageNum * 12 > totalCount - 12) {
			end = totalCount;
			start = end - 10;
		}
		
		List<GalleryDTO> pageList = new ArrayList<>();
		for(int i = start; i < end; i++) {
			pageList.add(gListAll.get(i));
		}
		
		return pageList;
	}
	
	@Override
	public List<GalleryDTO> selectAllPage(int intPageNum, Model model) throws Exception {
		// TODO Auto-generated method stub
		
//		List<GalleryDTO> pageList = this.selectAllPage(intPageNum);
//		int galleryTotal = gDao.countAll();
//		int totalPages = galleryTotal / 12;
//		// 현재 선택된 page가 14라면 page / 2를 하여 선택된 page 번호에서 값을 밸셈하여 시작 값으로 설정
//		int startPage = (intPageNum - ( 10 / 2 ));
//		int endPage = startPage + 10;
//		PageDTO pageDTO = PageDTO.builder().startPage(startPage).endPage(endPage).totalPages(totalPages).build();
//		model.addAttribute("PAGE_NAV", pageDTO);
//		model.addAttribute("TOTAL_PAGE", totalPages);
//		model.addAttribute("START_PAGE", startPage);
//		model.addAttribute("END_PAGE", endPage);
		
		
		List<GalleryDTO> galleryAll = gDao.selectAll();
		int totalListSize = galleryAll.size();
		
		PageDTO pageDTO = pService.makePagination(totalListSize, intPageNum);
		
		List<GalleryDTO> pageList = new ArrayList<>();
		for(int i = pageDTO.getOffset(); i < pageDTO.getLimit(); i++) {
			pageList.add(galleryAll.get(i));
		}
		
		model.addAttribute("PAGE_NAV", pageDTO);
		model.addAttribute("GALLERYS", pageList);
		
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(int pageNum, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchOderPage(int pageNum, String search, String column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findBySearchPage(String search_column, String search_text, int pageNum, Model model) {
		// TODO Auto-generated method stub
		
		List<GalleryDTO> gList = gDao.findBySearch(search_column, search_text);
		
		int totalListSize = gList.size();
		PageDTO pageDTO = pService.makePagination(totalListSize, pageNum);
		
		List<GalleryDTO> pList = new ArrayList<>();
		
		if(pageDTO == null) {
//			model.addAttribute("GALLERYS", gList);
			// 검색 결과 없으면 전체 리스트 나오게 하는거
			model.addAttribute("GALLERYS", gDao.selectAll());
			return;
		}
		
		for(int i = pageDTO.getOffset(); i < pageDTO.getLimit(); i++) {
			pList.add(gList.get(i));
		}
		
		model.addAttribute("GALLERYS", pList);
	}

	
}
