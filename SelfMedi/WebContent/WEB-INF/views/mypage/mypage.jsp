<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var $test = $(".testList");
		
	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("삭제하시겠습니까?") ) {
			$(location).attr("href", "/mypage/selftest/delete?selftestNo=${testInfo.selftestNo }");
		}
	});	
});

function update() {	
		console.log("수정");
		location.replace("/mypage/update");
}
</script>
<style>
/* 유저 정보 수정 버튼 */
button.mypageUpdatebtn{
	float : right;
	width : 70px;
	height : 30px;
	border-radius: 10px;
	background-color : #345EE6;
	color : white;
	border : 0;
	outline : 0;
/* 	border : 1px solid red; */
}

/* 자가진단 */
table.userSelfTest{
	width: 97%;
	border-collapse: collapse;
    margin : 5px 10px 10px 10px;
}

th.selfTEST{
    padding: 10px;
    font-weight : bold;
	background-color: #8e8e8e;
	font-size : 1em;
	color : white;
}
td.selftTEST{
    padding: 10px;
    font-weight : bold;
	font-size : 1em;
}

button.userSelfTestDelete{
	width : 70px;
	margin : 5% 0 5% 0;
	height : 30px;
	background-color : e8e8e8;
	color : 5e5e5e;
	border-radius: 10px;
	border : 0;
	outline : 0;
}

/* 게시글 */
table.mypage{
	width: 97%;
	border-collapse: collapse;
    margin : 5px 10px 10px 10px;
}

th.mypage{
    padding: 10px;
    font-weight : bold;
	background-color: #8e8e8e;
	font-size : 1em;
	color : white;
}
td.mypage{
    padding: 10px;
	border-bottom: 1px solid #8e8e8e;
    font-weight : bold;
	font-size : 1em;
}


</style>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<!-- 유저 정보 -->
<div style="width : 70%; margin :0 0 0 15%; clear : both;">
<img src = "https://i.imgur.com/kdzYdO8.png" alt ="user Icon" style="width : 25%; margin : 3% 3% 3% 3%; float : left;">
<div style="width : 60%; margin : 5% 0 0 0%; float : left;">
	<label style="font-size : 1.4em; font-weight : bold; float : left;">${member.userNick }님</label>
	<button class="mypageUpdatebtn" onclick="update();">수정</button>
	<br>
	<hr style="border: solid 1px #5e5e5e;">
	<span style="font-size : 1em; font-weight : bold; float : left; color : #5e5e5e;">
		<label>Id : ${member.userId }</label><br>
		<label>Email : ${member.userEm }</label><br>
		<label>Phone : ${member.userPh }</label><br>
		<c:if test="${member.userKind eq 2 }">
			<label>사업자 번호 : ${member.businessNo }</label><br>
		</c:if>
	</span>
</div>
</div>

<hr style="width : 80%;  margin : 3% 0 0 10%; border: solid 1px  #345EE6; background-color :  #345EE6;clear : both;">

<!-- selfTest 정보 -->
<div style="width : 70%; margin : 3% 0 0 15%; clear : both;">
<label style="font-size : 1.4em; font-weight : bold; color : black; margin : 10px 0 0 15px;">자가진단 목록</label>

<table class="userSelfTest">
<thead>
<tr>
	<th class="SelfTEST" style="text-align: center;">작성 이름</th>
	<th class="SelfTEST" style="text-align: center;">나이대</th>
	<th class="SelfTEST" style="text-align: center;">성별</th>
	<th class="SelfTEST" style="text-align: center;">삭제</th>
</tr>
</thead>

<tbody>
<c:forEach items="${selfTestList }" var="testInfo" begin="0" end="4">
<tr class="testList" style="border-bottom : 1px solid #8e8e8e;">
	<td class="SelfTEST">
		<a href="/selftest/view?selftestNo=${testInfo.selftestNo }">
			${testInfo.userName }
		</a>
	</td>
	<td class="SelfTEST">
	<c:if test="${testInfo.userAge eq 'youth'}">청소년</c:if> 
	<c:if test="${testInfo.userAge eq 'adult'}">성인</c:if> 
	<c:if test="${testInfo.userAge eq 'prime'}">중장년</c:if>
	</td>
	<td class="SelfTEST">
	<c:if test="${testInfo.userGender eq 'Male'}">남성</c:if> 
	<c:if test="${testInfo.userGender eq 'Female'}">여성</c:if>
	</td>
	<td><button class="userSelfTestDelete" onclick='location.href="<%=request.getContextPath() %>/mypage/selftest/delete?selftestNo=${testInfo.selftestNo }";'>삭제</button></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>

