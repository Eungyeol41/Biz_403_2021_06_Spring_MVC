<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	form#login_form {
		text-align: center;
    	margin: 2rem auto;
	}
	
	div.login {
		margin: 2rem auto;
	}
	
	label {
		margin: 5px;
	}
	
	input {
		border: 0;
    	border-bottom: 1px solid cadetblue;
		background-color: transparent;
    	width: 15%;
	}

	input:hover {
		background-color: aliceblue;
	}
	
	button {
		border: none;
	    background-color: lightblue;
	    width: 5%;
	}
	
	button:hover {
		cursor: pointer;
	}
	
	div.msg.view {
		color: yellow;
		background-color: red;
		font-size: 20px;
		padding: 1rem;
	}
	
</style>

<form method="POST" id="login_form">
	<div class="msg login error"></div>
	<div class="login">
		<label>사용자 ID</label>
		<input name="m_userid" type="email">
	</div>
	<div class="login">
		<label>비밀번호</label>
		<input name="m_password" type="password">
	</div>
	<div class="login button">
		<button type="button" class="login">로그인</button>
		<button type="button" class="join">회원가입</button>
	</div>
</form>

<script>
	let form = document.querySelector("#login_form")
	let btn_login = document.querySelector("button.login")
	let btn_join = document.querySelector("button.join")
	let msg_error = document.querySelector("div.msg.login.error")
	
	let input_userid = document.querySelector("input[name='m_userid']")
	let input_password = document.querySelector("input[name='m_password']")
	
	if(btn_login) {
		btn_login.addEventListener("click", ()=> {
			
			let m_userid = input_userid.value
			let m_password = input_password.value
			
			if(m_userid === "") {
				alert("사용자 ID는 무조건 입력해야 합니다!!")
				input_userid.focus()
				return false;
			}
			if(m_password === "") {
				alert("비밀번호는 무조건 입력해야 합니다!!")
				input_password.focus()
				return false;
			}
			
			form.submit()
		})
	}
	
	if(btn_join) {
		btn_join.addEventListener("click", ()=> {
			location.href = "${rootPath}/member/join"
		})
	}
	
	let login_fail = "${LOGIN_FAIL}"
	
	if(login_fail === "NOT_USER_ID") {
		
		msg_error.innerText = " * 사용자 ID가 없습니다 * "

		msg_error.classList.add("view")
		
		// msg_error.style.fontSize = "20px";
		// msg_error.style.backgroundColor = "red";
		// msg_error.style.padding = "2rem";
		
	} else if(login_fail === "!EQ_PASS") {
		
		msg_error.innerText = " * 비밀번호가 틀렸습니다 * "
		
		msg_error.classList.add("view")
		
		// msg_error.style.fontSize = "20px";
		// msg_error.style.backgroundColor = "red";
		// msg_error.style.padding = "2rem";
		
	} else if(login_fail === "LOGIN_REQ") {
		
		msg_error.innerText = " * Login이 필요한 서비스입니다 * \r\n"
		msg_error.innerText += " * Login을 해주세요 * "
			
		msg_error.classList.add("view")
	}
	
</script>