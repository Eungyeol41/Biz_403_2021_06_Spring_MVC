<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf" %>
<style>
	form {
		width: 80%;
		margin: 15px auto;
	}
	
	fieldset {
		border: 1px solid green;
		border-radius: 15px;
	}
	
	legend {
		text-align: center;
		padding: 10px 20px;
		background-color: lightblue;
	}
	
	form div {
		width: 80%;
		margin: 5px auto;
	}
	
	form label {
		display: inline-block;
		width: 20%;
		text-align: right;
		margin: 8px 5px;
		padding: 8px;
		font-weight: bold;
	}
	
	form input {
		width: 50%;
		margin: 4px 5px;
		padding: 8px;
		border-radius: 15px;
		outline: 0;
		border-color: lightblue;
	}
	form input:focus {
		border-color: aquamarine;
	}
	
	form input:hover {
		background-color: gainsboro;
	}
	
 </style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf" %>
	
	<form action="" method="POST">
		<fieldset>
			<legend>도서정보 등록</legend>
			<div>
				<label>ISBN</label>
				<input name="bk_isbn" id="bk_isbn""" placeholder="">
			</div>
			<div>
				<label>도서명</label>
				<input name="bk_title""" id="bk_title""" placeholder="">
			</div>
			<div>
				<label>출판사</label>
				<input name="bk_ccode""" id="bk_ccode""" placeholder="">
			</div>
			<div>
				<label>저자</label>
				<input name="bk_acode""" id="bk_acode""" placeholder="">
			</div>
			<div>
				<label>출판일</label>
				<input name="bk_date""" id="bk_date""" placeholder="">
			</div>
			<div>
				<label>가격</label>
				<input name="bk_price""" id="bk_price""" placeholder="">
			</div>
			<div>
				<label>페이지 수</label>
				<input name="bk_pages""" id="bk_pages""" placeholder="">
			</div>
		</fieldset>
		<div class="btn_box">
			<button type="button" class="btn_book_insert">도서 등록</button>
			<button type="reset" class="btn_book_reset">새로 작성</button>
			<button type="button" class="btn_book_list">List로</button>
		</div>
	</form>
	
	
	
	<%@ include file="/WEB-INF/views/include/include_footer.jspf" %>
</body>
<script>
	document.querySelector("button.btn_book_insert").addEventListener("click", ()=> {
		location.href="${rootPath}/books/insert";	
	})
</script>
</html>