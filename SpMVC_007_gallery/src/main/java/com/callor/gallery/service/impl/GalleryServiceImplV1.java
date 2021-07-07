package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.persistance.ext.FileDao;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("gallerServiceV1")
public class GalleryServiceImplV1 implements GalleryService {

	protected final GalleryDao gDao;
	protected final FileDao fDao;

	@Qualifier("fileServiceV2")
	protected final FileService fService;

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

		log.debug(" INSERT 전 seq {} ", gDTO.getG_seq());
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
	}

	@Override
	public List<GalleryDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub

		List<GalleryDTO> gList = gDao.selectAll();
		log.debug("갤러리 리스트 {}", gList.toString());

		return gList;
	}

}
