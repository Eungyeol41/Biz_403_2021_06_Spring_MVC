<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${NEWS_LIST}" var="NEWS">
	<div class="content">
		<div>
			<p class="title">
				<a href="${NEWS.link}" target="_NEW">${NEWS.title}</a>
			</p>
			<br />
			<p class="desc">${NEWS.description}</p>
			<br />
			<p class="link">
				<a href="${NEWS.link}">내용 자세히 보기</a>
			</p>
			<br />
			<p class="origin">
				<a href="${NEWS.originallink}">언론사 바로가기</a>
			</p>
		</div>
	</div>
</c:forEach>
