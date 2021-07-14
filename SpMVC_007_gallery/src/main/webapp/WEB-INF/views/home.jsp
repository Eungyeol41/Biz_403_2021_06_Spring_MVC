<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Gallery</title>
<script src="https://kit.fontawesome.com/bf824ab718.js" crossorigin="anonymous"></script>
<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	body {
		background: linear-gradient(#e0eafc, #cfdef3)
	}

	h1 {
		text-align: center;
	    color: cadetblue;
	    padding: 5px;
	    margin: 10px auto;
	   	text-shadow: 3px 3px 3px white;
	}
	
	h1:hover {
		cursor: progress;
	}
	
</style>
</head>
<body>
	<header>
		<h1 id="mg">My Gallery</h1>
	</header>
	
	<%@ include file="/WEB-INF/views/include/nav.jspf" %>
	
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
		</c:when>
		
		<c:when test="${BODY eq 'G-DETAILV2'}">
			<%@ include file="/WEB-INF/views/gallery/detail2.jsp" %>
		</c:when>
		
		<c:when test="${BODY eq 'JOIN'}">
			<%@ include file="/WEB-INF/views/member/join.jsp" %>
		</c:when>
		
		<c:when test="${BODY eq 'LOGIN'}">
			<%@ include file="/WEB-INF/views/member/login.jsp" %>
		</c:when>
		
		<c:otherwise>
			<a href="${rootPath}/gallery/input">이미지등록</a>
		</c:otherwise>
	</c:choose>

	<c:forEach items="${FILES}" var="FILE">
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