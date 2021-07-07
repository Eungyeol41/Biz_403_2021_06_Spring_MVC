<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>
	div#gallery_info {
	    margin: 5%;
	}
	
	fieldset {
		border-color: cadetblue;
		margin: 0 auto;
		width: 50%;
	}
	
	legend {
		text-align: center;
		color: cadetblue;
	}
	
</style>

<div id="gallery_info">
	<fieldset>
		<legend> 제목 : ${GFLIST[0].g_subject} </legend>
		<p>작성일 : ${GFLIST[0].g_date}</p>
		<p>작성시각 : ${GFLIST[0].g_time}</p>
		<p>작성자 : ${GFLIST[0].g_writer}</p>
		<p>작성 내용 : ${GFLIST[0].g_content}</p>
	</fieldset>
</div>

<style>
	div#gallery_files {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
	}
	
	div#gallery_files img {
	    padding: 20px;
	}
	
	a {
		text-decoration: none;
	}
</style>

<div id="gallery_files">
	<c:forEach items="${GFLIST}" var="GF">
		<img src="${rootPath}/files/${GF.f_upname}" height="100px">
	</c:forEach>
</div>