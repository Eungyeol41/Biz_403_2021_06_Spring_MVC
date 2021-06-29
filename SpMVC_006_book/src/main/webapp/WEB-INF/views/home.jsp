<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	section.content_box {
		border: 1px solid green;
		padding: 12px 16px;
		display: flex;
		flex-wrap: wrap;
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
	
	@media (min-width: 1200px) {
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
	}
	
</style>
</head>
<body>
	<nav id="main_nav">
		<form>
			<input name="search" placeholder="도서명 입력 후 Enter">
		</form>
	</nav>	
	<section class="content_box">
		<c:forEach items="${BOOKS}" var="BOOK">
			<div class="content">
				<img src="${BOOK.image}">
				<div>
					<p class="title">
						<a href="${BOOK.link}" target="_NEW">${BOOK.title}</a>
					</p>
					<br/>
					<!-- <p class="desc">${BOOK.description}</p> -->
					<br/>
					<p class="author">
						<strong>저자 : </strong>${BOOK.author}
					</p>
					<br/>
					<p class="publisher">
						<strong>출판사 : </strong>
						${BOOK.publisher}
					</p>
					<br/>
					<button class="insert">책 등록</button>
				</div>
			</div>
		</c:forEach>
	</section>
</body>
</html>