<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<div id="userEmUpdate">
	<br>
	<label class="oldEm" style="font-weight : bold;">${member.userEm }</label><br>
	<label class="emLabel" style="font-size : 0.8em;">Self-Medi 서비스의 변경/종료 등 Self-Medi의 대부분 안내에 사용할 이메일 주소입니다</label>
	<label class="emLabelExplain" style="font-size : 0.8em; display:none;">새 이메일 주소</label><br>
	<input type="text" class="newEm" name="newEm" style="display:none;" value="${member.userEm }" /><br>
	<button class="btnEmUpdate" style="display:none;">확인</button>
	<button class="btnEmUpdateCancel" style="display:none; background-color : #e0e0e0; color : black"">취소</button><br>
			
	<button class="EmUpdate">변경</button><br><br>
</div>