<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/admin/layout/header.jsp" />

<%-- 여기 header와 paging, footer사이가 본문코드 작성영역입니다. --%>
<div class="membercontainer">

	<div class="boardtitle">
		<h2 style="margin:0px auto;padding-top: 30px; text-align: center; color: black;">회원 관리</h2>
		<hr>
	</div>

	<table class="table table-striped table-hover table-condensed" style="width:800px;margin:0 auto;">

		<!-- 		<thead> -->
		<tr>
			<th style="text-align: center;">회원번호</th>
			<th style="text-align: center;">이름</th>
			<th style="text-align: center;">닉네임</th>
			<th style="text-align: center;">가입 날짜</th>
		</tr>
		<!-- 		</thead> -->
		<tbody>
			<c:forEach items="${memberList }" var="member">
				<tr>
					<td>${member.userNo }</td>
					<td><a href="/ad/member/view?userNo=${member.userNo }">
							${member.userName } </a></td>
					<%-- 	<td>${member.userid }</td> --%>
					<td>${member.userNick }</td>
					<td>${member.joinDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="/WEB-INF/views/layout/pagingMember.jsp" />

</div>


<c:import url="/WEB-INF/views/admin/layout/footer.jsp" />