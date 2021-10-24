<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//메인버튼 동작
	$("#btnMain").click(function() {
		$(location).attr("href", "/main");
	});
	
	//목록 복귀버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/selftest/list");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("삭제하시겠습니까?") ) {
			$(location).attr("href", "/selftest/delete?selftestNo=${testInfo.selftestNo }");
		}
	});
	
});
</script>

<style type="text/css">
#selftestcontainer{text-align: center;}
table.selftesttg{margin: 0 auto;}
#selftesttitle {padding-top:10px;color:#345EE6;}
.selftesttg  {border-collapse:collapse;border-spacing:0;}
.selftesttg td{border-style:solid;border-width:0px;font-family:Arial, sans-serif;font-size:14px;overflow:hidden;
  padding:5px 10px;word-break:normal;}
.selftesttg th{border-style:solid;border-width:0px;font-family:Arial, sans-serif;font-size:14px;font-weight:normal;
  overflow:hidden;padding:5px 10px;word-break:normal;}
.selftesttg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top; padding: 20px 10px;}
.selftesttg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top; padding: 20px 10px;}
strong{color:tomato;}
.resulttitle{background-color:rgba(62,141,218,45%); display: inline-block; font-size: 20px; font-weight: bold; padding:30px 15px; border-radius: 20px 20px 20px 20px;}
</style>

<div id="selftestcontainer">

<h1 id="selftesttitle"><b>자가진단 결과</b></h1>
<hr>

<%-- 컨텍스트 영역에서 데이터를 가져와서 jstl로 core를 이용하여 <c:if test="${test.ques1 eq 'Y'}"> 처럼 써서,
'Y'로 저장된 컬럼 하나에 대응하는 상품의 링크를 <a>로 연결한다. --%>
<div class="resulttitle">
이름 : ${testInfo.userName }<br>
나이대 : 
<c:if test="${testInfo.userAge eq 'youth'}">청소년</c:if> 
<c:if test="${testInfo.userAge eq 'adult'}">성인</c:if> 
<c:if test="${testInfo.userAge eq 'prime'}">중장년</c:if><br> 
성별 : 
<c:if test="${testInfo.userGender eq 'Male'}">남성</c:if> 
<c:if test="${testInfo.userGender eq 'Female'}">여성</c:if>
</div>

<hr>

<table class="selftesttg" style="undefined;table-layout: fixed; width: 550px">

<colgroup>
<col style="width: 450px">
<col style="width: 100px">
</colgroup>

<tbody>
<c:if test="${testInfo.userAge eq 'youth' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 청소년이라면 성장에 도움이 되는 영양제를 드셔보세요.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?targetId=1">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.userAge eq 'adult' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 성인이라면 성인건강에 도움이 되는 영양제를 드셔보세요.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?targetId=2">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.userAge eq 'prime' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 중장년이시라면 노화방지에 도움이 되는 영양제를 드셔보세요.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?targetId=3">상품 이동</a></td>
  </tr>
</c:if> 

<c:if test="${testInfo.userGender eq 'Male'}">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 남성이시라면 다음 영양제를 드셔보세요.</td>
    <td class="tg-c3ow"><a href="/product/display">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.userGender eq 'Female'}">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 여성이시라면 다음 영양제를 드셔보세요.</td>
    <td class="tg-c3ow"><a href="/product/display">상품 이동</a></td>
  </tr>
</c:if> 

<c:if test="${testInfo.question01 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 첫번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 심장질환 예방과 혈행 개선에 도움이 되는 <strong><u>오메가3</u></strong>가 포함된<br>&nbsp;&nbsp;&nbsp; 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=1">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question02 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 두번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 눈 노화 방지 및 눈 건강에 도움이 되는 <strong><u>루테인</u></strong>이 포함된<br>&nbsp;&nbsp;&nbsp; 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=2">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question03 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 세번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 장 건강에 도움이 되는 <strong><u>유산균</u></strong>이 포함된 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=4">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question04 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 네번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 피부손상을 보호하고 피부 건강을 유지하는 데 도움이 되는<br>&nbsp;&nbsp;&nbsp; <strong><u>콜라겐</u></strong>이 포함된 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=5">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question05 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 다섯번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 뼈의 형성과 유지에 필요하고 골다공증을 예방하는 데<br>&nbsp;&nbsp;&nbsp; 도움이 되는 <strong><u>비타민D</u></strong>가 포함된 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=11">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question06 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 여섯번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 신경과 근육의 기능을 유지하는 데 도움이 되는 <strong><u>마그네슘</u></strong>이<br>&nbsp;&nbsp;&nbsp; 포함된 영양제를 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=8">상품 이동</a></td>
  </tr>
