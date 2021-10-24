<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<style type="text/css">
#container {
	padding: 0px 0px 120px;
	width:100%;
}
.pageAticle {
  	width: 1050px;
    margin: 0 auto;
}
.main {
    margin: 0px 60px 0px -20px;
    padding: 0px 20px 0px 20px;
    width:100%;
}

.freetg  {border-collapse:collapse;border-bottom:1px solid #ccc;border-spacing:0;width:800px;}
.freetg td{font-size:14px;overflow:hidden;padding:10px 5px;word-break:normal;}
.freetg th{font-size:14px;font-weight:normal;overflow:hidden;word-break:normal;}
.freetg .freetg-cly1{text-align:left;vertical-align:middle;width:100%;}
.freetg .freetg-0lax{text-align:left;font-weight:bold;padding-left:15px;vertical-align:middle;background-color: #3E8DDA;width:130px;color:#FFF;}
#freetable {width:1050px;}
#freetable > table {margin:0 auto;text-align: center;/* width:100%; */}
.freetg tr {border-bottom:1px solid #ccc;}
#tableBar{border-top: 3px solid #345EE6; margin-bottom: 0px;}
.photo {list-style-type: none;}
/* li.photo{width:100%;} */

.freecontent {border:0px solid #FFF;width:1050px;}
.contentbutton {text-align: right;}
#contenthr{color:rgba(0,0,0,0.3);}

#freeComNick{color:black; font-weight: bold;font-size:20px;/* margin-bottom:3px; */}
#freeComCon{font-size: 15px;color:black;}
#freeComConUp{font-size: 15px;color:black;}
#freeComDate{font-size: 13px;color:black;}
</style>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/qna/list");
	});
	
	//신고버튼 동작
	$("#btnDeclare").click(function() {
		$(location).attr("href", "/qna/declare?boardNo=${viewBoard.boardNo }");
	});

	//수정버튼 동작
	$("#btnUpdate").click(function() {

		$(location).attr("href","/qna/update?boardNo=${viewBoard.boardNo }");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if (confirm("게시글을 삭제하시겠습니까?")) {
			$(location).attr("href","/qna/delete?boardNo=${viewBoard.boardNo }");
		}
	});
});



//댓글 관련
$(document).ready(function() {
	$(document.body).on("click", ".commentDelete", function() {
		var th = $(this);
		console.log("--",th.parent().children(".newComment").val())
		//----- 전달 파라미터 구성 -----
		var boardNo = ${viewBoard.boardNo};
		var commentNo = th.parent().attr("data-commentNo")
		var params = "&boardNo=" + boardNo + "&commentNo=" + commentNo;

		console.log(params)
		
		//----- URL 구성 ----
		var url = "/qna/comment/delete/";

		//----- AJAX 요청 전송 -----
		sendRequest("GET", url, params, callback);
	});
	
	$(document.body).on("click", ".commentUpdate", function() {
//		console.log($(this).parent().attr('data-commentNo'))
		
		$(this).parent().children(".oldComment").toggle()
		
		$(this).parent().children(".newComment").toggle()
		$(this).parent().children(".btnCommentUpdate").toggle()
		$(this).parent().children(".btnCommentUpdateCancel").toggle()
	});
	
	$(document.body).on("click", ".btnCommentUpdate", function() {
		
		var th = $(this);
		console.log("--",th.parent().children(".newComment").val())
		
		//----- 전달 파라미터 구성 -----
		var boardNo = ${viewBoard.boardNo};
		var commentNo = th.parent().attr("data-commentNo")
		var commentContent = th.parent().children(".newComment").val()
		var params = "&boardNo=" + boardNo + "&commentNo=" + commentNo + "&commentContent=" + commentContent;

		console.log(params)
		
		//----- URL 구성 ----
		var url = "/qna/comment/update";

		//----- AJAX 요청 전송 -----
		sendRequest("GET", url, params, callback);
	})
	
	$(document.body).on("click", ".btnCommentUpdateCancel", function() {
		$(this).parent().children(".oldComment").toggle()
		
		$(this).parent().children("[name='newComment']").toggle()
		$(this).parent().children(".btnCommentUpdate").toggle()
		$(this).parent().children(".btnCommentUpdateCancel").toggle()
		
	})
	
});


//AJAX 요청 보내는 메소드, 댓글

function send() {	
	//----- 전달 파라미터 구성 -----
	var boardNo = ${viewBoard.boardNo};
	var content = $("#content").val();
	var params = "&boardNo=" + boardNo + "&content=" + content;

	console.log(params)
	
	//----- URL 구성 ----
	var url = "/qna/comment/write";

	//----- AJAX 요청 전송 -----
	sendRequest("GET", url, params, callbacksend);
	
}

