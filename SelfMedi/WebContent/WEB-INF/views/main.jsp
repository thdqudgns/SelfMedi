<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<style type="text/css">
.button{margin:0 auto;text-align:center;}
/* ============================================== */
/* --- 이미지 슬라이더 박스 --- */
#sliderbox {z-index:-1;width:928px;height:272px;object-fit:cover;border: 1px solid #ccc;margin:0 auto;/* overflow: visible; */overflow: hidden;}
#slider {padding: 0;margin: 0;list-style-type: none;position: relative;}
#slider li {position: absolute;}
#slider li img {width:928px;height:272px;object-fit:cover;}
#selftestArea {display:table;text-align:center;margin:0 auto;}
#selftestArea.selftestBox {text-decoration:none;font-size:16px;padding:0;}
.box1 {border:1px solid gray;display:table-cell;vertical-align: middle;width:300px;height:300px;background-color: rgba(200,200,200,0.5);}
.box2 {border:1px solid #345EE6;display:table-cell;vertical-align: middle;width:300px;height:300px;background-color: #3E8DDA;}

#selftestList{color:black;}
.box1 > a {text-align:center;font-size:17px;font-weight:bold;}
</style>

<!-- 슬라이드 이미지 -->
<div id="sliderbox">
<ul id="slider">
	<li><img src="https://i.imgur.com/9ePqNGi.png"/></li>
	<li><img src="https://i.imgur.com/bLb4WA9.png" /></li>
	<li><img src="https://i.imgur.com/ZQYAS3S.png" /></li>
	<li><img src="https://i.imgur.com/h2T54Kx.png" /></li>
	<li><img src="https://i.imgur.com/byDbWAe.png"/></li>
	<li><img src="https://i.imgur.com/fa67HEw.png"/></li>
</ul>
</div>

<br><br>

<div id="selftestArea">

	<div class="selftestBox box1">
		<a href="<%=request.getContextPath()%>/selftest/list">
			<div><img style="width:180px;height:180px;margin-bottom:10px;" src="https://i.imgur.com/uZreYga.png" /></div>
			<span id="selftestList">자가진단 목록</span>
		</a>
	</div>

	<div class="selftestBox box2">
		<a href="<%=request.getContextPath()%>/selftest/list">
			<div style="text-align:left;">
			<div style="padding:0px 0px 0px 20px;font-size:13px;color:#FFF;display:inline-block;">안전하고 쉽게 <br>건강기능식품 선택에 도움을 드리는</div><br><br>
			<div style="padding:0px 0px 0px 20px;font-size:30px;font-weight:bold;color:black;display:inline-block;">Self-Medi</div><br>
			<div style="padding:0px 0px 20px 20px;font-size:13px;color:#FFF;display:inline-block;">Self-Medi를 통해 건강을 챙겨보세요!</div>
			</div>
		</a>
	</div>

</div>



<%-- <!-- 비로그인상태 -->
<c:if test="${empty login or not login }">
<div class="button">
   <strong>로그인이 필요합니다</strong>
   <br><br>
   <button onclick='location.href="<%=request.getContextPath() %>/member/login";'>일반 로그인</button>
   <button onclick='location.href="<%=request.getContextPath() %>/member/businesslogin";'>사업자 로그인</button>
   <br><br>
   <button onclick='location.href="<%=request.getContextPath() %>/member/join";'>일반 회원가입</button>
   <button onclick='location.href="<%=request.getContextPath() %>/member/businessjoin";'>사업자 회원가입</button>
</div>
   
</c:if>

<!-- 로그인상태 = 일반 -->
<c:if test="${not empty login and login and userKind ne 0}">
<div class="button">
   <strong>${userNick }님, 환영합니다</strong><br>
   <button onclick='location.href="<%=request.getContextPath() %>/selftest/write";'>자가진단 작성</button>
   <button onclick='location.href="<%=request.getContextPath() %>/selftest/list";'>자가진단 목록</button>
   <button onclick='location.href="<%=request.getContextPath() %>/basket/view";'>보관함</button>
   <button onclick='location.href="<%=request.getContextPath() %>/free/list";'>자유게시판</button>
   <button onclick='location.href="<%=request.getContextPath() %>/notice/list";'>공지사항</button>
   <button onclick='location.href="<%=request.getContextPath() %>/QnA/list";'>QnA게시판</button>
</div>
</c:if>

<!-- 로그인상태 = 관리자 -->
<c:if test="${not empty login and login and userKind eq 0}">
<div class="button">
   <strong>관리자님, 환영합니다</strong><br>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/member/list";'>회원 관리</button>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/notice/list";'>공지사항 관리</button>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/free/list";'>자유게시판 관리</button>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/qna/list";'>QnA 관리</button>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/declare/list";'>신고된 게시글 관리</button>
   <button onclick='location.href="<%=request.getContextPath() %>/ad/product/list";'>상품 관리</button>
</div>
</c:if> --%>

<!-- .container -->

<script type="text/javascript">
/* --- 슬라이더 스크립트 --- */
$(document).ready(function() {
	var $slider_list = $("#slider li")
	console.log($slider_list)
	
// 	$slider_list.css("left", "600px")
	$slider_list.css("left", $("#sliderbox").css("width"))
	//-------------------------------------------------------
	//새로고침했을 때 보여진 첫번째 이미지
	$slider_list.eq(0).css("left", 0)
	//-------------------------------------------------------
	//#sliderbox를 클릭할 때마다 이미지 교체하기
	//---------------------------------------------------
	var sliderClick = function() {
		//#sliderbox에 클릭이벤트 발생시키기
		$("#sliderbox").click()
	}
	
	//3초마다 #sliderbox에 클릭이벤트 발생시키기
	var tid = setInterval( sliderClick, 3000 );
	//	-> tid : timerid라고 하며 setInterval함수의 반환값
	//	-> 타이머객체를 관리하는 값
	//---------------------------------------------------
	//현재 보여지는 슬라이드의 인덱스
	var curSlide = 0;
	
	$("#sliderbox").click(function() {
		
		// tid를 가진 타이머객체의 타이머를 종료시킨다
		clearInterval( tid )
		
		//3초마다 클릭이벤트 발생시키기
		tid = setInterval( sliderClick, 3000 );
		//-> 마우스 클릭할 때마다 타이머의 시간을 3초로 갱신한다
		
		//다음에 보여지는 슬라이드의 인덱스
		var nextSlide = curSlide + 1;
		nextSlide %= $slider_list.length;			
		console.log(curSlide, ":", nextSlide)
		
		//현재 슬라이드 숨기기 - curSlide
		$slider_list.eq(curSlide).animate({"left": "-=" + $("#sliderbox").css("width") })

		//다음 슬라이드 준비하기
		$slider_list.eq(nextSlide).css("left", $("#sliderbox").css("width"))
		
		//다음 슬라이드 보여주기 - nextSlide
		$slider_list.eq(nextSlide).animate({"left": "-=" + $("#sliderbox").css("width") })

		//증가식 (순환구조 만들기)
		curSlide++;
		curSlide %= $slider_list.length;
	})
	
})
</script>

<br>

<c:import url="/WEB-INF/views/layout/footer.jsp" />