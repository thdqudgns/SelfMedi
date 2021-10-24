<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<style type="text/css">
#selftestcontainer{text-align: center;}
table {margin:auto;}
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td {
	border-style:solid;
	border-width:0px;
	font-family:Arial, sans-serif;
	font-size:14px;
	overflow:hidden;
  	padding:5px 10px;
 	word-break:normal;
}
.tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
.tg .tg-0pkyt{border-color:inherit;text-align:left;vertical-align:top;font-size: 15px;font-weight: bold;}

.question{display:inline;}

</style>

<div id="selftestcontainer">

<h1 style="color:#345EE6;"><b>자가진단서 작성</b></h1>
<hr>

<form action="<%=request.getContextPath() %>/selftest/write" method="post">
<input type="hidden" name="userno" value="${userNo }" />

<table class="tg" style="undefined;table-layout: fixed; width: 307px">
<colgroup>
<col style="width: 80px">
<col style="width: 227px">
</colgroup>
<tbody>
  <tr>
    <td class="tg-c3ow"><label for="name"> 이름 </label></td>
    <td class="tg-0pky"><input type="text" name="name" id="name" placeholder="이름을 입력하세요" /></td>
  </tr>
  <tr>
    <td class="tg-c3ow"><label for="age"> 연령대 </label></td>
    <td class="tg-0pky">
    <select name="age" id="age">
		<option value="youth">청소년</option>
		<option value="adult" selected>성인</option>
		<option value="prime">중장년</option>
	</select>
	</td>
  </tr>
  <tr>
    <td class="tg-c3ow"><label>성별</label></td>
    <td class="tg-0pky">
    <div id="gender" style="display:inline;">
		<input type="radio" name="gender" id="genderM" value="Male" checked="checked"><label for="genderM">남</label>&nbsp;&nbsp;&nbsp;
		<input type="radio" name="gender" id="genderF" value="Female"><label for="genderF">여</label> 
	</div>
	</td>
  </tr>
</tbody>
</table>

<%-- 
<label for="name"> 이름 </label>
<input type="text" name="name" id="name" placeholder="이름을 입력하세요" /><br>

<label for="age"> 연령대 </label>
	<select name="age" id="age">
		<option value="youth">청소년</option>
		<option value="adult" selected>성인</option>
		<option value="prime">중장년</option>
	</select><br>
	
성별 
	<div id="gender" style="display:inline;">
		<input type="radio" name="gender" id="genderM" value="Male" checked="checked"><label for="genderM">남</label> 
		<input type="radio" name="gender" id="genderF" value="Female"><label for="genderF">여</label> 
	</div>
--%>
	
<br>

<table class="tg" style="undefined;table-layout: fixed; width: 650px">
<colgroup>
<col style="width: 490px">
<col style="width: 160px">
</colgroup>
<tbody>
  <tr>
    <td class="tg-0pkyt">1. 심장질환 예방 및 혈압을 낮춰야 하십니까?<%-- omega3 오메가3 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q1" id="yes1" value="Need"><label for="yes1">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q1" id="no1" value="No" checked="checked"><label for="no1">아니오</label>
	</div>
	</td>
  </tr>
  <tr>
    <td class="tg-0pkyt">2. 눈 건강을 챙기길 원하십니까?<%-- lutein 루테인 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q2" id="yes2" value="Need"><label for="yes2">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q2" id="no2" value="No" checked="checked"><label for="no2">아니오</label>
	</div>
    </td>
  </tr>
  <tr>
    <td class="tg-0pkyt">3. 장에 문제가 있다고 느껴지십니까?<%-- lactic 유산균 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q3" id="yes3" value="Need"><label for="yes3">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q3" id="no3" value="No" checked="checked"><label for="no3">아니오</label>
	</div>
    </td>
  </tr>
  <tr>
    <td class="tg-0pkyt">4. 피부 건강 유지 및 피부 보습이 필요하십니까?<%-- collagen 콜라겐 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q4" id="yes4" value="Need"><label for="yes4">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q4" id="no4" value="No" checked="checked"><label for="no4">아니오</label>
	</div>
    </td>
  </tr>
  <tr>
    <td class="tg-0pkyt">5. 골다공증 등 뼈 건강에 문제가 있으시거나 예방을 원하십니까?<%-- vitaminD 비타민D --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q5" id="yes5" value="Need"><label for="yes5">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q5" id="no5" value="No" checked="checked"><label for="no5">아니오</label>
	</div>
    </td>
  </tr>
  <tr>
    <td class="tg-0pkyt">6. 신경과 근육 기능의 유지가 필요하십니까?<%-- magnesium 마그네슘 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q6" id="yes6" value="Need"><label for="yes6">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q6" id="no6" value="No" checked="checked"><label for="no6">아니오</label>
	</div>
    </td>
  </tr>
  <tr>
    <td class="tg-0pkyt">7. 면역기능 강화가 필요하십니까?<%-- redGinseng 홍삼 --%></td>
    <td class="tg-0pky">
    <div class="question">
	<input type="radio" name="q7" id="yes7" value="Need"><label for="yes7">네</label>&nbsp;&nbsp;&nbsp;
	<input type="radio" name="q7" id="no7" value="No" checked="checked"><label for="no7">아니오</label>
	</div>
    </td>
  </tr>
