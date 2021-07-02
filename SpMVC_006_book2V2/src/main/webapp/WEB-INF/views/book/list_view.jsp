<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List_view</title>
<style>

	table {
		border: 0;
		width: 90%;
		border-collapse: collapse;
		border-spacing: 0;
		margin: 10px auto;
		text-align: center;
	}
	
	tr {
		border-top: 1px solid green;
	}
	
	table tr:last-child {
		border-bottom: 1px solid green;
	}
	
</style>
</head>
<body>
	<h1>내 서재</h1>
	<table id="my_books">
		<tr>
			<td>이미지</td>
			<td>ISBN</td>
			<td>제목</td>
			<td>저자</td>
			<td>출판사</td>
			<td>출간일</td>
		</tr>
		<c:choose>
		
			<c:when test="${empty MYBOOKS}">
				<tr>
					<td colspan="6">데이터가 없음</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${MYBOOKS}" var="BLIST">
					<tr data-isbn="${BLIST.isbn}">
						<td><img src="${BLIST.image}"></td>
						<td>${BLIST.isbn}</td>
						<!-- <td class="book_title"><a href="${BLIST.link}" target="_NEW">${BLIST.title}</a></td> -->
						<td>${BLIST.title}</td>
						<td>${BLIST.author}</td>
						<td>${BLIST.publisher}</td>
						<td>${BLIST.pubdate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
			
		</c:choose>
	</table>
	<script>
		let my_books = document.querySelector("table#my_books")
		if(my_books) {
			my_books.addEventListener("click", (e)=> {
				let td = e.target
				if(td.tagName === "TD") {
					let tr = td.closest("TR")
					let isbn = tr.dataset.isbn
					alert(isbn)
				}
			})
		}
	</script>
</body>
</html>