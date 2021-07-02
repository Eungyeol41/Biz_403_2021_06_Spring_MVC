<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My HomePage</title>
<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	body {
		display: flex;
		flex-direction: column;
		height: 100vh;
	}
	
	form input {
		
	}
	
	section.content_box {
		border: 1px solid green;
		padding: 12px 16px;
		display: flex;
		flex-wrap: wrap;
		
		/*
			검색 결과가 표시되는 영역은 scroll
			상단의 검색창(nav) 화면에 고정하기
			
			1. body에 display: flex;	flex-direction: column;		height: 100vh;
			2. 검색 결과 창에 flex: 1;	overflow: auto;
		*/
		flex: 1;
		overflow: auto;
	}
	
	section.content_box div.content {
		display: flex;
		border: 1px solid blue;
		margin: 50px auto;
		width: 30%;
		height: 30vh;
		overflow: hidden;
	}
	
	section.content_box div.content img {
		flex: 1;
		width: 30%;
	}
	
	section.content_box div.content div {
		flex: 2;
		margin: 5px;
	}
	
	@media ( min-width : 1200px) {
		section.content_box div.content {
			width: 20%;
		}
	}
	
	a {
		text-decoration: none;
	}
	
	a:hover {
		color: lightskyblue;
	}
	
	p b {
		color: blue;
	}
	
	nav#main_nav {
		background-color: #00C43B;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	nav#main_nav form {
		margin: 0.6rem;
		width: 80%;
	}
	
	nav#main_nav input {
		padding: 8px;
		border: 0;
		outline: 0;
		width: 100%;
		border-radius: 5px;
	}
	
	nav#main_nav select {
		margin-left: auto;
		padding: 8px;
		width: 10%;
		border-radius: 10px;
		text-align-last: center;
	}
	
	nav#main_nav select option {
		text-align: center;
	}
</style>
</head>
<body>
	<nav id="main_nav">
		<c:if test="${CAT == 'BOOK'}">
			<c:set var="pHolder" value="도서 검색어" />
		</c:if>
		<c:if test="${CAT == 'MOVIE'}">
			<c:set var="pHolder" value="영화 검색어" />
		</c:if>
		<c:if test="${CAT == 'NEWS'}">
			<c:set var="pHolder" value="뉴스 검색어" />
		</c:if>
		
		<select name="category">
			<option value="BOOK" <c:if test="${CAT == 'BOOK'}">selected="selected"</c:if >>도서검색</option>
			<option value="MOVIE" <c:if test="${CAT == 'MOVIE'}">selected="selected"</c:if>>영화검색</option>
			<option value="NEWS" <c:if test="${CAT == 'NEWS'}">selected="selected"</c:if>>뉴스검색</option>
		</select>
		<form>
				<input name="search" placeholder="${pHolder} 입력 후 Enter">
			</form>
	</nav>
	<section class="content_box">
		<%@ include file="/WEB-INF/views/book/book_list.jsp" %>
		<%@ include file="/WEB-INF/views/movie_list.jsp" %>
		<%@ include file="/WEB-INF/views/news_list.jsp" %>
		
		<c:if test="${not empty MYBOOKS}">
			<%@ include file="/WEB-INF/views/book/list_view.jsp" %>
		</c:if>
		
	</section>
</body>
<script>
	let category = document.querySelector("select[name='category']")
	category.addEventListener("change", (e)=> {
		
		let value = category.value
		// alert(value);
		// location.href = "${rootPath}/?category=" + value;
		location.href = "${rootPath}/naver/" + value;
		
	})
</script>
</html>