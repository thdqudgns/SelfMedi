<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	//페이지 첫 접속 시 입력창으로 포커스 이동
	$("input").eq(0).focus();

	//로그인 버튼 클릭 시 form submit
	$("#btnJoin").click(function() {
		var p1 = document.getElementById('userPw').value;
	    var p2 = document.getElementById('userPw2').value;
	    
	    if( p1 != p2 ) {
	       alert("비밀번호가 일치 하지 않습니다");
	       return false;
	     } else {
	       $(this).parents("form").submit();
	     }   
	})
});
</script>


<style type="text/css">
#container {
	padding: 0px 0px 120px;
}

form#form {
	width: 680px;
	padding-top: 50px;
}

.memberJoin {
    width: 640px;
    margin: 0 auto;
}

.memberJoin .pageSub {
    padding-bottom: 10px;
    font-size: 12px;
    color: #666;
    line-height: 17px;
    text-align: right;
}

.memberJoin .tbl_comm .userId td {
    padding-top: 19px;
}

.typeForm .btnDefault {
    border: 1px solid #345ff6;
    background-color: #fff;
    color: #345ff6;
}

.memberJoin .btnDefault {
    display: inline-block;
    width: 120px;
    margin-left: 5px;
    vertical-align: top;
}

.typeForm .btnDefault {
    height: 44px;
    border-radius: 3px;
    font-weight: 700;
    font-size: 14px;
    line-height: 40px;
    text-align: center;
    outline: none;
}

.memberJoin th {
    width: 130px;
    padding: 20px 0 0 20px;
    font-weight: 700;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    text-align: left;
}

title {
	color: #333333;
	font: 28px "noto sans";
}

.titPage h2.title {
    font-weight: 700;
    font-size: 28px;
    color: #333;
    line-height: 35px;
    text-align: center;
    letter-spacing: -1px;
}

tbody {
	line-height: normal;
}

td {
    display: table-cell;
    padding: 10px 0;
}

div.check {
	color: #4C4C4C;
	font: 18px "noto sans";
	padding: 12px 0px 4px;
}

div.checkView {
	color: #4C4C4C;
	font: 14px "noto sans";
	padding: 8px 0px;
}


.memberJoin .regAgree .label_all_check {
    padding: 12px 0 4px;
    font-weight: 700;
    font-size: 18px;
}

.checkView > a {
    right: 22px;
    top: 0;
	padding: 10px 0px 0px;
    font-size: 14px;
    color: #345FF6;
    line-height: 18px;
    letter-spacing: 0;
	
}

.typeForm input[type=checkbox]:checked+.ico {
    background: url() no-repeat 50% 50%;
    background-size: 24px 24px;
}

input.radio {
	margin: 0px 12px 0px 0px;
}

input.agree {
	margin: 0px 12px 0px 0px;
}

input#userGen {
	margin: 0px 12px 0px 0px;
}

label.checked {
	padding: 10px 52px 10px 0px; 
}

.memberJoin input[type=text],.typeForm input[type=file], .memberJoin input[type=password] {
    width: 332px;
}

