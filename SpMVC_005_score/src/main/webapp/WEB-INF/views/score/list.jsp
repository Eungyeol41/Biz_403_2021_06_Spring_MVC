<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
/*
	button {
		background-color: #f6ecc4
	}
*/
</style>
<h2>성적 리스트</h2>
<table class="score_list detail">
	<tr>
		<th>학번</th>
		<th>이름</th>
		<th>과목코드</th>
		<th>과목명</th>
		<th>점수</th>
	</tr>
	<c:choose>
		<c:when test="${empty SCLIST}">
			<tr>
				<td colspan="5">데이터가 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${SCLIST}" var="SC">
				<tr data-stnum="${SC.sc_stnum}">
					<td>${SC.sc_stnum}</td>
					<td>${SC.sc_stname}</td>
					<td>${SC.sc_sbcode}</td>
					<td>${SC.sc_sbname}</td>
					<td class="number">${SC.sc_score}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div class="btn_box">
	<button class="score insert">성적 등록</button>
	<button class="score student list">학생정보 바로가기</button>
</div>
<script>
/*
	document.querySelector("table.score_list").addEventListener("click", (e)=> {
		
		let target = e.target
		let tagName = target.tagName
		
		if(tagName === "TD") {
			let tr = target.closest("TR")
			let stNum = tr.dataset.stnum
			
			location.href = "${rootPath}/score/detail?st_num=" + stNum
		}
		
	})
*/	
</script>