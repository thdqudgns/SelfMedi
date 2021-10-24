<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 발생</title>

<style type="text/css">
body{text-align: center;vertical-align: middle;}
#errorhead{color:red; font-size: 70px;font-weight: bold;}
#errorlayout {border: 2px dashed #ccc;vertical-align: middle;padding:30px;width:600px;margin:0 auto;}
#errorlayout > div > p > a {text-decoration: none;color:#345EE6;}
#errorlayout > div > p {line-height:30px;}
</style>

</head>
<body>
	<div id="errorlayout">
		<div id="errorhead">405 ERROR!</div>

		<div>
			요청 처리 과정에서 에러가 발생하였습니다. URL을 받을 수 없습니다.<br>

			<%-- <p>
				에러 타입:
				<%=exception.getClass().getName()%><br> 에러 메세지: <b><%=exception.getMessage()%></b><br>
			</p> --%>
			<p style="text-align: left;">
			<b>다음 사항들을 확인해보세요.</b>
			<br>
			&bull; 잘못된 URL 경로로 들어서지 않았습니까?<br>
			&nbsp;&nbsp; 메인으로 돌아가기 &rArr; <a href="<%=request.getContextPath()%>/">메인</a><br>
			
			&bull; 로그인이 필요한 서비스는 아닙니까?<br>
			&nbsp;&nbsp; 회원가입하기  &rArr; <a href="<%=request.getContextPath()%>/member/join">회원가입</a><br>
			&nbsp;&nbsp; 로그인하기  &rArr; <a href="<%=request.getContextPath()%>/member/login">로그인</a><br>
			</p>
			
		</div>
		<br><br>
		<a href="<%=request.getContextPath()%>/"><img src="https://i.imgur.com/QLLAO5N.png" style="width: 200px"></a>
	</div>

</body>
</html>