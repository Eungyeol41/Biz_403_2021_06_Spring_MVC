<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comp input</title>
</head>
<body>
	<h1>출판사 정보 등록</h1>
	<form method="POST">
		<!-- <div><label>출판사코드</label><input name="cp_code"></div> -->
		<div><label>출판사명</label><input name="cp_title"></div>
		<div><label>대표자명</label><input name="cp_ceo"></div>
		<div><label>전화번호</label><input name="cp_tel"></div>
		<div><label>주소</label><input name="cp_addr"></div>
		<div><button>저장</button></div>
	</form>
	<div>
		<label>삭제할 코드</label>
		<input id="cpcode" name="cpcode">
		<button class="btn_delete">삭제</button>
	</div>
	<script>
		// 상수를 선언하는 keyword
		// 		코드가 진행되는 동안 값이 변경되면 안 되는 것
		const doc = document
		doc.querySelector("button.btn_delete").addEventListener("click", (e) => {
			// id 지정하지 않고 사용할 때
			// doc.querySelector("input[name='cpcode']")
			// input에 id 지정해서 사용할 때
			const cpcodeObj = doc.querySelector("input#cpcode")
			let cpcode = cpcodeObj.value
			
			if(confirm(cpcode + "를 삭제합니다")) {
				location.replace("${rootPath}/comp/delete?cpcode=" + cpcode)
			}
			
			alert("삭제 버튼 클릭 " + cpcode)
		})
	</script>
</body>
</html>