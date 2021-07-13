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
	
	div.msg {
		font-size: 12px;
		color: red;
		padding: 0.5rem;
	}
	
	form {
	    text-align: center;
	}
	
	label {
		display: inline-block;
	    width: 20%;
	    text-align: right;
	    margin-right: 5%;
	}
	
	input {
		border: 0;
    	border-bottom: 1px solid cadetblue;
		background-color: transparent;
	}
	
	input {
    	width: 20%;
	}
	
	input:hover {
		background-color: aliceblue;
	}
	
	button {
		border: none;
	    background-color: lightblue;
	    width: 10%;
	}
	
	button:hover {
		cursor: pointer;
	}
	
</style>
<form method="POST">
	<div>
		<label>사용자 ID(E_mail)</label>
		<input name="m_userid" type="email">
		<div class="msg join id"></div>
	</div>
	
	<div>
		<label>비밀번호</label>
		<input name="m_password" type="password">
	</div>
	
	<div>
		<label>비밀번호 확인</label>
		<input name="re_password" type="password">
	</div>
	
	<div>
		<label>닉네임</label>
		<input name="m_nick">
	</div>
	<div>
		<label>전화번호</label>
		<input name="m_tel">
	</div>
	<div>
		<!-- button의 type 지정하지 않을 시 script 사용하지 않음 -->
		<button>가입 신청</button>
	</div>
</form>

<script>
	// let user_id = document.querySelector("input#user_id")
	let user_id = document.querySelector("input[name='m_userid']")
	let msg_user_id = document.querySelector("div.join.id")
	
	if(user_id) {
		/*
			lost focus
			input tag에 입력하는 도중 다른 tag로 focus가 이동되는 경우
			
			blur, focusout event 코드에서
			
			alert를 사용하면 lost focus와 alert 사이에서 무한반복이 일어나는 현상 발생..
			lost focus가 되었을 대 메시지를 사용자에게 보이고 싶을 때는 alert를 사용하지 않고 다른 방법을 강구해야 함.
			
			현재 코드는 비어있는 div box를 하나 만들고 그 곳에 메시지를 표시하는 방법을 사용한 것
		*/
		user_id.addEventListener("blur", (e)=> {
			
			let m_userid = e.currentTarget.value
			
			msg_user_id.innerText = ""
			msg_user_id.style.padding = "0"
			
			/*
				m_userid box에 사용자 ID를 입력한 상태로 Tab 키를 누르거나, 다른 값을 입력하기 위하여 focus를 이동하면
				m_userid box에 입력된 값으로 ID 중복검사 수행하기
			*/
			if(m_userid === "") {
				msg_user_id.innerText = " * 사용자 ID는 반드시 입력하세요 *"
				msg_user_id.style.color = "red";
				msg_user_id.style.padding = "5px";
				user_id.focus()
				return false;
			}
			
			// fetch는 location.href와 유사
			fetch("${rootPath}/member/id_check?m_userid=" + m_userid)
			.then(response=>response.text())
			.then(result=> {
				if(result === "USE_ID") {
					msg_user_id.innerText = " * 이미 가입된 ID입니다 * "
					msg_user_id.style.color = "red";
					user_id.focus()
					return false;
				} else if(result === "NOT_USE_ID") {
					msg_user_id.innerText = " * 가입가능한 ID입니다 * "
					msg_user_id.style.color = "blue"
					document.querySelector("input[name='m_password']").focus()
				} else {
					msg_user_id.innerText = " * 알 수 없는 결과입니다 * "
					msg_user_id.style.color = "red";
				}
			}) 
			/*
				.then( (response)=> {
					return response.text()
				})
			*/			
		})
	}
</script>