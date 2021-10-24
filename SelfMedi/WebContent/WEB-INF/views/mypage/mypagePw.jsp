<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
<div id="userPwUpdate">
	<br>
	<label class="pwLabel" style="font-size : 0.8em;">Self-Medi 로그인 시 사용하는 비밀번호는 변경할 수 있습니다. 주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요</label>
	<label class="pwLabelExplain" style="display:none; font-size : 0.8em;">현재 비밀번호</label><br>
	<input type="text" class="oldPw" name="oldPw" style="display:none;" /><br><br>
	<label class="pwLabelExplain" style="display:none; font-size : 0.8em;">새 비밀번호</label><br>
	<input type="text" class="newPw" name="newPw" style="display:none;"/><br>
	<label class="pwLabelExplain" style="display:none; font-size : 0.8em;">새 비밀번호 확인</label><br>
	<input type="text" class="newPwCheck" name="newPwCheck" style="display:none;"/><br>
	<button class="btnPwUpdate" style="display:none;">확인</button>
	<button class="btnPwUpdateCancel" style="display:none; background-color : #e0e0e0; color : black"">취소</button>
			
	<button class="pwUpdate">변경</button><br><br>
</div>
    