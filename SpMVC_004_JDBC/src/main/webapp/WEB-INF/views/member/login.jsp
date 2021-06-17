<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
form#login_form {
	width: 400px;
	padding: 40px;
	margin: 70px auto;
	background-color: lightsteelblue;
	text-align: center;
	border-radius: 13px;
}

form#login_form h2 {
	color: white;
	font-weight: 500;
}

form#login_form input {
	border: 0;
	outline: 0;
	display: block;
	width: 200px;
	margin: 20px auto;
	padding: 14px 10px;
	color: white;
	border-radius: 15px;
	border: 1px solid #3498db;
	text-align: center;
	transition: 0.3s;
}

form#login_form input:focus {
	width: 280px;
	border-color: lightcyan;
}

form#login_form button {
	border: 0;
	outline: none;
	background: none;
	display: block;
	margin: 20px auto;
	text-align: center;
	border: 1px solid lightcyan;
	color: white;
	border-radius: 15px;
	cursor: pointer;
}

form#login_form button.btn_join {
	background-color: lightsteelblue;
}

form#login_form button:hover {
	background-color: lightsteelblue;
}

form#login_form div.msg {
	margin: 0 auto;
	background-color: red;
	color: yellow;
	font-size: 10px;
	border-radius: 15px;
}
</style>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<form id="login_form" method="POST">
		<h2>로그인</h2>
		<div class="msg">${MSG}</div>
		<input name="m_username" id="m_username" placeholder="사용자 ID" /> 
		<input type="password" id="m_password" name="m_password" placeholder="P.W." />
		<button type="button" class="btn_login">로그인</button>
		<button type="button" class="btn_join">회원가입</button>
	</form>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
</body>
<script>

	// if("${MSG}" === "NONE") {
	//  	document.querySelector("div.msg").style.display = "NONE";
	// }
	
	document.querySelector("div.msg").style.display = "${MSG}";

	document.querySelector("button.btn_join").addEventListener("click", ()=> {
		location.href="${rootPath}/member/join";	
	});
	
	document.querySelector("button.btn_login").addEventListener("click", ()=> {
		
		let username = document.querySelector("input#m_username")
		let password = document.querySelector("input#m_password")
		
		/*
			view 단에서 입력 유효성 검사하기
			값이 입력되었는가를 검사하기
			입력되지 않으면 alert를 보이고 입력 box에 focus 추가
		*/
		
		if(username.value === "") {
			alert("사용자 ID를 입력하세요");
			username.focus();
			return false;
		}
		
		if(password.value === "") {
			alert("비밀번호를 입력하세요");
			username.focus();
			return false;
		}
		
		// 유효성 검사를 통과하면 서버로 전송하기
		document.querySelector("form#login_form").submit();
		
	});
</script>
</html>