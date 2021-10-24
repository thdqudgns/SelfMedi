<%@page import="com.fulldoping.member.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
#container {
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

div.findId {
	margin: 0 auto;
}

div.typeForm memberfindPw {
	padding-top:50px; 
}

.memberfindPw {
    width: 300px;
    margin: 0 auto;
    
}

.memberfindPw {
	padding-top: 50px;
    padding-bottom: 150px;
    
    font-size: 15px;
    color: #666;
    line-height: 17px;
}

.memberfindPw td {
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
}

button.btnback {
    border: 0px solid #345FF6;
    background-color: #345FF6;
    color: #fff;
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

form {margin:0 auto;text-align:center;/* border:3px dotted gray; */padding-top:20px;}
form > div#userPw {padding:10px;display:inline-block;margin:25px auto;font-size:16px;}
/* form > div#userPw > strong > span {color:tomato;} */
</style>

<div class="container">

<div class="pageAticle">
<div class="typeForm memberfindPw">
<form action="/member/finduserpw" method="post">

<div class="titPage">
	<h2 class="title">비밀번호 찾기</h2>
</div>

<c:if test="${(empty member.userId and member.userId == null) or (empty member.userName and member.userName == null) 
	or (empty member.userEm and member.userEm == null)}">

<div id="userPw" style="margin:20px 10px;">
   <strong style="margin-bottom:20px;">비밀번호 찾기에 실패했습니다</strong><br>
</div> 
   	<input type="button" class="btnBack" value="돌아가기"  onClick="history.back()" />
</c:if>

<c:if test="${(not empty member.userId and member.userId != null) or (not empty member.userName and member.userName != null) 
	or (not empty member.userEm and member.userEm != null)}">
   
<div id="userPw">
	<img alt="image" src="https://i.imgur.com/z2UwFhJ.png" style="width:60px;height:45px;margin-bottom:20px;"><br>
	<strong>회원님의 비밀번호는 <span><c:out value="${member.userPw }" ></c:out> 입니다</span></strong><br>
</div>

<div id="btnLogin">
   <button type="button" class="btnLogin" onclick='location.href="/member/login";'>로그인 하기</button>
<!--    <button type="button" class="findId" onclick='location.href="/member/finduserid";'>아이디 찾기</button> -->
</div>
</c:if>

</form>

</div>
</div>
<!-- .container -->
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />