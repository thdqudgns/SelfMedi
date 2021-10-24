<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />



<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css">
<!-- <script src="//code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="//code.jquery.com/ui/1.13.0/jquery-ui.js"></script>


<%-- <script type="text/javascript" src="<%=request.getContextPath() %> /resources/js/httpRequest.js"> --%>
<!-- </script> -->


<script type="text/javascript">
$(document).ready(function() {
   //페이지 첫 접속 시 입력창으로 포커스 이동
   $("input[type=text]").eq(0).focus();
   
   //패스워드 입력 창에서 엔터 입력 시 form submit
   $("input[type=text]").eq(1).keydown(function(key) {
      if(key.keyCode == 13) {
         $(this).parents("form").submit();
      }
   })

   //로그인 버튼 클릭 시 form submit
   $("[name='btnLogin']").click(function() {
      $(this).parents("form").submit();  
   })

   

   });
</script>

<!-- style="background-color:#345EE6; color:#FFFFFF -->
<!-- style="background-color:#345EE6; color:#000; -->

<style type="text/css">
#container {
	padding: 0px 0px 120px;
}

.pageAticle {
    width: 1050px;
    margin: 0 auto;
}

#tabs {
	width: 450px;
	margin: 0 auto;
}

#form {
	width: 300px;
	margin: 0 auto;
}

div.typeForm businessLogin {
	padding-top:50px; 
}

.businessLogin {
    width: 640px;
    margin: 0 auto;
    
}

.businessLogin {
	padding-top: 50px;
    padding-bottom: 150px;
    
    font-size: 15px;
    color: #666;
    line-height: 17px;
    text-align: right;
}

.pageAticle td {
    display: table-cell;
    padding: 10px 0;
}

tbody {
	line-height: normal;
}

th.userId, th.userPw {
	font-size: 14px;
	text-align: left;
	padding-right: 20px;
}

td.a {
    display: table-cell;
    padding: 10px 0;
    font-size: 14px;
}

.login th {
    width: 150px;
    padding: 20px 0 0 20px;
    font-weight: 700;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    text-align: left;
}

.login th {
    width: 130px;
    padding: 20px 0 0 20px;
    font-weight: 700;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    text-align: left;
}

.typeForm input[type=text],input[type=password] {
    height: 44px;
    padding: 0 14px;
    border: 1px solid #ccc;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    border-radius: 3px;
    background: #fff;
    outline: none;
    vertical-align: top;
}

button#btnLogin {
    border: 1px solid #345FF6;
    background-color: #345FF6;
    color: #fff;
    width: 300px;
    height: 56px;
    font-size: 16px;
    line-height: 54px;
    border-radius: 3px;
    font-weight: 700;
}

/* tab 영역 */
a#ui-id-1.ui-tabs-anchor {
 	padding: 
}

ui-tabs .ui-tabs-nav li.ui-tabs-active .ui-tabs-ancho {
	cursor: inherit;
}

ui-tabs .ui-tabs-nav li {
    list-style: none;
    float: left;
    position: relative;
    top: 0;
    margin: 1px .2em 0 0;
    border-bottom-width: 0;
    padding: 0;
    white-space: nowrap;
}
ui.login  {
    float: left;
    padding: 10px 57px;
    text-decoration: none;
}
</style>

<div class="container">
<div class="pageAticle">
<div class="typeForm businessLogin">

<div id="tabs">
<!-- 상단 tab 영역 -->
<ul class=login >
   <li class="member"><a href="#fragment-1" ><span>일반 로그인</span></a></li>
   <li class="memberBusiness"><a href="#fragment-2" ><span>사업자 로그인</span></a></li>
</ul>

<div id="fragment-1">
	<form id="form" action="/member/login" method="post">

<table  id="Join">
	<thead>
		<tr class="userId">
			<th class="userId">아이디 </th>
			<td>
				<input type="text" id="userId" name="userId" />
			</td>
		</tr>
		
		<tr class="userPw">
			<th class="userPw">비밀번호 </th>
			<td>
				<input type="password" id="userPw" name="userPw" />
			</td>
		</tr>
	</thead>
</table>

<table>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/member/finduserid">아이디찾기</a> &Iota; 
			<a href="<%=request.getContextPath()%>/member/finduserpw">비밀번호 찾기</a> &Iota;  
			<a href="<%=request.getContextPath()%>/member/join">회원가입</a>
		</td>
	</tr>
</table>

<table>
	<tr>
	<th id="btnLogin"></th>
	 	<td class="formFooter">
	 	<button type="button" id="btnLogin" name="btnLogin">로그인</button>
	 	</td>
	</tr>
</table>

<!-- <div> -->
<!-- 	<label for="btnLogin"></label> -->
<!-- 	<div  class="formFooter"> -->
<!-- 	<button type="button" id="btnLogin">로그인</button> -->
<!-- 	</div> -->
<!-- </div> -->

</form>
</div>

<div id="fragment-2">
<form id="form" action="/member/businesslogin" method="post">

<table id="businessJoin">

<thead>
	<tr class="userId">
		<th class="userId">아이디</th>
		<td>
		<input type="text" id="userId" name="userId" />
		</td>
	</tr>

	<tr class="userPw">
		<th class="userPw">비밀번호</th>
		<td>
		<input type="password" id="userPw" name="userPw" />
		</td>
	</tr>
</thead>
</table>

<table>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/member/finduserid">아이디찾기</a> &Iota; 
			<a href="<%=request.getContextPath()%>/member/finduserpw">비밀번호 찾기</a> &Iota; 
			<a href="<%=request.getContextPath()%>/member/businessjoin">회원가입</a>
		</td>
	</tr>
</table>

<table>
 	<tr>
	<th id="btnLogin"></th>
	 	<td class="formFooter">
	 	<button type="button" id="btnLogin" name="btnLogin">로그인</button>
	 	</td>
	</tr>
</table>

</form>
</div>

</div><!-- .tabs -->
</div><!-- typeForm businessLogin -->
   
</div><!-- .pageAticle -->
</div><!-- .container -->     

<script>
$( "#tabs" ).tabs();
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
