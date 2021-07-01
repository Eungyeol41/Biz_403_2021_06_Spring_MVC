<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${BOOKS}" var="BOOK">
	<div class="content">
		<img src="${BOOK.image}">
		<div>
			<p class="title">
				<a href="${BOOK.link}" target="_NEW">${BOOK.title}</a>
			</p>
			<br />
			<!-- <p class="desc">${BOOK.description}</p> -->
			<br />
			<p class="author">
				<strong>저자 : </strong>${BOOK.author}
			</p>
			<br />
			<p class="publisher">
				<strong>출판사 : </strong> ${BOOK.publisher}
			</p>
			<br />
			<a href="${rootPath}/book/insert/${BOOK.isbn}">책 등록</a>
			<!-- <button class="insert">책 등록</button> -->
		</div>
	</div>
</c:forEach>
