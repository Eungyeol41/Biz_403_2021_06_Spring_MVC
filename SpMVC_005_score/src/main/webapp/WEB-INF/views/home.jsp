<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale: 1;">
<title>My HomePage</title>
<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	body {
		/* viewpoint의 width만큼 */
		width: 100wv;
		height: 100vh;
		display: flex;
		flex-direction: column;
	}
	
	header {
		height: 30vh;
		background: url("${rootPath}/static/images/header_01.jpg") no-repeat;
		background-size: 100% 100%;
		background-position: top;
		background-attachment: fixed;
		text-align: center;
		text-shadow: 1px 1px 1px black;
		color: white;
		padding: 2rem;
	}
	
	nav {
		background-color: black;
		color: white;
		width: 100wv;
	}
	
	nav.fixed {
		position: fixed;
		top: 0;
		left: 0;
		right: 10px;
		border-bottom-right-radius: 20px;
		box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
	}
	
	nav ul {
		list-style: none;
		display: flex;
		margin: 0 20px;
	}
	
	nav li {
		padding: 16px 12px;
		border-bottom: 3px solid transparent;
		transition: 0.5s;
	}
	
	nav li:hover {
		border-bottom: 3px solid gold;
		cursor: pointer;
	}
	
	nav li:nth-of-type(2) {
		margin-left: auto;
	}
	
	section#main_sec {
		flex: 1;
		width: 100wv;
		display: flex;
		flex-direction: column;
		background-size: 100% 100%;
		background-attachment: fixed;
		background-image: linear-gradient(to top, #d9afd9 0%, #97d9e1 100%);
		/* 
				overflow: auto;
				
				header와 nav를 화면에 고정하고 data가 보이는 부분만 scroll하기 위함.
			 */
	}
	
	table {
		border: 0;
		width: 90%;
		border-collapse: collapse;
		border-spacing: 0;
		margin: 10px auto;
	}
	
	tr {
		border-top: 1px solid green;
	}
	
	tr:last-child {
		border-botton: 1px solid green;
	}
	
	tr:nth-of-type(odd) {
		background-color: #ccc;
	}
	
	tr:nth-of-type(even) {
		background-color: #eee;
	}
	
	tr:hover {
		background-color: #ccc;
	}
	
	tr:hover {
		background-color: #aaa;
	}
	
	td, th {
		border-right: 1px solid green;
		padding: 8px 12px;
	}
	
	td:last-child, th:last-child {
		border: none;
	}
	
	div.btn_box {
		width: 90%;
		/* table의 margin: 10px auto;로 설정되있기 때문에 margin-top: 0, magin-bottom: 10px, margin-left, margin-right: auto로 설정 */
		margin: 0px auto 10px auto;
		padding: 5px;
		text-align: right;
	}
	
	div.btn_box button {
		border: 0;
		outline: 0;
		padding: 12px 16px;
		margin-left: 10px;
		border-radius: 5px;
	}
	
	button {
		background-image: linear-gradient(to top, #c1dfc4 0%, #deecdd 100%);
	}
	
	button:hover {
		box-shadow: 2px 2px 2px 2px black;
		cursor: wait;
	}
	
	form {
		width: 90%;
		margin: 0 auto 10px auto;
	}
	
	fieldset {
		background-color: #edf1f4;
		border: 1px solid gold;
		border-radius: 10px;
		padding: 0.7rem;
	}
	
	fieldset label {
		display: inline-block;
		margin: 5px;
		padding: 5px;
	}
	
	form label {
		width: 30%;
		text-align: right;
	}
	
	form input {
		width: 60%;
		outline: 0;
		border: #aaa;
		border-radius: 10px;
	}
</style>
</head>
<body>
	<header>
		<h1>대한고교 성적처리</h1>
		<p>대한고교 성적처리 시스템 2021 V1</p>
	</header>

	<nav id="main_nav">
		<ul>
			<li>HOME</li>
			<li>Login</li>
			<li>Logout</li>
			<li>관리자</li>
		</ul>
	</nav>

	<section id="main_sec">
		<c:choose>
			<c:when test="${BODY == 'SCORE_VIEW'}">
				<%@ include file="/WEB-INF/views/score/list.jsp"%>
			</c:when>
			<c:when test="${BODY == 'STUDENT_LIST'}">
				<%@ include file="/WEB-INF/views/student/list.jsp"%>
			</c:when>
			<c:when test="${BODY == 'STUDENT_INPUT'}">
				<%@ include file="/WEB-INF/views/student/input.jsp"%>
			</c:when>
			<c:when test="${BODY == 'STUDENT_DETAIL'}">
				<%@ include file="/WEB-INF/views/student/detail.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/main.jsp"%>
			</c:otherwise>
		</c:choose>
	</section>
</body>
<script>
/*
 * JS 코드에서 event를 등록할 때 현재 화면에 없는 DOM 요소에 addEvent를 설정하면 null 오류가 발생
 	그 이유는 현재 화면에 없는 DOM 요소를 querySelector하면 그 결과값이 null이기 때문에 발생하는 문제이다
 	
 	JS 코드를 통합하여 모음으로 관리할 때는 addEvent를 하려고 하는 요소가 있는 지를 먼저 검사한 후 addEvent를 수행해주어야 한다.
 */
	let st_list = document.querySelector("button.student.list")
	let st_insert = document.querySelector("button.student.insert")
	let home = document.querySelector("button.student.home")
	
	if(st_list) {
		st_list.addEventListener("click", (e)=> {
			location.href = "${rootPath}/student";
		})
	}
	
	if(st_insert) {
		st_insert.addEventListener("click", (e)=> {
			location.href = "${rootPath}/student/insert"
		})
	}
	
	if(home) {
		home.addEventListener("click", (e)=> {
			location.href = "${rootPath}/";
		})
	}
	
	let table = document.querySelector("table.detail")
	
	if(table != null) {
		table.addEventListener("click", (e)=> {
			
			let target = e.target
			let tagName = target.tagName
			
			if(tagName === "TD") {
				let tr = target.closest("TR")
				let stNum = tr.dataset.stnum
				
				location.href = "${rootPath}/student/detail?st_num=" + stNum
			}
		})
	}
	
	let main_nav = document.querySelector("nav#main_nav")
	let main_header = document.querySelector("header")
	let main_header_height = main_header.offsetHeight;
	
	document.addEventListener("scroll", ()=> {
		let doc_bound = document.querySelector("HTML").getBoundingClientRect();
		let doc_top = doc_bound.top
		
		/*
			화면이 아래방향으로 scroll될 때 화면 문서의 top 좌표를 추출하여 header box의 높이와 비교
			
			header box의 높이에 -1을 곱하고 그 값보다 작아지면 (== header box가 화면에서 사라지면) nav에 fixed라는 class 부착하고
			header box가 화면에서 나타나면 nav에 fixed class를 제거하여 원래 모습으로 다시 보이기
		*/
		if(doc_top < main_header_height * -1) {
			main_nav.classList.add("fixed")
		} else {
			main_nav.classList.remove("fixed")
		}
	});
	
		

</script>
</html>