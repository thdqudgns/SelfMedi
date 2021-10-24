<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>: : : Self-Medi : : :</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript"src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/se2/js/service/httpRequest.js"></script>

<script type="text/javascript">
var updateNo = null;

$(document).ready(function() {
	//닉네임
	$(document.body).on("click", ".NickUpdate", function() {
//		console.log($(this).parent().attr('data-commentNo'))
		
// 		$(this).parent().children(".oldNick").toggle()
		$(this).parent().children(".newNick").toggle()
		$(this).parent().children(".btnNickUpdate").toggle()
		$(this).parent().children(".btnNickUpdateCancel").toggle()
		$(this).parent().children(".NickUpdate").toggle()
	});
		
	$(document.body).on("click", ".btnNickUpdate", function() {
		var th = $(this);
		
		if("${member.userNick }" == th.parent().children(".newNick").val()){
			if(confirm("변경하시려는 닉네임과 변경전 닉네임이 같습니다")){
				history.go(0);
			}
		}else{
			//----- 전달 파라미터 구성 -----
			updateNo = 1;
			var userNo = ${member.userNo};
			var userNick = th.parent().children(".newNick").val()
			var params = "&updateNo=" + updateNo + "&userNo=" + userNo + "&userNick=" + userNick;

			console.log(params)
			
			//----- URL 구성 ----
			var url = "/mypage/update";

			//----- AJAX 요청 전송 -----
			sendRequest("POST", url, params, callback);
		}
	})
	
	$(document.body).on("click", ".btnNickUpdateCancel", function() {
// 		$(this).parent().children(".oldNick").toggle()
		
		$(this).parent().children("[name='newNick']").toggle()
		$(this).parent().children(".btnNickUpdate").toggle()
		$(this).parent().children(".btnNickUpdateCancel").toggle()
		$(this).parent().children(".NickUpdate").toggle()
		
	})
	
	
	//비밀번호 수정
	$(document.body).on("click", ".pwUpdate", function() {
//		console.log($(this).parent().attr('data-commentNo'))
		
		$(this).parent().children(".pwLabel").toggle()
		$(this).parent().children(".pwLabelExplain").toggle()
		$(this).parent().children(".oldPw").toggle()
		$(this).parent().children(".newPw").toggle()
		$(this).parent().children(".newPwCheck").toggle()
		$(this).parent().children(".btnPwUpdate").toggle()
		$(this).parent().children(".btnPwUpdateCancel").toggle()
		$(this).parent().children(".pwUpdate").toggle()
	});
		
	$(document.body).on("click", ".btnPwUpdate", function() {
		var th = $(this);
		
		if(th.parent().children(".oldPw").val() != ${member.userPw}){
			if(confirm("현재 비밀번호를 정확하게 입력해주세요")){history.go(0);}
		}else{
			if(th.parent().children(".oldPw").val() == th.parent().children(".newPw").val()){
				if(confirm("현재 비밀번호와 변경하려는 비밀번호가 일치합니다")){
					history.go(0);
				}
			}else{
				if(th.parent().children(".newPw").val() != th.parent().children(".newPwCheck").val()){
					if(confirm("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다")){
						history.go(0);
					}
				}else{
					if(th.parent().children(".oldPw").val() == ${member.userPw} && th.parent().children(".oldPw").val() != th.parent().children(".newPw").val() && th.parent().children(".newPw").val() == th.parent().children(".newPwCheck").val()){			
						//----- 전달 파라미터 구성 -----
						updateNo = 2;
						var userNo = ${member.userNo};
						var userPw = th.parent().children(".newPw").val()
						var params = "&updateNo=" + updateNo + "&userNo=" + userNo + "&userPw=" + userPw;

						console.log(params)
						
						//----- URL 구성 ----
						var url = "/mypage/update";

						//----- AJAX 요청 전송 -----
						sendRequest("POST", url, params, callback);
					}//if
				}//else
			}//else
		}//if
	})//function
	
	$(document.body).on("click", ".btnPwUpdateCancel", function() {
		$(this).parent().children(".pwLabel").toggle()
		$(this).parent().children(".pwLabelExplain").toggle()
		$(this).parent().children(".oldPw").toggle()
		$(this).parent().children(".newPw").toggle()
		$(this).parent().children(".newPwCheck").toggle()
		$(this).parent().children(".btnPwUpdate").toggle()
		$(this).parent().children(".btnPwUpdateCancel").toggle()
		$(this).parent().children(".pwUpdate").toggle()
	})
	
	//이메일
	$(document.body).on("click", ".EmUpdate", function() {
//		console.log($(this).parent().attr('data-commentNo'))
		
		$(this).parent().children(".oldEm").toggle()
		$(this).parent().children(".emLabel").toggle()
		$(this).parent().children(".emLabelExplain").toggle()
		$(this).parent().children(".newEm").toggle()
		$(this).parent().children(".btnEmUpdate").toggle()
		$(this).parent().children(".btnEmUpdateCancel").toggle()
		$(this).parent().children(".EmUpdate").toggle()
	});
		
	$(document.body).on("click", ".btnEmUpdate", function() {
		
		var th = $(this);
		
		if("${member.userEm }" == th.parent().children(".newEm").val()){
			if(confirm("변경하시려는 이메일 주소와 변경전 이메일 주소가 같습니다")){
				history.go(0);
			}
		}else{
			//----- 전달 파라미터 구성 -----
			updateNo = 3;
			var userNo = ${member.userNo};
			var userEm = th.parent().children(".newEm").val()
			var params = "&updateNo=" + updateNo + "&userNo=" + userNo + "&userEm=" + userEm;

			console.log(params)
			
			//----- URL 구성 ----
			var url = "/mypage/update";

			//----- AJAX 요청 전송 -----
			sendRequest("POST", url, params, callback);	
		}
	})
	
	$(document.body).on("click", ".btnEmUpdateCancel", function() {
		$(this).parent().children(".oldEm").toggle()
		$(this).parent().children(".emLabel").toggle()
		$(this).parent().children(".emLabelExplain").toggle()
		$(this).parent().children(".newEm").toggle()
		$(this).parent().children(".btnEmUpdate").toggle()
		$(this).parent().children(".btnEmUpdateCancel").toggle()
		$(this).parent().children(".EmUpdate").toggle()
	})
	
	//휴대전화 번호
	$(document.body).on("click", ".PhUpdate", function() {
//		console.log($(this).parent().attr('data-commentNo'))
		
		$(this).parent().children(".oldPh").toggle()
		$(this).parent().children(".phLabel").toggle()
		$(this).parent().children(".phLabelExplain").toggle()
		$(this).parent().children(".newPh").toggle()
		$(this).parent().children(".btnPhUpdate").toggle()
		$(this).parent().children(".btnPhUpdateCancel").toggle()
		$(this).parent().children(".PhUpdate").toggle()
	});
		
	$(document.body).on("click", ".btnPhUpdate", function() {
		
		var th = $(this);
		
		if("${member.userPh }" == th.parent().children(".newPh").val()){
			if(confirm("변경하시려는 휴대전화 번호와 변경전 휴대전화 번호가 같습니다")){
				history.go(0);
			}
		}else{
			//----- 전달 파라미터 구성 -----
			updateNo = 4;
			var userNo = ${member.userNo};
			var userPh = th.parent().children(".newPh").val()
			var params = "&updateNo=" + updateNo + "&userNo=" + userNo + "&userPh=" + userPh;

			console.log(params)
			
			//----- URL 구성 ----
			var url = "/mypage/update";

			//----- AJAX 요청 전송 -----
			sendRequest("POST", url, params, callback);	
		}
	});
	
	$(document.body).on("click", ".btnPhUpdateCancel", function() {
		$(this).parent().children(".oldPh").toggle()
		$(this).parent().children(".phLabel").toggle()
		$(this).parent().children(".phLabelExplain").toggle()
		$(this).parent().children(".newPh").toggle()
		$(this).parent().children(".btnPhUpdate").toggle()
		$(this).parent().children(".btnPhUpdateCancel").toggle()
		$(this).parent().children(".PhUpdate").toggle()
	})
});

