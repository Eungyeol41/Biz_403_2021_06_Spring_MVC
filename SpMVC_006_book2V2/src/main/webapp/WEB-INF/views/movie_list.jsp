<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${MOVIES}" var="MOVIE">
	<div class="content">
		<img src="${MOVIE.image}">
		<div>
			<p class="title">
				<a href="${MOVIE.link}" target="_NEW">${MOVIE.title}</a>
			</p>
			<br />
			<p class="subtitle">
				<strong>영문 제목 : </strong>${MOVIE.subtitle}
			</p>
			<br />
			<p class="director">
				<strong>감독 : </strong> ${MOVIE.director}
			</p>
			<br/>
			<p class="actor">
				<strong>배우 : </strong> ${MOVIE.actor}
			</p>
			<br />
			<button class="insert">영화 등록</button>
		</div>
	</div>
</c:forEach>
