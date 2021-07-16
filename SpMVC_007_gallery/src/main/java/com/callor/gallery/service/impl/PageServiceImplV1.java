package com.callor.gallery.service.impl;

import org.springframework.stereotype.Service;

import com.callor.gallery.model.PageDTO;
import com.callor.gallery.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Service("pageServiceV1")
public class PageServiceImplV1 implements PageService{

	// 1페이지에 보여질 데이터 리스트의 개수
	protected int listPerPage = 12;
	// 1페이지에 보여질 페이지 nav의 개수
	protected int navPerPage = 5;

	// 전체 페이지 수와 현재 페이지 번호를 매개변수로 받아서 Pagination을 그리는 데 필요한 데이터 생성하기
	@Override
	public PageDTO makePagination(int totalListSize, int currentPage) {
		// TODO Auto-generated method stub
		
		if(totalListSize < 1) return null;
		
		/* 
		 * 이 연산을 수행하면 전체 리스트를 표현하는 데 몇 페이지가 필요한가를 계산
		 * 이 연산은 int 형 데이터로 연산을 수행하므로 소수점이하를 무조건 자른다
		 * 마지막 페이지 개수가 listPerPage보다 작으면 마지막 페이지는 무시해버린다. <- totalPages가
		 */
		
		// 실수형 값을 소수점 이하에서 반올림하여 결과를 만드는 함수
		// Math.round() : 반올림
		// Math.floor() : 버림
		// Math.ceil() : 올림
		int totalPages = (int)Math.round((double)totalListSize / (double)this.listPerPage);
		
		int startPage = currentPage - (this.navPerPage / 2);
		// nav 시작페이지를 계산하여 1보다 작으면 무조건 1부터 시작
		startPage = startPage < 1 ? 1 : startPage;
		
		int endPage = startPage + this.navPerPage - 1;
		// 마지막 페이지가 전체 페이지 수보다 커지면 마지막 페이지로 세팅하기
		endPage = endPage > totalPages ? totalPages : endPage;
		
		/*
		 * 데이터를 자르기 위한 변수 생성
		 * 몇 번 데이터부터 몇 개를 자를 지 선택하기
		 * 
		 * 전체 데이터가 685개라고 가정하면 마지막 페이지를 표현하기 위한 offset, limit은 각각
		 * offset : 680, limit : 5가 되어야 하는데 아래 연산에서 limit : 690이 되어 버린다
		 * 데이터를 자르는 과정에서 Exception 발생할 것..
		 */ 
		int offset = (currentPage - 1) * this.listPerPage;
		int limit = offset + this.listPerPage;
		// 마지막 위치 값이 전체 리스트보다 크면 전체 리스트 끝 값으로 세팅하기
		limit = limit > totalListSize ? totalListSize : limit;
		
		// offset : 680, limit : 685가 되도록 만들었다.
		
		PageDTO pageDTO = PageDTO.builder().totalPages(totalPages).startPage(startPage).endPage(endPage).offset(offset).limit(limit).build();

		return pageDTO;
	}

}
