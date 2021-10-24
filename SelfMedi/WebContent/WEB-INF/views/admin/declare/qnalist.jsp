<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/admin/layout/header.jsp" />

<div class="boardcontainer">
<div class="boardtitle">

<h2 style= "margin:0px auto;padding-top: 30px; text-align: center; color: black;" >QnA 신고 목록</h2>
<hr>
</div>

<table class="table table-hover" style="width: 800px; margin: 0 auto;">
<colgroup>
	<col style="width: 83px">
	<col style="width: 400px">
	<col style="width: 83px">
	<col style="width: 83px">
	<col style="width: 160px">
</colgroup>
<thead>
  <tr>
    <th style="text-align: center;">신고 번호</th>
    <th style="text-align: center;">제목</th>
    <th style="text-align: center;">작성자</th>
    <th style="text-align: center;">조회수</th>
    <th style="text-align: center;">작성일</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${qnaDeclareList }" var="qnaDeclare">
  <tr>
    <td class="tg-xphl" style= "text-align: center;">${qnaDeclare.boardNo }</td>
    <td class="tg-xphl" ><a href="/ad/qnadeclare/view?boardNo=${qnaDeclare.boardNo }">
		${qnaDeclare.boardTitle }
		</a></td>
<%--     <td class="tg-xphl">${free.userId }</td> --%>
    <td class="tg-xphl" style= "text-align: center;">${qnaDeclare.userNick }</td>
    <td class="tg-xphl" style= "text-align: center;">${qnaDeclare.hit }</td>
    <td class="tg-xphl" style= "text-align: center;">${qnaDeclare.boardDate }</td>
  </tr>
</c:forEach>
</tbody>
</table>

<c:import url="/WEB-INF/views/layout/pagingqnadeclare.jsp" />
</div>


</body>
</html>
