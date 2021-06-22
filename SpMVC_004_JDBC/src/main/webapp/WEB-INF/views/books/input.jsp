<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<script>
	var rootPath = "${rootPath}"
</script>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
	form#book_input input.search {
		width:30%;
	}
	
	form#book_input span.name {
		color: blue;
		font-weight: bold;
		margin-left: 10px;
	}
</style>
<script src="${rootPath}/static/js/book_input.js?ver=2021-06-21-004"></script>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section class="main_sec">
		<form id="book_input" method="POST">
			<fieldset>
				<legend>도서정보 등록</legend>
				<div>
					<label>ISBN</label> <input name="bk_isbn" id="bk_isbn"
						placeholder="">
				</div>
				<div>
					<label>도서명</label> <input name="bk_title" id="bk_title"
						placeholder="">
				</div>
				<div>
					<label>출판사</label> <input class="search" name="bk_ccode" id="bk_ccode" placeholder="">
					<span id="cp_title" class="name">출판사명</span>
				</div>
				<div>
					<label>저자</label> <input class="search" name="bk_acode" id="bk_acode" placeholder="">
					<span id="au_name" class="name">저자명</span>
				</div>
				<div>
					<label>출판일</label> <input name="bk_date" id="bk_date"
						placeholder="">
				</div>
				<div>
					<label>가격</label> <input name="bk_price" id="bk_price"
						placeholder="">
				</div>
				<div>
					<label>페이지 수</label> <input name="bk_pages" id="bk_pages" value="0"
						placeholder="">
				</div>
			</fieldset>
			<div class="btn_box">
				<button type="button" class="btn_save book">도서 등록</button>
				<button type="reset" class="btn_reset book">새로 작성</button>
				<button type="button" class="btn_list book">List로</button>
			</div>
		</form>
	</section>


	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
</body>

<script>
	/*
	 * 동적으로(fetch로 가져온 HTML) 만들어진 DOM에 event 설정하기
	 	표준 JS에서는 동적으로 생성된 tag는 document.querySelector()에 의해서 선택되지 않는다.
	 	이벤트를 document(가장 상위 DOM)에 설정하기
	 */
	document.addEventListener("click", (e)=> {
		let target = e.target;
		let tagName = target.tagName;
		
		if(parentClassName === "search_comp" && tagName == "TD") {
			
			let parentTag = target.closest("TR");
			let parentClassName = parentTag.className;
			
			/*
				table에 click event가 발생하면 가장 중심부에 있는 TD가 event를 최종 수신한다
				그러면 TD가 click되었을 때와 같은 효과를 낸다
				
				TD가 클릭되면 어떤 데이터를 가져오고 싶다
				TD는 여러개가 있어서 어떤 TD가 click될 지 모른다
				1개의 TD에만 데이터를 담아두고 그 TD만 클릭했을 때 반응하도록 하면 사용자가 상당히 불편할 것이다
				그래서 TD가 선택되면(click되면) TD를 감싸고 있는 TR이 누구인지 알아보고
					e.target.closet("TR")
			*/
			// let ccode = parentTag.dataset.ccode;
			
			// 현재 선택된 TR의 child를 가져와서 각 TD 칼럼에 담겨있는 값을 추출하기
			let tds = parentTag.childNodes
			let ccode = tds[1].textContent
			let ctitle = tds[2].textContent
			let cceo = tds[3].textContent
			let ctel = tds[5].textContent
			
			// alert("출판사 : " + ccode + " ; " + ctitle);
			
			let msg = ctitle + ",";
			msg += cceo + ",";
			msg += ctel;
			
			document.querySelector("input#bo_ccode").value = ccode;
			document.querySelector("span#cp_title").innerText = msg;
			
		} else if(parentClassName === "search_author") {
			
			// let acode = parentTag.dataset.acode;
			let tds = parentTag.childNodes
			let acode = tds[1].textContent
			let aname = tds[3].textContent
			let atel = tds[5].textContent
			
			// alert("저자 코드 : " + acode);
			
			let msg = aname + ",";
			msg += atel;
			
			document.querySelector("input#bo_ccode").value = acode;
			document.querySelector("span#cp_title").innerText = msg;
			
		}
		document.querySelector("div#modal").style.display = "none";
		document.querySelector("div#div_search").innerHTML = ""
		document.querySelector("div#div_search").remove();
	})
</script>
</html>