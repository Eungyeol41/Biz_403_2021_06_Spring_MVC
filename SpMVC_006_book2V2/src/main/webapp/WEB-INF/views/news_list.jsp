<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${NEWS_LIST}" var="NEWS">
	<div class="content">
		<img src="${NEWS.image}">
		<div>
			<p class="title">
				<a href="${NEWS.link}" target="_NEW">${NEWS.title}</a>
			</p>
			<br />
			<!-- <p class="desc">${NEWS.description}</p> -->
			<br />
			<p class="author">
				<strong>저자 : </strong>${NEWS.author}
			</p>
			<br />
			<p class="publisher">
				<strong>출판사 : </strong> ${NEWS.publisher}
			</p>
			<br />
			<button class="insert">책 등록</button>
		</div>
	</div>
</c:forEach>