</c:if> 
<c:if test="${testInfo.question07 eq 'Need' }">
  <tr>
    <td class="tg-0pky">&bull;&nbsp; 일곱번째 질문에 '네' 라고 응답하셨군요.<br> &rArr; 면역 기능 강화에 도움이 되는 <strong><u>홍삼</u></strong>이 포함된 영양제를<br>&nbsp;&nbsp;&nbsp; 추천합니다.</td>
    <td class="tg-c3ow"><a href="http://localhost:8088/product/display?nutId=9">상품 이동</a></td>
  </tr>
</c:if> 
</tbody>

</table>

<%-- 
<c:if test="${testInfo.userAge eq 'youth' }">
	청소년이라면 성장에 도움이 되는 이 영양제를 드셔보세요.
	<a href="#">청소년 영양제</a>
</c:if>

<c:if test="${testInfo.userAge eq 'adult' }">
	성인이라면 성인건강에 도움이 되는 이 영양제를 드셔보세요.
	<a href="#">성인 영양제</a>
</c:if>

<c:if test="${testInfo.userAge eq 'prime' }">
	중장년이시라면 건강에 도움이 되는 이 영양제를 드셔보세요.
	<a href="#">중장년 영양제</a>
</c:if> --%>


<%-- ${testInfo.question01}<br>
${testInfo.question02}<br>
${testInfo.question03}<br>
${testInfo.question04}<br>
${testInfo.question05}<br>
${testInfo.question06}<br>
${testInfo.question07}<br>
<hr>

${testInfo.question01 eq 'Need' }<br>
${testInfo.question02 eq 'Need' }<br>
${testInfo.question03 eq 'Need' }<br>
${testInfo.question04 eq 'Need' }<br>
${testInfo.question05 eq 'Need' }<br>
${testInfo.question06 eq 'Need' }<br>
${testInfo.question07 eq 'Need' }<br>
<hr>

${testInfo.question01 ne 'No' }<br>
${testInfo.question02 ne 'No' }<br>
${testInfo.question03 ne 'No' }<br>
${testInfo.question04 ne 'No' }<br>
${testInfo.question05 ne 'No' }<br>
${testInfo.question06 ne 'No' }<br>
${testInfo.question07 ne 'No' }<br>
<hr> --%>

<%-- <c:if test="${testInfo.question01 eq 'Need' }">
	심장질환 예방과 혈행 개선에 도움이 되는 <strong>오메가3</strong>가 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크1</a>
	<hr>
</c:if>

<c:if test="${testInfo.question02 eq 'Need' }">
	눈 노화 방지 및 눈 건강에 도움이 되는 <strong>루테인</strong>이 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크2</a>
	<hr>
</c:if>

<c:if test="${testInfo.question03 eq 'Need' }">
	장 건강에 도움이 되는 <strong>유산균</strong>이 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크3</a>
	<hr>
</c:if>

<c:if test="${testInfo.question04 eq 'Need' }">
	피부손상을 보호하고 피부 건강을 유지하는 데 도움이 되는 <strong>콜라겐</strong>이 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크4</a>
	<hr>
</c:if>

<c:if test="${testInfo.question05 eq 'Need' }">
	뼈의 형성과 유지에 필요하고 골다공증을 예방하는 데 도움이 되는 <strong>비타민D</strong>가 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크5</a>
	<hr>
</c:if>

<c:if test="${testInfo.question06 eq 'Need' }">
	신경과 근육의 기능을 유지하는 데 도움이 되는 <strong>마그네슘</strong>이 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크6</a>
	<hr>
</c:if>

<c:if test="${testInfo.question07 eq 'Need' }">
	면역 기능 강화에 도움이 되는 <strong>홍삼</strong>이 포함된 영양제를 추천합니다.
	<a href="#">영양제 링크7</a>
	<hr>
</c:if> --%>
<br>

<button type="button" id="btnMain" class="btn btn-primary btn-sm">메인 페이지</button>
<button type="button" id="btnList" class="btn btn-info btn-sm">자가진단 목록</button>
<button type="button" id="btnDelete" class="btn btn-danger btn-sm">자가진단 삭제</button>
<%-- <button onclick='location.href="<%=request.getContextPath() %>/main";'>메인 페이지</button> --%>
<%-- <button onclick='location.href="<%=request.getContextPath() %>/selftest/list";'>자가진단 목록</button> --%>
<%-- <button onclick='location.href="<%=request.getContextPath() %>/selftest/delete?selftestNo=${testInfo.selftestNo }";'>자가진단 삭제</button> --%>

<br><br>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />