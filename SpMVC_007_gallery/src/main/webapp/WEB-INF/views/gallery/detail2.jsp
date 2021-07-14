<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>
	div#gallery_info {
	    margin: 5%;
	}
	
	fieldset {
		border-color: cadetblue;
		margin: 0 auto;
		padding: 5px;
	}
	
	legend {
		text-align: center;
		color: cadetblue;
		letter-spacing: 1px;
		padding: 0 5px;
	}
	
	legend#seq {
		text-align: right;
	}
	
	div#gallery_files {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
	}
	
	div#gallery_files img {
	    padding: 20px;
	}
	
	a {
		text-decoration: none;
	}
	
	div#gallery_box {
		width: 90%;
		margin: 10px auto;
		border: 1px solid cadetblue;
		display: flex;
	}
	
	div#gallery_box div:first-of-type {
		flex: 1;
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 10px;
	}
	
	div#gallery_box div:last-of-type {
		flex: 3;
	}
	
	div#gallery_button_box {
		width: 90%;
		margin: 5px auto;
	}
	
	button {
		border: none;
		padding: 5px;
		margin: 5px;
		width: 5%;
		background-color: aliceblue;
	}
	
	button:hover {
		cursor: pointer;
	}
</style>
<div id="gallery_box">
	<div>
		<c:if test="${empty GALLERY.g_image}">
			<img src="${rootPath}/files/noImage.png" alt="main_image" width="200px">
		</c:if>
		<c:if test="${not empty GALLERY.g_image}">
			<img src="${rootPath}/files/${GALLERY.g_image}" alt="main_image">
		</c:if>
	</div>
	
	<div id="gallery_info">
		<fieldset>
			<legend> 제목 : ${GALLERY.g_subject} </legend>
			<legend id="seq">SEQ : ${GALLERY.g_seq}</legend>
			<p>작성일 : ${GALLERY.g_date}</p>
			<p>작성시각 : ${GALLERY.g_time}</p>
			<p>작성자 : ${GALLERY.g_writer}</p>
			<p>작성 내용 : ${GALLERY.g_content}</p>
		</fieldset>
	</div>
</div>
<div id="gallery_files" data-fseq="${FILE.file_seq}">
	<c:forEach items="${GALLERY.fileList}" var="FILE">
		<c:if test="${empty FILE.file_upname}">
			<img src="${rootPath}/files/noImage.png" width="100px" >
		</c:if>
		<c:if test="${not empty FILE.file_upname}">
			<img src="${rootPath}/files/${FILE.file_upname}" height="200px">
		</c:if>
	</c:forEach>
</div>
<div id="gallery_button_box">
	<button class="gallery update">수정</button>
	<button class="gallery delete">삭제</button>
</div>




<script type="text/javascript">
	let btn_update = document.querySelector("button.gallery.update")
	let btn_delete = document.querySelector("button.gallery.delete")
	
	btn_update.addEventListener("click", ()=> {
		// alert("일련번호가 ${GALLERY.g_seq}인 게시물 수정")
		/*
			location.href = "${rootPath}/gallery/update?g_seq=" + ${GALLERY.g_seq}
			${GALLERY.g_seq}가 문자열일 경우 위의 코드는 실행되지 않는다.
		*/
		
		/*
			현재 GALLERY.g_seq 값은 숫자형이다
			만약 GALLERY.g_seq 값니 S0001 등과 같이 문자열형이라면 이 코드는 JS 문법오류가 발생할 것이다
			
			WHY...?
			
			현재 작성한 코드는 JSP 코드이다
			이 코드는 Response가 될 때 HTML 코드로 변환이 되고 Script 부분도 변환이 된다.
			
			변환되는 과정에서 ${GALLERY.g_seq}는 담겨있는 문자열인 S0001만 단독으로 나타난다
			위의 코드는 "/root-context/gallery/update?g_seq=" + S0001처럼 변환이 된다
			결국 JS 코드가 실행될 때 +S0001 처럼 변환되어 변수를 찾게 된다
			그리고 S0001이라는 변수가 정의되지 않아 문법오류가 발생한다
			
			** EL tag를 사용하여 스크립트 부분에서 직접 값을 부착할 때는 DO("")를 부착하여 문법 오류를 방지하자! **
		*/
		location.href = "${rootPath}/gallery/update?g_seq=${GALLERY.g_seq}"
	})
	
	btn_delete.addEventListener("click", ()=> {
		if(confirm("일련번호가 ${GALLERY.g_seq}인 게시물 삭제??")) {
			// .replace -- 뒤로가기 안 됨..
			location.replace("${rootPath}/gallery/delete?g_seq=${GALLERY.g_seq}")
		}
	})
</script>