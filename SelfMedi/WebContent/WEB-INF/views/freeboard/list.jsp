<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<style type="text/css">

.tg  {border-collapse:collapse;
	border-spacing:0;
	text-align: center;}

.tg td{ border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:'notoRegular';
	font-size:14px;
  	overflow:hidden;
  	padding:10px 20px;
  	word-break:normal;}
  
.tg th{border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:'notoRegular';
	font-size:14px;
  	font-weight:bold;
  	overflow:hidden;
  	padding:10px 20px;
  	word-break:normal;
  	color:#FFF;
  	text-align: center;}
  
.tg .tg-18bt{background-color:#3E8DDA;
	border-color:#dae8fc;
	font-family:'notoRegular';
	vertical-align:middle;
	border-left: none;
	border-right: none;}

.tg .tg-xphl{border-color:#3E8DDA;
	vertical-align:middle;
	color: black;
	border-left: none;
	border-right: none;
	text-decoration: none;}

.btnBox {
	background-color:#7892c2;
	border-radius:10px;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:'notoRegular';
	font-size:15px;
	font-weight:bold;
	padding:5px 10px;
	text-decoration:none;
}
.btnBox {
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	background-color:#ffffff;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:'notoRegular';
	font-size:12px;
	font-weight:bold;
	padding:6px 12px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.btnBox:hover {
	background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
	background-color:#f6f6f6;
}
.btnBox:active {
	position:relative;
	top:1px;
}
.btn{
	position : relative;
	left: 880px;
}
tbody > tr:nth-child(odd){background-color: #F2F2F2;}
tbody > tr > td:nth-child(2):hover {background-color: highlight;}
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/free/write";
	});
	
});
</script>




<div class="container">
<div class="boardtitle">
<h1 style= "text-align :center;color:black;font-weight:bold;" >자유게시판</h1>
<hr>
</div>

<table class="tg" style="undefined;table-layout: fixed; width: 900px; margin:0 auto;">
<colgroup>
	<col style="width: 10%">
	<col style="width: 40%">
	<col style="width: 20%">
	<col style="width: 15%">
	<col style="width: 15%">
</colgroup>
<thead>
  <tr>
    <th class="tg-18bt">글번호</th>
    <th class="tg-18bt">제목</th>
    <th class="tg-18bt">작성자</th>
    <th class="tg-18bt">조회수</th>
    <th class="tg-18bt">작성일</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${freeList }" var="free">
  <tr>
    <td class="tg-xphl" style= "text-align: center;">${free.boardNo }</td>
    <td class="tg-xphl" ><a href="/free/view?boardNo=${free.boardNo }" style="color:black;">
		${free.boardTitle }
		</a></td>
<%--     <td class="tg-xphl">${free.userId }</td> --%>
    <td class="tg-xphl" style= "text-align: center;">${free.userNick }</td>
    <td class="tg-xphl" style= "text-align: center;">${free.hit }</td>
    <td class="tg-xphl" style= "text-align: center;">${free.boardDate }</td>
  </tr>
</c:forEach>
</tbody>
</table>
<br>

<c:if test="${not empty login and login}">
<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-primary btn-sm">글 작성</button>
</div>
</c:if>

<!-- .container -->
</div>

<c:import url="/WEB-INF/views/layout/pagingfree.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />
