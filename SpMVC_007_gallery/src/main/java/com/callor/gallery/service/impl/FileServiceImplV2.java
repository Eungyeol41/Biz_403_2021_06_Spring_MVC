package com.callor.gallery.service.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("fileServiceV2")
public class FileServiceImplV2 extends FileServiceImplV1 {

	// file-context.xml에 설정된 변수값 가져오기
	protected final String winPath;
	protected final String macPath;

	protected String fileUpPath;

	@Autowired
	public void getFilePath(String winPath, String macPath) {

		/*
		 * 파일을 업로드할 때 사용할 path 가졍괴
		 * 
		 * 1. 지정된 폴더를 윈도우 기반의 폴더로 설정
		 * 2. mac 기반의 폴더가 있으면 해당 폴더로 변경 
		 */
		this.fileUpPath = this.winPath;

	}

	@Override
	public String fileUp(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub

		String originFileName = file.getOriginalFilename();
		if (originFileName == null || originFileName.isEmpty()) {
			return "";
		}

		// 현재 시스템에 macPath로 설정된 폴더가 있는 지 확인 후
		//		있으면 업로드 폴더를 macPath에 지정된 값으로 설정하기
		File path = new File(macPath);
		if (path.exists()) {
			this.fileUpPath = this.macPath;
		}

		// 다시 한 번 fileUpPath가 있는 지 검사
		// winPath일 경우는 폴더를 만들어라
		path = new File(fileUpPath);
		if (!path.exists()) {
			path.mkdir();
		}

		String strUUID = UUID.randomUUID().toString();

		strUUID += originFileName;

		File uploadPathAndFile = new File(fileUpPath, strUUID);

		file.transferTo(uploadPathAndFile);

		return strUUID;
	}

	// 전달받은 파일이름으로 된 파일을 fileUpPath에서 삭제
	@Override
	public int delete(String imgFileName) {

		/*
		 * fileService.delete(파일명)을 호출할 때 파일명이 null이거나 없으면 진행을 멈추는 코드
		 * 
		 * 이러한 코드는 호출하는 곳에서
		 * if(파일명 == null || 파일명.isEmpty()) {
		 * 		fileService.delete(파일명)
		 * }
		 * 처럼 호출할 수 있지만 delete() method를 사용하는 곳이 많다면 그 때마다 검사하는 코드를 부착해야 한다
		 * 
		 * 그것보다는 delete() method가 시작될 때 파일의 null값 등을 검사하여 실행을 취소하는 방법이 더 편리한 코드가 될 것이다
		 */
		if(imgFileName == null || imgFileName.isEmpty()) {
			return 1;
		}
		
		// 삭제하기 위한 파일 정보 객체 생성
		File delFile = new File(this.fileUpPath, imgFileName);

		if (delFile.exists()) {
			boolean ok = delFile.delete();
			if (ok) {
				log.debug("파일 삭제 성공");
				return 1;
			} else {
				log.debug("파일 삭제 실패");
				/*
				 * method return type을 wrapper class(Integer, Long) 등으로 설정하면 실패하는 메시지를 return할 때 null을 return하면 된다
				 * 
				 * primitive 숫자형으로 return type을 설정한 경우 null값을 return할 수 없다.
				 * 
				 * 이럴 때 성공한 메시지는 양의 정수값을 return하고 실패한 메시지는 음의 정수값을 return한다
				 * 
				 * 호출한 곳에서 성공, 실패를 검사할 때
				 * 	- if( ret > 0 ) 성공
				 * 	- if( ret < 0 ) 실패
				 * 	와 같은 방식을 사용할 수 있다.
				 */
				return -1;
			}
		}
		return 1; // 첨부파일이 없더라도 성공한 것처럼..
	}

}
