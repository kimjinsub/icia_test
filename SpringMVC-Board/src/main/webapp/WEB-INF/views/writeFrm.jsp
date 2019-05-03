<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
input[type='text']{width:98%;}
textarea{width:98%}
</style>
</head>
<body>
	<form action="boardWrite" id="frm" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="b_title" id="b_title"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="b_contents" id="b_contents" rows="20"></textarea></td>
			</tr>
			<tr>
				<td>파일첨부</td>
				<td>
					<input type="file" name="b_files" id="b_files" 
							onchange="fileChk(this)" multiple/>
					<input type="hidden" id="fileCheck" value="0" name="fileCheck"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글작성"/>
					<input type="reset" value="취소"/>
					<input type="button" onclick="location.href='boardList'" value="리스트보기"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
function fileChk(elem){
	console.log(elem);
	console.log(elem.value);
	console.dir(elem);
	if(elem.value==""){
		console.log("empty");
		$("#fileCheck").val(0);//0:파일첨부 안했음 val() : val출력 val(a) val에 a입력
	}else{
		console.log("not empty");
		$("#fileCheck").val(1);//1:파일첨부 했음
	}
}
</script>
</html>