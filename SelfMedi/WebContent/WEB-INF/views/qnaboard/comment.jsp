<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<c:forEach items="${commentList }" var="comment">
	<div data-commentNo="${comment.commentNo }">
			<label>${comment.userNick }</label><br>
		<label class="oldComment">${comment.commentContent }</label>
		<input type="text" class="newComment" name="newComment" style="display:none;" value="${comment.commentContent }" />
		<button class="btnCommentUpdate" style="display:none;">확인</button>
		<button class="btnCommentUpdateCancel" style="display:none;">취소</button><br>
		<label>${comment.commentDate }</label><br>
<%-- 		<a href="/QnA/Comment/Delete/?commentNo=${comment.commentNo }">삭제</a> --%>
<%-- 		<a href="/QnA/Comment/Update?commentNo=${comment.commentNo }">수정</a> --%>
		<span class="commentDelete">삭제</span>
		<span class="commentUpdate">수정</span>
<!-- 			<button  class="commentDelete">삭제</button> -->
<!-- 			<button  class="commentUpdate">수정</button> -->
		<hr>
	</div>
</c:forEach>


