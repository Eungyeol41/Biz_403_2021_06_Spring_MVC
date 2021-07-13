package com.callor.gallery.service.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("fileServiceV2")
public class FileServiceImplV2 extends FileServiceImplV1{

	// file-context.xml에 설정된 변수값 가져오기
	protected final String winPath;
	protected final String macPath;
	@Override
	public String fileUp(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		
		String originFileName = file.getOriginalFilename();
		if(originFileName == null || originFileName.isEmpty()) {
			return "";
		}
		
		/*
		 * 파일을 업로드할 때 사용할 path 가졍괴
		 * 
		 * 1. 지정된 폴더를 윈도우 기반의 폴더로 설정
		 * 2. mac 기반의 폴더가 있으면 해당 폴더로 변경 
		 */
		String fileUpPath = this.winPath;
		
		// 현재 시스템에 macPath로 설정된 폴더가 있는 지 확인 후
		//		있으면 업로드 폴더를 macPath에 지정된 값으로 설정하기
		File path = new File(macPath);
		if(path.exists()) {
			fileUpPath = this.macPath;
		}
		
		// 다시 한 번 fileUpPath가 있는 지 검사
		// winPath일 경우는 폴더를 만들어라
		path = new File(fileUpPath);
		if(!path.exists()) {
			path.mkdir();
		}
		
		
		String strUUID = UUID.randomUUID().toString();
		
		strUUID += originFileName;
		
		File uploadPathAndFile = new File(fileUpPath, strUUID);
		
		file.transferTo(uploadPathAndFile);
		
		return strUUID;
	}
	
	
}
