<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
<div id="nicknameUpdate">
	<br>
	<label class="oldNick" style="font-weight : bold;">${member.userNick }님</label><br>
	<input type="text" class="newNick" name="newNick" style="display:none;" value="${member.userNick }" /><br>
	<button class="btnNickUpdate" style="display:none;">확인</button>
	<button class="btnNickUpdateCancel" style="display:none; background-color : #e0e0e0; color : black">취소</button><br>
			
	<button class="NickUpdate">변경</button><br><br>
</div>