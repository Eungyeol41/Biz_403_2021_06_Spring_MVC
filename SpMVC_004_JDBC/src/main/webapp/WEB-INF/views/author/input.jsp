<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Author input</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include_header.jspf"%>

	<form method="POST">
		<fieldset>
			<legend>저자 정보 등록</legend>
			<div>
				<label>저자코드</label><input name="au_code" id="au_code">
			</div>
			<div>
				<label>저자명</label><input name="au_name" id="au_name">
			</div>
			<div>
				<label>전화번호</label><input type="tel" name="au_tel" id="au_tel">
			</div>
			<div>
				<label>주소</label><input name="au_addr" id="au_addr">
			</div>
			<div>
				<label>주요장르</label><input name="au_genre" id="au_genre">
			</div>
			<div class="btn_box">
				<button type="button" class="btn_save">저자 등록</button>
				<button type="reset" class="btn_reset">새로 작성</button>
				<button type="button" class="btn_list">List로</button>
			</div>
		</fieldset>
		<fieldset>
			<div>
				<label>삭제할 코드</label><input id="aucode" name="aucode">
				<button type="button" class="btn_delete" style="margin-left: 30px;">삭제</button>
			</div>
		</fieldset>
	</form>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>

</body>
<script>
	document.querySelector("button.btn_author_insert").addEventListener("click", ()=> {
		location.href="${rootPath}/author/insert";	
	})
	document.querySelector("button.btn_list").addEventListener("click", ()=> {
		location.href="${rootPath}/author"
	})

	const doc = document
	doc.querySelector("button.btn_delete").addEventListener("click", (e) => {
		const cpcodeObj = doc.querySelector("input#aucode")
		let cpcode = cpcodeObj.value
		
		if(confirm(cpcode + "를 삭제합니다")) {
			location.replace("${rootPath}/author/delete?aucode=" + aucode)
		}
		
		alert("삭제 버튼 클릭 " + cpcode)
	})

</script>
</html>