//AJAX 응답 처리하는 콜백함수
function callback() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			console.log("AJAX 정상 응답")

			//정상응답 처리 함수
			appendResult();

		} else {
			console.log("AJAX 요청/응답 에러")
			if(confirm("오류가 발생하였습니다")){
				location.replace("/mypage/update");
			}
		}
	}
}

//정상 응답 후 응답데이터 처리하는 함수
function appendResult() {
	if(updateNo == 1){
		nicknameUpdate.innerHTML = httpRequest.responseText;	
	}
	if(updateNo == 2){
		userPwUpdate.innerHTML = httpRequest.responseText;	
	}
	if(updateNo == 3){
		userEmUpdate.innerHTML = httpRequest.responseText;	
	}
	if(updateNo == 4){
		userPhUpdate.innerHTML = httpRequest.responseText;	
	}
}
</script>

<style type="text/css">
#footer {
	text-align: center;
	background: #353b48;
	height: 140px;
}

div#footer01 {
	padding-left: 20px;
	height: 60px;
	text-align: left;
	vertical-align: middle;
}

div#footer02 {
	padding-left: 40px;
	color: #DDD;
	text-align: left;
	height: 80px;
	font-size: 11px;
}

div#footer01>div.foot-bar {
	display: inline-block;
	padding: 19px 20px 0px 20px;
}

div#footer01>div>a {
	color: #DDD;
	text-decoration: none;
	font-size: 12px;
}

div#footer01>div>a:hover {
	color: tomato;
	text-decoration: none;
	vertical-align: middle;
}

div#footer01>span {
	color: #DDD;
}

table.mypageUpdate {
	width: 100%;
	border-collapse: collapse;
}

th.mypageUpdate {
	width: 30%;
	padding: 10px;
	font-weight: bold;
	background-color: #e0e0e0;
	font-size: 1em;
	color: black;
	border: 1px solid #8e8e8e;
}

td.mypageUpdate {
	padding: 10px;
	font-size: 1em;
	border: 1px solid #8e8e8e;
}

