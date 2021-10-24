<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/admin/layout/header.jsp" />



<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/ad/freedeclare/list");
	});
		
	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("신고 사유를 확인하셨습니까?") ) {
			$(location).attr("href", "/ad/freedeclare/delete?boardNo=${viewFreeDeclare.boardNo }");
		}
	});
	
});
</script>

<div class="boardcontainer">

<h2 style= "margin:0px auto;padding-top: 30px; text-align: center; color: black;">자유게시판 신고 상세보기</h2>
<hr>

<table class="table table-bordered" style="width: 800px; margin: 0 auto;text-align: left;">
<colgroup>
<col style="width: 61px">
<col style="width: 71px">
<col style="width: 71px">
<col style="width: 71px">
<col style="width: 71px">
<col style="width: 51px">
<col style="width: 71px">
<col style="width: 51px">
<col style="width: 71px">
<col style="width: 51px">
</colgroup>

  <tr>
    <td class="info" style="font-weight:bold;">제목</td>
    <td class="tg-0lax" colspan="9">${viewFreeDeclare.boardTitle }</td>
  </tr>

  <tr>
    <td class="info" style="font-weight:bold;">글 번호</td>
    <td class="tg-c">${viewFreeDeclare.boardNo }번</td>
    <td class="info" style="font-weight:bold;">작성자</td>
    <td class="tg-c">${viewFreeDeclare.userNick }</td>
    <td class="info" style="font-weight:bold;">조회</td>
    <td class="tg-c">${viewFreeDeclare.hit }회</td>
    <td class="info" style="font-weight:bold;">작성일</td>
    <td class="tg-b" colspan="3">${viewFreeDeclare.boardDate }</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="10" rowspan="8">${viewFreeDeclare.boardContent }</td>
  </tr>
</table>
<table class="table table-bordered" style="width: 800px; margin: 0 auto;text-align: left;">
  <tr>
  	<th class="info" style="font-weight:bold;">신고사유<br></th>
  </tr>	
  <tr>
    <td class="tg-c">${viewFreeDeclare.reason }</td>
  </tr>
</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty freeFile }">
<li class="photo">
	<img src="/upload/${freeFile.storedName }" style="max-width:50%; height: auto"/>
</li>
</c:if>
</div>

<br>
<div class="text-center">	
	<button id="btnList" class="btn btn-primary btn-sm">목록</button>
	<button id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
	
</div>

</div>

</body>
</html>
