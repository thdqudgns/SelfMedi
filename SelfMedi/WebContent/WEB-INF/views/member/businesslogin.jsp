<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript"
	src="<%=request.getContextPath() %> /resources/js/httpRequest.js">
</script>

<script type="text/javascript">
$(document).ready(function() {
   //페이지 첫 접속 시 입력창으로 포커스 이동
   $("input").eq(0).focus();
   
   //패스워드 입력 창에서 엔터 입력 시 form submit
   $("input").eq(1).keydown(function(key) {
      if(key.keyCode == 13) {
         $(this).parents("form").submit();
      }
   })

   //로그인 버튼 클릭 시 form submit
   $("#btnLogin").click(function() {
      $(this).parents("form").submit();
   })
   
//    //취소 버튼 누르면 뒤로가기
//    $("#btnCancel").click(function() {
//       history.go(-1);
//    })
});
</script>

<style type="text/css">
#container {
	padding: 0px 0px 120px;
}

form {
   width: 400px;
   margin: 0 auto;
}

.memberJoin {
    width: 640px;
    margin: 0 auto;
}
</style>


<div class="container">

<div class="pageAticle">
<div class="typeForm businessLogin">


<form id="form" action="/member/businesslogin" method="post">

<div class="titPage">
	<h2 class="title">사업자 로그인</h2>
	<hr>
</div>

<table class="table">

<thead>
	<tr class="usrId">
		<th class="usrId">아이디</th>
		<td>
		<input type="text" id="userId" name="userId" />
		</td>
	</tr>

	<tr class="usrPw">
		<th class="usrPw">비밀번호</th>
		<td>
		<input type="text" id="usrPw" name="usrPw" />
		</td>
	</tr>
</thead>

<tbody>
	<tr>
		<td><a href="<%=request.getContextPath()%>/member/finduserid">아이디찾기</a> &Iota; </td>
		<td><a href="<%=request.getContextPath()%>/member/finduserpw">비밀번호 찾기</a> &Iota; </td> 
		<td><a href="<%=request.getContextPath()%>/member/businessjoin">회원가입</a></td>
	</tr> 
</tbody>
</table>

<div>
	<label for="btnLogin"></label>
	<div class="formFooter">
		<button type="button" id="btnLogin">로그인</button>
	</div>
</div>

</form>

</div>
</div><!-- .pageAticle -->
</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
