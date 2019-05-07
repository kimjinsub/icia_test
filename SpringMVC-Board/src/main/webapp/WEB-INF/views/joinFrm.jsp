<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.subject{; text-align: center;}
</style>
<body>
<h1>회원가입 폼</h1>
<form name="joinFrm" action="memberInsert" method="post" onsubmit="return check()">
	<table border="1">
		<tr>
			<td colspan="2" class="subject">회원가입</td>
		</tr>
		<tr>
			<td width="100">ID</td>
			<td><input type="text" name="mid"/></td>
		</tr>
		<tr>
			<td width="100">PWD</td>
			<td><input type="password" name="mpwd"/></td>
		</tr>
		<tr>
			<td width="100">NAME</td>
			<td><input type="text" name="mname"/></td>
		</tr>
		<tr>
			<td width="100">BIRTH</td>
			<td><input type="text" name="mbirth"/></td>
		</tr>
		<tr>
			<td width="100">ADDR</td>
			<td><input type="text" name="maddr"/></td>
		</tr>
		<tr>
			<td width="100">PHONE</td>
			<td><input type="text" name="mphone"/></td>
		</tr>
		<tr>
			<td colspan="2" class="subject"><input type="submit" value="회원가입"/></td>
		</tr>
	</table>
</form> 
</body>
<script>
function check(){
	var frm=document.joinFrm;
	var length=frm.length-1;
	for(var i=0;i<length;i++){
		if(frm[i].value=="" || frm[i].value==null){
			alert(frm[i].name+"을 입력하세요!!");
			frm[i].focus();
			return false;//실패,,유효성검사,, 반드시 해줘야함
		}
	}
	return true//성공,,서버로 전송
}
</script>
</html>