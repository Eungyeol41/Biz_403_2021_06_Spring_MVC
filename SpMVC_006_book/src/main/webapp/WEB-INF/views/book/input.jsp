<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<c:set
	var="rootPath"
	value="${pageContext.request.contextPath}" />
<form method="POST">
	<div>
		<label>ISBN</label>
		<input name="isbn">
	</div>

	<div>
		<label>도서명</label>
		<input name="title">
	</div>

	<div>
		<label>저자</label>
		<input name="author">
	</div>

	<div>
		<label>출판사</label>
		<input name="publisher">
	</div>

	<div>
		<label>요약 정보</label>
		<input name="description">
	</div>

	<div>
		<label>출판일</label>
		<input name="pubdate">
	</div>

	<div>
		<label>정가</label>
		<input name="price">
	</div>

	<div>
		<label>할인</label>
		<input name="discount">
	</div>

	<div>
		<label>네이버 도서 정보</label>
		<input name="link">
	</div>

	<div>
		<label>이미지</label>
		<input name="image">
	</div>

</form>