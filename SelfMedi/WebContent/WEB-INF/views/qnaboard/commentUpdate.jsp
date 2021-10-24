<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>

<h3>댓글 수정!</h3>

<form action="/qna/Comment/Update" method="POST">
<input type="hidden" name="commentNo" value="${updateComment.commentNo}" />

${userNick }<br>
<textarea id="content" name="content">${updateComment.commentContent}</textarea><br>

<div class="text-center">	
	<input type="submit" value="수정">
	<button type="button" id="btnCancel">취소</button>
</div>

</form>


<c:import url="/WEB-INF/views/layout/footer.jsp" />
