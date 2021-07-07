<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>

	* {
		box-sizing: border-box;
		margin:0;
		padding:0;
	
	}

	div.total {
		display: flex;
		flex-wrap: wrap;
	}

	div.g_box {
		border: 1px solid cadetblue;
    	width: 30%;
    	max-height:30vh;
		margin: 2rem auto;
		display: flex;
	}
		
	div.g_box div:first-of-type {
		padding: 5px;
	}
	
	
	div.img_box {
		width: 45%;
		height: 100%;
		overflow: hidden;
	}
	
	img {
		width: 100%;
	}
	

	h3 {
		text-align: center;
	}
	
	a#detail:hover {
		cursor: pointer;
		color: cornflowerblue;
	}
	
	a#detail {
		text-decoration: none;
		color: black;
	}
	
	p {
		text-align: center;
	}
	
</style>

<div class="total">
	<c:forEach items="${GALLERYS}" var="G">
		<div class="g_box">
			<div class="img_box">
				<img src="${rootPath}/files/${G.g_image}" >
			</div>
			<div>
				<h3>
					<a id="detail" href="${rootPath}/gallery/detail/${G.g_seq}">${G.g_subject}</a>
				</h3>
				<p>${G.g_content}</p>
			</div>
		</div>	
	</c:forEach>
</div>	
