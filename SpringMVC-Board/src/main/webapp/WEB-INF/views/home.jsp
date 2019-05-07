<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<script>
		window.onload=function(){
			var chk=${check};
			if(chk==1){
				alert("회원가입 성공");
			}
			if(chk==2){
				alert("로그인실패");
			}
		}
	</script>
</head>
<body>
<h1>home.jsp(login page)</h1>
<form action="access" name="logFrm" method="post">
	<table border="1">
		<tr>
			<td colspan="2" align="center" bgcolor="skyblue">로그인</td>
		</tr>
		<tr>
			<td><input type="text" name="mid" placeholder="아이디"></td>
			<td rowspan="2"><button>로그인</button></td>
		</tr>
		<tr>
			<td><input type="password" name="mpwd" placeholder="비밀번호"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" bgcolor="skyblue">com.board.jinsub</td>
		</tr>
		<tr>
			<td colspan="2" align="center" bgcolor="skyblue"><a href="joinFrm">회원가입</a></td>
		</tr>
	</table>
</form>
</body>
</html>