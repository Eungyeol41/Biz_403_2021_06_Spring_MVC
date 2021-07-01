package com.callor.book.service.impl.news;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQulifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQulifier.NAVER_NEWS_SERVICE_V1)
public class NaverNewsServiceImplV1 extends NaverAbstractService<NewsDTO>{

	@Override
	public String queryURL(String search) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		String queryURL = NaverSecret.Nurl.NEWS;
		queryURL += "?query=%s&display=10";

		String searchUTF8 = URLEncoder.encode(search, "UTF-8");
		queryURL = String.format(queryURL, searchUTF8);
		log.debug("queryURL : {}", queryURL);
		
		return queryURL;
	}

	@Override
	public List<NewsDTO> getNaverList(String jsonString) throws ParseException {
		// TODO Auto-generated method stub
		
		JsonElement jsonElement = JsonParser.parseString(jsonString);
		JsonElement oItems = jsonElement.getAsJsonObject().get("items");
		
		Gson gson = new Gson();
		TypeToken<List<NewsDTO>> typeToken = new TypeToken<List<NewsDTO>> () {};
		
		List<NewsDTO> news = gson.fromJson(oItems, typeToken.getType());
		
		return news;
		
	}

}
