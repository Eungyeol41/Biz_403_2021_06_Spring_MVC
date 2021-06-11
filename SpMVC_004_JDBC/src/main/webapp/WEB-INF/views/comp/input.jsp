<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<title>Comp - input</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>

	<form method="POST">
		<fieldset>
			<legend>출판사 정보 등록</legend>
			<div>
				<label>출판사명</label><input name="cp_title" id="cp_title">
			</div>
			<div>
				<label>대표자명</label><input name="cp_ceo" id="cp_ceo">
			</div>
			<div>
				<label>전화번호</label><input type="tel" name="cp_tel" id="cp_tel">
			</div>
			<div>
				<label>주소</label><input name="cp_addr" id="cp_addr">
			</div>
			<div class="btn_box">
				<button type="button" class="btn_save">출판사 등록</button>
				<button type="reset" class="btn_reset">새로 작성</button>
				<button type="button" class="btn_list">List로</button>
			</div>
		</fieldset>
		<fieldset>
			<div>
				<label>삭제할 코드</label><input id="cpcode" name="cpcode">
				<button type="button" class="btn_delete" style="margin-left: 30px;">삭제</button>
			</div>
		</fieldset>
	</form>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>

</body>
<script>
	document.querySelector("button.btn_save").addEventListener("click", ()=> {
		location.href="${rootPath}/comp/insert";	
	})
	document.querySelector("button.btn_list").addEventListener("click", ()=> {
		location.href="${rootPath}/comp"
	})

	const doc = document
	doc.querySelector("button.btn_delete").addEventListener("click", (e) => {
		const cpcodeObj = doc.querySelector("input#cpcode")
		let cpcode = cpcodeObj.value
		
		if(confirm(cpcode + "를 삭제합니다")) {
			location.replace("${rootPath}/comp/delete?cpcode=" + cpcode)
		}
		
		alert("삭제 버튼 클릭 " + cpcode)
	})

</script>
</html>