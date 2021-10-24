<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnMain").click(function() {
		$(location).attr("href", "/main");
	});
	
	//추가버튼 동작
	var $test = $(".testList");
	
	if( $test.length < 5 ) {
	$("#btnAdd").click(function() {
		$(location).attr("href", "/selftest/write");
	})};

	if( $test.length >= 5 ) {
	$("#btnAdd").click(function() {
		if( confirm("결과지는 5개만 조회됩니다. 하나를 삭제해주세요.") ) {
			history.go(0);
		}
	})};
	
	//삭제버튼 동작
	$(document).on("click", "#btnDelete", function() {
		if( confirm("삭제하시겠습니까?") ) {
			$(location).attr("href", "/selftest/delete?selftestNo=${testInfo.selftestNo }");
		}
	});
	
});
</script>

<style type="text/css">
#selftestcontainer{text-align: center;}
table {border-collapse: collapse;border: 1px solid #ccc;width:600px;margin: 0 auto;}
th {color:#fff;padding: 10px;background-color:#3E8DDA;text-align: center;}
td {padding: 10px;text-align: center;}
tr:nth-child(odd){background-color: #EEE;}
td:hover {background-color: highlight;}
</style>

<div id="selftestcontainer">

<h1 style="color:#345EE6;"><b>자가진단 목록</b></h1>
<hr>

	<table>

		<thead>
			<tr>
				<th>작성 이름</th>
				<th>나이대</th>
				<th>성별</th>
				<th>삭제여부</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${myList }" var="testInfo" begin="0" end="4">
				<tr class="testList">
					<td><a
						href="/selftest/view?selftestNo=${testInfo.selftestNo }">
							${testInfo.userName } </a></td>
					<td><c:if test="${testInfo.userAge eq 'youth'}">청소년</c:if> <c:if
							test="${testInfo.userAge eq 'adult'}">성인</c:if> <c:if
							test="${testInfo.userAge eq 'prime'}">중장년</c:if></td>
					<td><c:if test="${testInfo.userGender eq 'Male'}">남성</c:if> <c:if
							test="${testInfo.userGender eq 'Female'}">여성</c:if></td>
						<td><button class="btn bnt-danger btn-sm" onclick='location.href="<%=request.getContextPath() %>/selftest/delete?selftestNo=${testInfo.selftestNo }";'>삭제</button></td>
<!-- 					<td><button type="button" id="btnDelete">삭제</button></td> -->
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<br><br>

	<button type="button" id="btnMain" class="btn btn-primary btn-sm">메인 페이지</button>
	<button type="button" id="btnAdd" class="btn btn-info btn-sm">자가진단 추가</button>
<br><br>

<%-- <button onclick='location.href="<%=request.getContextPath() %>/main";'>메인 페이지</button>
<button onclick='location.href="<%=request.getContextPath() %>/selftest/write";'>자가진단 추가 작성</button>
--%>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />