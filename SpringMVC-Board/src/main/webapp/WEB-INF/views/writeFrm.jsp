<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				<td><input type="file" name="b_files" id="b_files"/></td>
			</tr>
		</table>
	</form>
</body>
</html>