<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
#container {
	padding: 0px 0px 120px;
	width:100%;
}
.pageAticle {
  	width: 1050px;
    margin: 0 auto;
}
.main {
    margin: 0px 60px 0px -20px;
    padding: 0px 20px 0px 20px;
    width:100%;
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


<div class="qnadeclaremain">
<form action="/qna/declare" method="post" enctype="multipart/form-data">


<table >

<tr><td class="info">게시글 번호</td><td>${declareQnA.boardNo }</td></tr>
	<tr><td><input type="hidden" name="boardNo" value="${declareQnA.boardNo }" /></td><tr>
<%-- <tr><td class="info">아이디</td><td>${declareQnA.userId }</td> --%>
	<tr><td><input type="hidden" name="userId" value="${declareQnA.userId }"/></td></tr>
	
<%-- <tr><td class="info">회원번호</td><td>${declareQnA.userNo }</td> --%>
	<tr><td><input type="hidden" name="userNo" value="${declareQnA.userNo }"/></td></tr>

<tr><td class="info">닉네임</td><td>${declareQnA.userId}</td>
	<td><input type="hidden" name="userNick" value="${declareQnA.userId }"/></td></tr>
	
<tr><td class="info">조회수</td><td>${declareQnA.hit }</td>
	<td><input type="hidden" name="hit" value="${declareQnA.hit }"/></td></tr>

<tr><td class="info" >제목</td><td>${declareQnA.boardTitle }</td>
	<td><input type="hidden" name="boardTitle" value="${declareQnA.boardTitle }"/></td></tr>
	
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2" >${declareQnA.boardContent }</td>
	<td><input type="hidden" name="boardContent" value="${declareQnA.boardContent }"/></td></tr>
	
<tr><td class="info" colspan="2">신고사유</td></tr>
<tr><td colspan="2"><input type="text" id="reason" name="reason" class="reason" size="100px"/></td></tr>
</table>


<div>
	<div id="beforeFile">
		기존 첨부파일: 
		<a href="/upload/${qnaFile.storedName }" download="${qnaFile.originName }">${qnaFile.originName }</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
	</div>
</div>

</form>
</div>


<div class="writebtn" style="text-align: right">	
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>


</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />