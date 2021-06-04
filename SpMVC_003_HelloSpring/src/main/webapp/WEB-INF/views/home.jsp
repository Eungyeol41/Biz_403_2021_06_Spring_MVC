<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${rootPath}/static/main.css" rel="stylesheet">
<title>My HomePage</title>
<style>
	form,  h1 {
		width: 80%;
		margin: 0 auto;
	}

	label, input {
		padding: 8px;
		margin: 3px;
	}

	label {
		display: inline-block;
		width: 20%;
		text-align: right;
	}
	
	input {
		display: inline-block;
		width: 70%;
	}
</style>
</head>
<body>
	<h1>Welcome</h1>
	
	<!-- 
		form에 네이버, 다음, 구글 검색 URL을 action으로 지정하고 input의 name 속성에 각 검색용 query 변수명을 사용하면
			input에 검색어를 입력한 후 Enter를 누르면 각 검색 사이트에 데이터를 전송하고 결과를 화면에 보여준다.
			
		action 값이 http:// 또는 https:// 로 시작되는 경우는 현재의 LocalHost에 요청을 보내는 것이 아니고 외부의 URI(URL)로 전송을 하게 된다.
		
		=> 크롤링
	 -->
	
	<form action="https://search.naver.com/search.naver">
		<label>네이버 검색</label><input name="query">
	</form>
	<form action="https://search.daum.net/search">
		<label>다음 검색</label><input name="q">
	</form>
	<form action="https://www.google.com/search">
		<label>구글 검색</label><input name="q">
	</form>
</body>
</html>