package com.callor.book.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQulifier;
import com.callor.book.dao.ext.BookDao;
import com.callor.book.model.BookDTO;
import com.callor.book.service.BookService;
import com.callor.book.service.NaverAbstractService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImplV1 implements BookService{

	@Qualifier(NaverQulifier.NAVER_BOOK_SERVICE_V2)
	protected final NaverAbstractService<BookDTO> nBookService;
	protected final BookDao bookDao;
	
	@Override
	public int insert(String isbnUTF) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		String isbn = URLDecoder.decode(isbnUTF, "UTF-8"); // add throws
		String[] isbns = isbn.split(" "); // 띄어쓰기!!
		
		isbn = isbns[1];
		
		String queryURL = nBookService.queryURL(isbn);
		String jsonString = nBookService.getJsonString(queryURL);
		List<BookDTO> books = nBookService.getNaverList(jsonString);
		
		BookDTO book = books.get(0);
		book.setIsbn(isbn);
		
		bookDao.insert(book);
		
		return 0;
	}

}
