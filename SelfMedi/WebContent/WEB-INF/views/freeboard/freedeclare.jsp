<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<style type="text/css">
#container {
	padding: 0px 0px 120px;
	width: 100%;
}

.pageAticle {
	width: 1050px;
	margin: 0 auto;
}

.main {
	margin: 0px 60px 0px -20px;
	padding: 0px 20px 0px 20px;
	width: 100%;
}

.freetg {
	border-collapse: collapse;
	border-bottom: 1px solid #ccc;
	border-spacing: 0;
	width: 800px;
}

.freetg td {
	font-size: 14px;
	overflow: hidden;
	padding: 15px 15px;
	word-break: normal;
}

.freetg th {
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	word-break: normal;
}

.freetg .freetg-cly1 {
	text-align: left;
	vertical-align: middle;
	width: 100%;
}

.freetg .freetg-0lax {
	text-align: left;
	font-weight: bold;
	padding-left: 15px;
	vertical-align: middle;
	background-color: #3E8DDA;
	width: 130px;
	color: #FFF;
}

#freetable {
	width: 1050px;
}

#freetable>table {
	margin: 0 auto;
	text-align: center; /* width:100%; */
}

.freetg tr {
	border-bottom: 1px solid #ccc;
}

#tableBar {
	border-top: 3px solid #345EE6;
	margin-bottom: 0px;
}

.photo {
	list-style-type: none;
}
/* li.photo{width:100%;} */
.freecontent {
	border: 0px solid #FFF;
	width: 1050px;
}

.contentbutton {
	text-align: right;
}

#contenthr {
	color: rgba(0, 0, 0, 0.3);
}

#freeComNick {
	color: black;
	font-weight: bold;
	font-size: 20px; /* margin-bottom:3px; */
}

#freeComCon {
	font-size: 15px;
	color: black;
}

#freeComConUp {
	font-size: 15px;
	color: black;
}

#freeComDate {
	font-size: 13px;
	color: black;
}

.reason {
 width: 590px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		//작성버튼 동작
		$("#btnWrite").click(function() {

			$("form").submit();
		});

		//취소버튼 동작
		$("#btnCancel").click(function() {
			history.go(-1);
		});

	});
</script>

<div class="container">

<h1 style= "text-align :center; color: cornflowerblue;">신고사유</h1>
<hr>

<div id="container">

	<div class="pageAticle">
		<div class="main">
			<form action="/free/declare" method="post" enctype="multipart/form-data">
			<div id="freetable">
				<hr id="tableBar">
				<table class="freetg" style="table-layout: fixed;">
					<thead>
						<tr>
							<th class="freetg-0lax">제목</th>
							<td class="freetg-cly1" colspan="3" style="width: 920px;">${declareFree.boardTitle }</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th class="freetg-0lax">작성자</th>
							<td class="freetg-cly1" colspan="3" style="width: 920px;">${declareFree.userNick }</td>
						</tr>
						<tr>
							<th class="freetg-0lax">작성일</th>
							<td class="freetg-cly1" style="width: 920px;">${declareFree.boardDate }</td>
						</tr>
						<tr>
							<th class="freetg-0lax">조회수</th>
							<td class="freetg-cly1" style="width: 920px;">${declareFree.hit }회</td>
						</tr>
						<tr>
							<td class="freetg-cly1" colspan="4">${declareFree.boardContent }</td>
						</tr>
						
						<tr>
							<th class="freetg-0lax">신고사유</th>
							<td colspan="2"><input type="text" id="reason" name="reason" class="reason" size="100px"/></td>
						</tr>
						
					</tbody>
				</table>
				
				<input type="hidden" name="boardContent" value="${declareFree.boardContent }"/>
				<input type="hidden" name="boardTitle" value="${declareFree.boardTitle }"/>
				<input type="hidden" name="hit" value="${declareFree.hit }"/>
				<input type="hidden" name="userNick" value="${declareFree.userNick }"/>
				<input type="hidden" name="boardNo" value="${declareFree.boardNo }" />
				<input type="hidden" name="userId" value="${declareFree.userId }"/>
				<input type="hidden" name="userNo" value="${declareFree.userNo }"/>

			</div>
			<div>
				<div id="beforeFile">
					기존 첨부파일: 
					<a href="/upload/${freeFile.storedName }" download="${freeFile.originName }">${freeFile.originName }</a>
					<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
				</div>
			</div>
			</form>
			<div class="writebtn" style="text-align: right">	
				<button type="button" id="btnWrite" class="btn btn-info">작성</button>
				<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
			</div>
			
		</div>


	</div>
	<!-- .pageAticle -->
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp" />