<!-- 공지사항 -->
<c:if test="${not empty NoticeList }">

<hr style="width : 80%;  margin : 3% 0 0 10%; border: solid 1px  #345EE6; background-color :  #345EE6;clear : both;">

<div class="NoticeBOARD" style="width : 70%; margin : 3% 0 0 15%; clear : both;">
<label style="font-size : 1.4em; font-weight : bold; color : black; margin : 10px 0 0 15px;">공지사항 게시글 작성 목록</label>

<table class="mypage">
<tr>
	<th class="mypage" style="text-align: center;">글번호</th>
	<th class="mypage" style="text-align: center;">작성자</th>
	<th class="mypage" style="text-align: center;">제목</th>
	<th class="mypage" style="text-align: center;">작성일</th>
	<th class="mypage" style="text-align: center;">조회수</th>
</tr>
<c:forEach items="${NoticeList }" var="notice">
<tr>
	<td class="mypage">${notice.boardNo }</td>
	<td class="mypage">${notice.userNick }</td>
	<td class="mypage">
		<a href="/notice/view?boardNo=${notice.boardNo }">
		${notice.boardTitle }
		</a>
	</td>
	<td class="mypage">${notice.boardDate }</td>
	<td class="mypage">${notice.hit }</td>
</tr>
</c:forEach>

</table>
</div>
</c:if>

<!-- 자유게시판 -->

<hr style="width : 80%;  margin : 3% 0 0 10%; border: solid 1px  #345EE6; background-color :  #345EE6;clear : both;">

<c:if test="${not empty FreeList }">
<div class="FreeBOARD" style="width : 70%; margin : 3% 0 0 15%; clear : both;">
<label style="font-size : 1.4em; font-weight : bold; color : black; margin : 10px 0 0 15px;">자유 게시글 작성 목록</label>

<table class="mypage">
<tr>
	<th class="mypage" style="text-align: center;">글번호</th>
	<th class="mypage" style="text-align: center;">작성자</th>
	<th class="mypage" style="text-align: center;">제목</th>
	<th class="mypage" style="text-align: center;">작성일</th>
	<th class="mypage" style="text-align: center;">조회수</th>
</tr>
<c:forEach items="${FreeList }" var="free">
<tr>
	<td class="mypage">${free.boardNo }</td>
	<td class="mypage">${free.userNick }</td>
	<td class="mypage">
		<a href="/free/view?boardNo=${free.boardNo }">
		${free.boardTitle }
		</a>
	</td>
	<td class="mypage">${free.boardDate }</td>
	<td class="mypage">${free.hit }</td>
</tr>
</c:forEach>

</table>
</div>
</c:if>

<!-- QnA게시판 -->
<c:if test="${not empty QnAList }">

<hr style="width : 80%;  margin : 3% 0 0 10%; border: solid 1px  #345EE6; background-color :  #345EE6;clear : both;">

<div class="QnABOARD" style="width : 70%; margin : 3% 0 0 15%; clear : both;">
<label style="font-size : 1.4em; font-weight : bold; color : black; margin : 10px 0 0 15px;">QnA 게시글 작성 목록</label>

<table class="mypage">
<tr>
	<th class="mypage" style="text-align: center;">글번호</th>
	<th class="mypage" style="text-align: center;">작성자</th>
	<th class="mypage" style="text-align: center;">제목</th>
	<th class="mypage" style="text-align: center;">작성일</th>
	<th class="mypage" style="text-align: center;">조회수</th>
</tr>
<c:forEach items="${QnAList }" var="qna">
<tr>
	<td class="mypage">${qna.boardNo }</td>
	<td class="mypage">${qna.userNick }</td>
	<td class="mypage">
		<a href="/qna/view?boardNo=${qna.boardNo }">
		${qna.boardTitle }
		</a>
	</td>
	<td class="mypage">${qna.boardDate }</td>
	<td class="mypage">${qna.hit }</td>
</tr>
</c:forEach>
</table>
</div>
</c:if>

<hr style="width : 80%;  margin : 3% 0 0 10%; border: solid 1px  #345EE6; background-color :  #345EE6;clear : both;">

<c:import url="/WEB-INF/views/layout/footer.jsp" />