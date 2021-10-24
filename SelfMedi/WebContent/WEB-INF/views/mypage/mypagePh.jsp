<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
<div id="userPhUpdate">
	<br>
	<label class="oldPh" style="font-weight : bold;">${member.userPh }</label><br>
	<label class="phLabel" style="font-size : 0.8em;">아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 네이버로부터 알림을 받을때 사용합니다</label>
	<label class="phLabelExplain" style="font-size : 0.8em; display:none;">새 휴대전화 번호</label><br>
	<input type="text" class="newPh" name="newPh" style="display:none;" value="${member.userPh }" /><br>
	<button class="btnPhUpdate" style="display:none;">확인</button>
	<button class="btnPhUpdateCancel" style="display:none; background-color : #e0e0e0; color : black"">취소</button><br>
			
	<button class="PhUpdate">변경</button><br><br>
</div>