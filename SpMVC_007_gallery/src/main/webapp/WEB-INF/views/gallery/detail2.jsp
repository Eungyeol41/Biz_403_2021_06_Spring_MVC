<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<style>
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
	width: 90%;
	margin: 10px auto;
	border: 1px solid cadetblue;
}

div#gallery_files div.gallery_file {
	width: 200px;
	height: 200px;
	padding: 5px;
	overflow: hidden;
	position: relative;
}

div.gallery_file:after {
	content: "";
	position: absolute;
	left: 0;
	top: 0;
	bottom: 0;
	right: 0;
	background-color: transparent;
	color: transparent;
	z-index: 10;
	transition: 1s;
	padding: auto;
	line-height: 200px;
}

div.gallery_file:hover:after {
	content: '\f2ed';
	background-color: rgba(0, 0, 0, 0.7);
	color: white;
	text-align: center;
	cursor: pointer
}

div#gallery_files img {
	padding: 20px;
	width: 100%;
	height: 100%;
}

a {
	text-decoration: none;
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

div#gallery_botton_box button:nth-of-type(1) {
	background-color: blue;
	color: white;
}

div#gallery_botton_box button:nth-of-type(2) {
	background-color: red;
	color: yellow;
}
</style>
<div id="gallery_box">
	<div>
		<c:if test="${empty GALLERY.g_image}">
			<img src="${rootPath}/files/noImage.png" alt="main_image"
				width="200px">
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
			<img src="${rootPath}/files/noImage.png" width="100px">
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

<script>
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

	/*
		const : JS에서 상수 선언하기
		다른 언어에서는 상수선언이 메모리적 문제를 해결하고 동시성처리(멀티 환경에서 서로 변수가 간섭하는 현상을 핸들링)를 쉽게 하기위한 방안으로 사용한다
		
		상수를 선언하는 이유
		여기에 설정된 값이 코드 중간에 어떤 이유로 변경되는 것을 방지하는 역할
		
		1개의 선언된 변수에 코드 중간에 다른 값이 저장되어(의도하든 그렇지 않든) 논리적인 오류를 일으킬 수 있다.
		그러한 문제를 방지하기 위하여 const 키워드를 상당히 권장
	*/
	const gallery_files = document.querySelector("div#gallery_files")
	if(gaellery_files) {
		gallery_files.addEventListener("click", (e)=> {
			let tag = e.target
	
			/*
				tag에 걸려있는 class 이름을 챙겨서 조건을 걸 때
				tag.className === "gallery_file"과 같이 사용할 수 있지만
				혹시 tag에 다수의 클래스가 설정될 수 있기 때문에 조건이 false가 될 수 있다
				
				className.includes() 함수를 사용하여 조건 검사를 하는 것이 좋다
			*/
			
			if(tag.tagName === "DIV" && tag.tagName.includes("gallery_file")) {
				let seq = tag.dataset.fseq
				if(confirm(seq + "이미지 삭제")) {
					
					// GET method 방식으로 Ajax 요청
					fetch("${rootPath}/gallery/file/delete/" + seq)
					.then(response=>response.text())
					.then(result=> {
						if(result === "OK") {
							alert("삭제 성공")
							tag.remove()
						} else if(result === "FAIL_SEQ") {
							alert("이미지 코드가 잘못되어 삭제할 수 없음")
						} else if(result === "NONE") {
							alert("Server가 알 수 없음")
						} else {
							alert("삭제 실패")
						}
					})
				}
			}
		})
	}
</script>