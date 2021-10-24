<%@page import="com.fulldoping.member.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />


<style type="text/css">
#container-findId {
	padding: 0px 0px 120px;
}

.pageAticle {
    width: 300px;
    margin: 0 auto;
}

#form {
	width: 300px;
	margin: 0 auto;
}


div.typeForm memberfingId {
	padding-top:50px; 
}

.memberfingId {
    width: 300px;
    margin: 0 auto;
    
}

.memberfingId {
	padding-top: 50px;
    padding-bottom: 150px;
    
    font-size: 15px;
    color: #666;
    line-height: 17px;
}

.memberfingId td {
    display: table-cell;
    padding: 10px 0px 0px 0px;
}

h2.title {
	text-align: center;
    color: #333;
    font-weight: 700;
    font-size: 28px;
    padding: 10px 0px 10px 0px;
}

button.btnLogin {
    border: 0px solid #345FF6;
    background-color: #345FF6;
    color: #fff;
    width: 300px;
    height: 50px;
    font-size: 16px;
    line-height: 54px;
    border-radius: 3px;
    font-weight: 700;
    margin: 20px 0px;
}

button.btnFindPw {
    border: 1px solid #345FF6;
    background-color: #ffffff;
    color: #345FF6;
    width: 300px;
    height: 50px;
    font-size: 16px;
    line-height: 54px;
    border-radius: 3px;
    font-weight: 700;
}

input.btnBack {
    border: 1px solid #345FF6;
    background-color: #ffffff;
    color: #345FF6;
    width: 300px;
    height: 50px;
    font-size: 16px;
    line-height: 54px;
    border-radius: 3px;
    font-weight: 700;
}

.successuserUserId {
	text-align: center;
    font-size: 19px;
    margin: 10px;
    color: #345ff6;
}
.successuserId {
	text-align: center;
    font-size: 19px;
    margin: 10px;
    color: #3d3d3d;
}

form {margin:0 auto;text-align:center;/* border:3px dotted gray; */padding-top:20px;}
form > div#findId {padding:10px;display:inline-block;margin:25px auto;font-size:20px;}
form > div#findId > strong > span {color:tomato;}
</style>

<div id="container-findId">

<div class="pageAticle">
<div class="typeForm memberfingId">
<form action="/member/finduserid" method="post" >

<div class="titPage">
	<h2 class="title">아이디 찾기</h2>
</div>

<c:if test="${(empty member.userName and member.userName == null) 
	or (empty member.userEm and member.userEm == null)}">
<div class="findId" style="margin:20px 10px;">	
   <strong>아이디 찾기에 실패했습니다</strong><br>
</div>
	<input type="button" class="btnBack" value="돌아가기"  onClick="history.back()" />
</c:if>

<c:if test="${(not empty member.userName and member.userName != null) 
	or (not empty member.userEm and member.userEm != null)}">

<div class="findId">
<img alt="image" src="https://i.imgur.com/mUldLBU.png" style="width:50px;height:50px;margin:20px 10px;"><br>
	<strong>회원님의</strong><br>
	<strong>아이디 찾기가 완료되었습니다!</strong><br><br>
	<strong>회원님의 아이디는 <span><c:out value="${member.userId }" ></c:out>입니다</span></strong><br>
</div>

<div id="btnLogin">
   <button type="button" class="btnLogin" onclick='location.href="/member/login";'>로그인 하기</button>
   <button type="button" class="btnFindPw" onclick='location.href="/member/finduserpw";'>비밀번호 찾기</button>
</div>
</c:if>


</form>

</div>
</div>
<!-- .container -->
</div>


<c:import url="/WEB-INF/views/layout/footer.jsp" />