button {
	width: 60px;
	height: 25px;
	margin: 10px 0 0 0;
	background-color: #5e5e5e;
	color: white;
	border-radius: 3px;
	border: 0;
	outline: 0;
}
</style>

</head>
<body>

	<label><a href="/main"
		style="text-decoration-line: none; color: #345EE6; font-weight: bold; font-size: 2.5em; margin: 0 1% 0 0;">Self-Medi</a></label>
	<label style="color: #345EE6; font-weight: bold; font-size: 1em;">프로필
		수정</label>
	<hr
		style="width: 100%; margin: 0 0 0 0; border: solid 1px #345EE6; background-color: #345EE6; clear: both;">

	<div style="width: 70%; margin: 2% 0 0 15%;">

		<div style="margin: 0 0 15px 0">
			<label style="font-weight: bold; font-size: 0.8em;">${member.userNick }님의
				연락처 정보입니다</label><br> <label style="font-size: 0.8em;">회원정보는
				개인정보처리방침에 따라 안전하게 보호되며, 회원님의 명백한 동의 없이 공개 또는 제 3자에게 제공되지 않습니다. <a
				href="/main">개인정보 처리방침</a>
			</label>
		</div>

		<table class="mypageUpdate">
			<tr>
				<th class="mypageUpdate">닉네임 수정</th>
				<td class="mypageUpdate">
					<div id="nicknameUpdate">
						<br> <label class="oldNick" style="font-weight: bold;">${member.userNick }님</label><br>
						<input type="text" class="newNick" name="newNick"
							style="display: none;" value="${member.userNick }" /><br>
						<button class="btnNickUpdate" style="display: none;">확인</button>
						<button class="btnNickUpdateCancel"
							style="display: none; background-color: #e0e0e0; color: black">취소</button>
						<br>

						<button class="NickUpdate">변경</button>
						<br>
						<br>
					</div>
				</td>
			</tr>

			<tr>
				<th class="mypageUpdate">비밀번호 수정</th>
				<td class="mypageUpdate">
					<div id="userPwUpdate">
						<br> <label class="pwLabel" style="font-size: 0.8em;">Self-Medi
							로그인 시 사용하는 비밀번호는 변경할 수 있습니다. 주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요</label> <label
							class="pwLabelExplain" style="display: none; font-size: 0.8em;">현재
							비밀번호</label><br> <input type="text" class="oldPw" name="oldPw"
							style="display: none;" /><br>
						<br> <label class="pwLabelExplain"
							style="display: none; font-size: 0.8em;">새 비밀번호</label><br>
						<input type="text" class="newPw" name="newPw"
							style="display: none;" /><br> <label class="pwLabelExplain"
							style="display: none; font-size: 0.8em;">새 비밀번호 확인</label><br>
						<input type="text" class="newPwCheck" name="newPwCheck"
							style="display: none;" /><br>
						<button class="btnPwUpdate" style="display: none;">확인</button>
						<button class="btnPwUpdateCancel"
							style="display: none; background-color: #e0e0e0; color: black"">취소</button>

						<button class="pwUpdate">변경</button>
						<br>
						<br>
					</div>
				</td>
			</tr>

			<tr>
				<th class="mypageUpdate"">연락처 이메일 수정</th>
				<td class="mypageUpdate">
					<div id="userEmUpdate">
						<br> <label class="oldEm" style="font-weight: bold;">${member.userEm }</label><br>
						<label class="emLabel" style="font-size: 0.8em;">Self-Medi
							서비스의 변경/종료 등 Self-Medi의 대부분 안내에 사용할 이메일 주소입니다</label> <label
							class="emLabelExplain" style="font-size: 0.8em; display: none;">새
							이메일 주소</label><br> <input type="text" class="newEm" name="newEm"
							style="display: none;" value="${member.userEm }" /><br>
						<button class="btnEmUpdate" style="display: none;">확인</button>
						<button class="btnEmUpdateCancel"
							style="display: none; background-color: #e0e0e0; color: black"">취소</button>
						<br>

						<button class="EmUpdate">변경</button>
						<br>
						<br>
					</div>
				</td>
			</tr>

			<tr>
				<th class="mypageUpdate">휴대전화 번호</th>
				<td class="mypageUpdate">
					<div id="userPhUpdate">
						<br> <label class="oldPh" style="font-weight: bold;">${member.userPh }</label><br>
						<label class="phLabel" style="font-size: 0.8em;">아이디, 비밀번호
							찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 네이버로부터 알림을 받을때 사용합니다</label> <label
							class="phLabelExplain" style="font-size: 0.8em; display: none;">새
							휴대전화 번호</label><br> <input type="text" class="newPh" name="newPh"
							style="display: none;" value="${member.userPh }" /><br>
						<button class="btnPhUpdate" style="display: none;">확인</button>
						<button class="btnPhUpdateCancel"
							style="display: none; background-color: #e0e0e0; color: black"">취소</button>
						<br>

						<button class="PhUpdate">변경</button>
						<br>
						<br>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<hr>
	<c:import url="/WEB-INF/views/layout/footer.jsp" />