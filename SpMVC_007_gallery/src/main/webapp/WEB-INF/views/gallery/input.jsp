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
	
	div {
		margin: 5px;
	}
	
	fieldset {
		border-color: cadetblue;
		margin: 0 auto;
		width: 50%;
	}
	
	legend {
		text-align: center;
		color: cadetblue;
	}

	label {
		display: inline-block;
	    width: 20%;
	    text-align: right;
	    margin-right: 5%;
	}
	
	input, textarea {
		border: 0;
    	border-bottom: 1px solid cadetblue;
		background-color: transparent;
	}
	
	input {
    	width: 20%;
	}
	
	textarea {
		resize: none;
	}

	input:hover, textarea:hover {
		background-color: aliceblue;
	}
	
	input.file {
		background-color: none;
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

<form method="POST" enctype="multipart/form-data">
	<fieldset>
		<legend>Gallery 등록하기</legend>
		<div>
			<label>작성자</label>
			<input name="g_writer" value="${CMD.g_writer}">
		</div>
		<div>
			<label>작성일자</label>
			<input type="date" name="g_date" value="${CMD.g_date}">
		</div>
		<div>
			<label>작성시각</label>
			<input type="time" name="g_time" value="${CMD.g_time}">
		</div>
		<div>
			<label>제목</label>
			<input name="g_subject" value="${CMD.g_subject}">
		</div>
		<div>
			<label></label>
			<textarea cols="24" rows="5" name="g_content"></textarea>
		</div>
		
		<div>
			<label>대표이미지</label>
			<input class="file" type="file" name="one_file" />
		</div>
		<div>
			<label>갤러리</label>
			<input class="file" type="file" multiple="multiple" name="m_file" />
		</div>
		
		<button>등록</button>
	</fieldset>
</form>