.typeForm textarea, .typeForm input[type=text], .typeForm input[type=file], .typeForm input[type=password] {

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

.pageAticle {
    width: 1050px;
    margin: 0 auto;
}

.pageAticle button, .pageAticle input, .pageAticle select, .pageAticle * {
    font-family: noto sans;
    font-weight: 400;
    letter-spacing: 0;
}

span.ico {
	color: #EE6A7B;
	font-size: 12px "noto sans";
	padding: 0px 2px 0px 0px;
}

.type_form input[type=radio]:checked+.ico {
    border: 1px solid #5f0081;
    background-color: #5f0080;
}

.type_form input[type=radio]:checked+.ico:after {
    content: "";
    position: absolute;
    left: 50%;
    top: 50%;
    width: 10px;
    height: 10px;
    margin: -5px 0 0 -5px;
    border-radius: 100%;
    background-color: #fff;
}

.memberJoin .formFooter {
    padding-top: 40px;
    text-align: center;
}

/* 중복 버튼 */
button.btnJoin {
    border: 1px solid #345FF6;
    background-color: #345FF6;
    color: #fff;
    width: 240px;
    height: 56px;
    font-size: 16px;
    line-height: 54px;
    border-radius: 3px;
    font-weight: 700;
}

/* input태그  포커스 변경 */
input[type=text]:focus, input[type=password]:focus {
border-color: #2932b1;
box-shadow: 0 0 2px 0 #2932b1;
}
table.table > thead > tr > th {vertical-align: middle; text-align: left;}
</style>


<!-- ------------------------------------------------------------------ -->


<div id="container">

<div class="pageAticle">
<div class="typeForm memberJoin ">
<form id="form" name="frmMember" action="/member/businessjoin" method="post" enctype="multipart/form-data">

<div class="titPage">
	<h2 class="title">회원가입</h2>
	<h4 class="text-left">1. 기본정보<p class="pageSub"><span class="ico">*</span>필수입력사항</p></h4>
	<hr>
</div>

<table class="table">

<thead>
	<tr class="userId">
		<th>아이디<span class="ico">*</span></th>
		<td>
			<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요" />
			<button class="btnDefault" type="submit">중복확인</button>
		</td>
	</tr>
	
	<tr class="userPw">
		<th>비밀번호<span class="ico">*</span></th>
		<td>
			<input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요"/>
		</td>
	</tr>	
		
	<tr class="userPw2">
		<th>비밀번호확인<span class="ico">*</span></th>
		<td>
			<input type="password" id="userPw2" name="userPw2" placeholder="비밀번호를 한번 더 입력해주세요" />			
		</td>
	</tr>
		
	<tr class="userName">
	<th>이름<span class="ico">*</span></th>
		<td>
			<input type="text" id="userName" name="userName" placeholder="이름을 입력해주세요" />
		</td>
	</tr>
	
	<tr class="userNick">
	<th>닉네임<span class="ico">*</span></th>
		<td>
			<input type="text" id="userNick" name="userNick" placeholder="닉네임을 입력해주세요" />
			<button class="btnDefault" type="submit">중복확인</button>
		</td>
	</tr>
		
	<tr class="userEm">
	<th>이메일<span class="ico">*</span></th>
		<td>
			<input type="text" id="userEm" name="userEm" placeholder="예: self-medi@fulldoping.com" />
			<button class="btnDefault" type="submit">중복확인</button>				
		</td>
	</tr>
	
	<tr class="userPh">
		<th>휴대폰<span class="ico">*</span></th>
		<td>
			<input type="text" id="userPh" name="userPh" placeholder="숫자만 입력해주세요" />
		</td>
	</tr>
		
	<tr class="userGen">
		<th>성별</th>
		<td>
			<label class="checked">
				<input type="radio" name="userGen" id="userGen" value="M">
				<span class="radio"></span>남자
			</label>
			<label class="checked">
				<input type="radio" name="userGen" id="userGen" value="F">
				<span class="radio"></span>여자
			</label>
			<label class="checked">
				<input type="radio" name="userGen" value="N" checked="checked">
				<span class="radio"></span>선택안함
			</label>
		</td>
	</tr>
		
	<tr class="userBirth" >
		<th>생년월일</th>
		<td>
			<div class="userBirth">
				<input type="text" id="userBirth" name="userBirth" placeholder="YYYY / MM / DD"/>
			</div>
		</td>
	</tr>
	
	<tr class="businessNo">
		<th>약사면허번호<span class="ico">*</span></th>
		<td>
			<input type="text" id="businessNo" name="businessNo" placeholder="숫자만 입력해주세요" />
		</td>
	</tr>		

	<tr>
		<th>
			<h4>2. 약국정보</h4>
			<hr>
		</th>
		<td >
		</td>
	</tr>
	
	<tr class="pharmacy">
		<th>약국명<span class="ico">*</span></th>
		<td>
			<input type="text" id="pharmacy" name="pharmacy" placeholder="약국명을 입력해주세요" />
		</td>
	</tr>	
			
			
	<tr class="originName">
		<th>약사면허증<span class="ico">*</span></th>
		<td class="filebox"> 
			<label for="originName">
<!-- 				<input id="originName" name="originName" class="originName" value="첨부파일" placeholder="파일을 첨부해주세요" /> -->
				<input type="file" id="originName" name="originName" />
			</label>	
		</td>
	</tr>
</thead>

<!-- -------------------------------------------------------------- -->

<tbody class="tbody">
	<tr class="agree">
		<th>이용약관동의 <span class="ico">*</span></th>
		<td>
			
		<div class="check">
			<label class="checkAgree">
				<input type="checkbox" name="agree">
				<span class="agree"></span>전체 동의합니다.
			</label>
		</div>		
	
		<div class="checkView">
			<label class="checkAgree">
				<input type="checkbox" name="agree">
				<span class="agree">이용약관 동의 (필수)</span>
			</label>
			<a href="#">약관보기 ></a>
		</div>

		<div class="checkView">
			<label class="checkAgree label_block">
				<input type="checkbox" name="agree">
				<span class="agree">개인정보 수집이용 동의 (필수)</span>
			</label>
			<a href="#">약관보기 ></a>
		</div>

		<div class="checkView">
			<label class="check_agree label_block">
				<input type="checkbox" name="agree">
				<span class="agree">마케팅 정보 수신 (선택) 이메일</span>
			</label>
			<a href="#">약관보기 ></a>
		</div>			
		</td>
</table>

	<div>
		<label for="btnJoin"></label>
		<div id="btnJoin" class="formFooter">
			<button type="button" class="btnJoin">가입하기</button>
		</div>
	</div>
</form>
</div><!-- .typeForm memberJoin -->
</div><!-- .pageAticle -->
</div><!-- #container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
