<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> : : : Self-Medi : : : </title>

<style type="text/css">

* {margin: 0; padding: 0; box-sizing: border-box;}
.head {display: table;width:100%;table-layout:fixed; margin-left:30px;margin-right:30px;}
.head-row {display:table-cell;vertical-align: middle;}
.head-center { display:table-cell;padding:0 auto;border:1px solid red;}
.head-left {border:1px solid green;}
.head-right {border:1px solid blue;text-align:center;}
.img-overlay {opacity:1; display:block;width:100%;height:100%;background:rgba(0, 0, 0, 0); text-align:center; position:relative;padding: 10px 15px;}
.clearfix {clear: both;
	/* 컨텐츠 영역 없애기 */
	width: 0; height: 0;
	/* 넘쳐서 흘러나온 내용물을 보이지 않게 설정한다 */
	overflow: hidden;}
/* my style */
ul {margin: 20px;}
li {display: inline-table;
	width: 200px;
	height: 100px;
	border: 1px solid #ccc;
	text-align: center;
	table-layout: fixed;
	list-style-type: none;}
span {display: table-cell;vertical-align: middle;}
.fixed {position: fixed;
	border: 1px solid #213983;
	bottom: 30px;
	right: 50px;
	font-size: 15px;
	font-weight:bolder;
	text-align: center;
	color: black;}

</style>

</head>


<body>

	<div class="head">

		<div class="head-row head-left">왼쪽 박스</div>

		<div class="head-row head-center">
			<div class="img-overlay">
				<a href="/"><img style="width: 300px; height: 112px; border: 1px solid black;"
					alt="logo" src="../resources/img/selfmedi.png"></a>
			</div>
		</div>

		<div class="head-row head-right">
		<c:if test="${empty login or not login }">
		<span class="ui-icon ui-icon-person"></span><a href="<%=request.getContextPath() %>/member/login">로그인</a> &Iota; <a href="<%=request.getContextPath() %>/member/join">회원가입</a>
		</c:if>
		<c:if test="${not empty login and login and userKind ne 0 }">
		<span class="ui-icon ui-icon-person"></span><a href="<%=request.getContextPath() %>/member/logout" >로그아웃</a> &Iota; <a href="<%=request.getContextPath() %>/">마이페이지</a>
		</c:if>
		<c:if test="${not empty login and login and userKind eq 0}">
		<span class="ui-icon ui-icon-person"></span><a href="<%=request.getContextPath() %>/member/logout" >로그아웃</a> &Iota; <a href="<%=request.getContextPath() %>/ad/member/list">관리자페이지</a>
		</c:if>
		</div>

	</div>


	<div class="clearfix"></div>
	<div></div>

	<div>
		<ul>
	
			<div>
				<li><a>
				<span>카테고리</span>
				</a></li>
			</div>
	
			<div>
				<li><a>
				<span>상품</span>
				</a></li>
			</div>
	
			<div>
				<li><a>
				<span>게시판</span>
				</a></li>
			</div>

			<div>
				<li><a>
				<span>공지사항</span>
				</a></li>
			</div>
	
		</ul>
	</div>


	<div class="box fixed">
<a  href="#">
&nbsp;&Delta;TOP&nbsp;
</a>
</div>


</body>
</html>