//AJAX 응답 처리하는 콜백함수
function callback() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			console.log("AJAX 정상 응답")

			//정상응답 처리 함수
			appendResult();

		} else {
			console.log("AJAX 요청/응답 에러")
		}
	}
}

//AJAX 응답 처리하는 콜백함수
function callbacksend() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			console.log("AJAX 정상 응답")

			//정상응답 처리 함수
			appendsendResult();

		} else {
			console.log("AJAX 요청/응답 에러")
		}
	}
}

//정상 응답 후 응답데이터 처리하는 함수
function appendResult() {
	commentList.innerHTML = httpRequest.responseText;
}

//정상 응답 후 응답데이터 처리하는 함수
function appendsendResult() {
	commentList.innerHTML = httpRequest.responseText;
	content.value = "";
}

//url복사
function copy(val) {
 		var dummy = document.createElement("textarea");
		 document.body.appendChild(dummy);
	  dummy.value = val;
	  dummy.select();
 		document.execCommand("copy");
 		document.body.removeChild(dummy);
}
	
</script>

<div class="container">

	<div class="pageAticle">
		<div class="main">
			<div id="freetable">
			<hr id="tableBar">

	<table class="freetg" style="table-layout: fixed;">
					<thead>
					<tr>
						<th class="freetg-0lax">제목</th>
						<td class="freetg-cly1" colspan="3" style="width:920px;">${viewBoard.boardTitle }</td>
					</tr>
				</thead>
									<tbody>
						<tr>
							<th class="freetg-0lax">작성자</th>
							<td class="freetg-cly1" colspan="3" style="width: 920px;">${viewBoard.userId }</td>
						</tr>
						<tr>
							<th class="freetg-0lax">작성일</th>
							<td class="freetg-cly1" style="width: 920px;">${viewBoard.boardDate }</td>
						</tr>
						<tr>
							<th class="freetg-0lax">조회수</th>
							<td class="freetg-cly1" style="width: 920px;">${viewBoard.hit }회</td>
						</tr>
						<tr>
							<td class="freetg-cly1" colspan="4">${viewBoard.boardContent }</td>
						</tr>
					</tbody>
			</table>
	</div>
	<!-- 첨부파일 -->
	<div>
		<c:if test="${not empty boardFile }">
			<li class="photo"><img src="/upload/${boardFile.storedName }"
				style="max-width: 100%; height: auto" /></li>
			<a href="/upload/${boardFile.storedName }"
				download="${boardFile.originName }">${boardFile.originName }</a>
		</c:if>
	</div>
	<br>
	<div class="contentbutton">
		<button id="btnList" class="btn btn-primary btn-sm">목록</button>
		<c:if test="${not empty login and login}">
		<button id="btnDeclare" class="btn btn-warning btn-sm">신고</button>
		</c:if>
		<button class="btn btn-info btn-sm" onclick="copy('http://localhost:8088/free/view?boardNo='+${viewFree.boardNo })">URL복사</button>
		<c:if test="${viewBoard.userNo eq userNo }">
			<button id="btnUpdate" class="btn btn-info btn-sm">수정</button>
			<button id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
		</c:if>
	</div>

	<h4>댓글</h4>
	<br>
	<hr id="contenthr">

	<div id="commentList">
		<c:forEach items="${commentList }" var="comment">
		<div data-commentNo="${comment.commentNo }">
			<label>${comment.userNick }</label><br>
			<label class="oldComment">${comment.commentContent }</label>
			<input type="text" class="newComment" name="newComment" style="display:none;" value="${comment.commentContent }" />
			<button class="btnCommentUpdate" style="display:none;">확인</button>
			<button class="btnCommentUpdateCancel" style="display:none;">취소</button><br>
			<label>${comment.commentDate }</label><br>
			
			<c:if test="${comment.userNo eq userNo}">
				<span class="commentDelete">삭제</span>
				<span class="commentUpdate">수정</span>
			</c:if>
			
			<hr>
		</div>
		</c:forEach>
	</div>

	<div class="freecontent">
		<c:if test="${not empty userNo }">
			<c:if test="${userKind eq 2}">
				<label>${userNick }<br> <textarea placeholder="댓글을 입력하세요."
				style="width: 1050px; height: 150px; resize: none;" id="content"></textarea>
				</label>
				<br>
				<div class="contentbutton">
				<button onclick="send();" class="btn btn-secondary">댓글 달기</button>
				</div>
			</c:if>
		</c:if>
	</div>
</div>
</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