</tbody>
</table>

<%-- 1. 심장질환 예방 및 혈압을 낮춰야 하십니까?
omega3 오메가3
<div class="question">
	<input type="radio" name="q1" id="yes1" value="Need"><label for="yes1">네</label>
	<input type="radio" name="q1" id="no1" value="No" checked="checked"><label for="no1">아니오</label>
</div>
<br>
2. 눈 건강을 챙기길 원하십니까?
<!-- lutein 루테인 -->
<div class="question">
	<input type="radio" name="q2" id="yes2" value="Need"><label for="yes2">네</label>
	<input type="radio" name="q2" id="no2" value="No" checked="checked"><label for="no2">아니오</label>
</div>
<br>

3. 장에 문제가 있다고 느껴지십니까?
<!-- lactic 유산균 -->
<div class="question">
	<input type="radio" name="q3" id="yes3" value="Need"><label for="yes3">네</label>
	<input type="radio" name="q3" id="no3" value="No" checked="checked"><label for="no3">아니오</label>
</div>
<br>

4. 피부 건강 유지 및 피부 보습이 필요하십니까?
<!-- collagen 콜라겐 -->
<div class="question">
	<input type="radio" name="q4" id="yes4" value="Need"><label for="yes4">네</label>
	<input type="radio" name="q4" id="no4" value="No" checked="checked"><label for="no4">아니오</label>
</div>
<br>

5. 골다공증 등 뼈 건강에 문제가 있으시거나 예방을 원하십니까?
<!-- vitaminD 비타민D -->
<div class="question">
	<input type="radio" name="q5" id="yes5" value="Need"><label for="yes5">네</label>
	<input type="radio" name="q5" id="no5" value="No" checked="checked"><label for="no5">아니오</label>
</div>
<br>

6. 신경과 근육 기능의 유지가 필요하십니까?
<!-- magnesium 마그네슘 -->
<div class="question">
	<input type="radio" name="q6" id="yes6" value="Need"><label for="yes6">네</label>
	<input type="radio" name="q6" id="no6" value="No" checked="checked"><label for="no6">아니오</label>
</div>
<br>

7. 면역기능 강화가 필요하십니까?
<!-- redGinseng 홍삼 -->
<div class="question">
	<input type="radio" name="q7" id="yes7" value="Need"><label for="yes7">네</label>
	<input type="radio" name="q7" id="no7" value="No" checked="checked"><label for="no7">아니오</label>
</div>
<br>
 --%>

	<button type="reset" id="btnCancel" class="btn btn-info btn-sm">취소</button>
	<button type="button" id="btnWrite" class="btn btn-primary btn-sm">작성</button>

</form>

</div>

<%-- <button onclick='location.href="<%=request.getContextPath() %>/selftest/list";'>취소</button> --%>
<!-- <button>작성</button> -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />