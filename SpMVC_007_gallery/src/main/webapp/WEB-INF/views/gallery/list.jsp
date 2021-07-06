<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>
	div.g_box {
		display: flex;
	}
	
	div.g_box div:first-of-type {
		flex: 1;
	}
	
	div.g_box div:last-of-type {
		flex: 3;
	}
</style>

<c:forEach items="${GALLERYS}" var="G">
	<div class="g_box">
		<div>
			<img src="${rootPath}/files/${G.g_image}" width="300px">
		</div>
		<div>
			<h3>${G.g_subject}</h3>
			<p>${G.g_content}</p>
		</div>
	</div>	
</c:forEach>
