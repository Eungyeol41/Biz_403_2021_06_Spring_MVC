<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Gallery</title>
<style>
	body {
		background: linear-gradient(#e0eafc, #cfdef3)
	}

	h1 {
		text-align: center;
	    color: cadetblue;
	    padding: 5px;
	    margin: 10px auto;
	}
	
	h1:hover {
		cursor: progress;
	}
</style>
</head>
<body>
	<h1 id="mg">My Gallery</h1>

	<c:choose>
		<c:when test="${BODY eq 'G-INPUT'}">
			<%@ include file="/WEB-INF/views/gallery/input.jsp"%>
		</c:when>
		<c:when test="${BODY eq 'G-LIST'}">
			<%@ include file="/WEB-INF/views/gallery/list.jsp" %>
			<a href="${rootPath}/gallery/input">이미지등록</a>
		</c:when>
		<c:when test="${BODY eq 'G-DETAIL'}">
			<%@ include file="/WEB-INF/views/gallery/detail.jsp" %>
			<a href="${rootPath}/gallery">리스트로</a>
		</c:when>
		<c:otherwise>
			<a href="${rootPath}/gallery/input">이미지등록</a>
		</c:otherwise>
	</c:choose>

	<c:forEach
		items="${FILES}"
		var="FILE">
		<a href="${rootPath}/files/${FILE}" target="_NEW"></a>
		<img src="${rootPath}/files/${FILE}" width="200px" height="200px">
	</c:forEach>
	<!-- <img src="${rootPath}/files/title.jpg" width="200px" height="200px"> -->
</body>
<script>
	document.querySelector("h1#mg").addEventListener("click", ()=> {
		location.href = "${rootPath}/"
	})
</script>
</html>