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
		width: 50%;
	}
	
	legend {
		text-align: center;
		color: cadetblue;
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
</style>


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
<div id="gallery_files">
	<c:forEach items="${GALLERY.fileList}" var="FILE">
		<c:if test="${empty FILE.file_upname}">
			<img src="${rootPath}/files/noImage.png" width="100px" >
		</c:if>
		<c:if test="${not empty FILE.file_upname}">
			<img src="${rootPath}/files/${FILE.file_upname}" height="100px">
		</c:if>
	</c:forEach>
</div>
<div>
	<button class="gallery update">수정</button>
	<button class="gallery delete">삭제</button>
</div>




<script type="text/javascript">
	let btn_update = document.querySelector("button.gallery.update")
	let btn_delete = document.querySelector("button.gallery.delete")
	
	btn_update.addEventListener("click", ()=> {
		alert("일련번호가 ${GALLERY.g_seq}인 게시물 수정")
		location.href = "${rootPath}/gallery/update?g_seq=${GALLERY.g_seq}"
	})
	
	btn_delete.addEventListener("click", ()=> {
		if(confirm("일련번호가 ${GALLERY.g_seq}인 게시물 삭제??")) {
			// .replace -- 뒤로가기 안 됨..
			location.replace("${rootPath}/gallery/delete?g_seq=${GALLERY.g_seq}")
		}
	})
</script>