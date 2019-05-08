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
					<input type="button" onclick="formData()" value="FormData"/>
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
function formData(){
	var $obj=$("#b_files");
	/* console.log(1,$obj[0]);
	console.log(2,$obj[0].files);
	console.log(3,$obj[0].files[0]);
	console.log(4,$obj[0].files[1]); */
	//multipart/form-data를 전송시 무조건 사용(파일업로드)
	//FormData객체는 form의 일부데이터만 서보에 전송하고 싶을 때 좋음
	//ajax에서 사용함
	var formData=new FormData();
	formData.append("b_title",$("#b_title").val());
	formData.append("b_contents",$("#b_contents").val());
	formData.append("fileCheck",$("#fileCheck").val());//0,1
	
	var files=$obj[0].files;//배열로 파일정보를 반환
	/*for(var i in files){
		console.log(i,files[i]);//length랑 item() 뭐 이런게 같이 넘어온다 아래와 같이 반복문 돌리자
	}*/
	for(var i=0;i<files.length;i++){
		formData.append("files",files[i]);//속성명이 같다면 append가 배열로 만들어준다
	}
	//formData 객체에 저장된 값 확인
	console.log(formData.get("btitle"));
	console.log(formData.get("files"));
	
	$.ajax({
		type:"post",
		url:"boardWrite",
		data:formData,
		processData:false,
		//application/x-www-form-urlencoded(쿼리스트리형식) 처리금지,,데이터 안까게하기
		
		contentType:false,
		//contentType:"application/json" json 쓸때 이렇게 했던거 처럼 multipart의 경우 false로 해야됨
		dataType:"html",//html은 생략가능
		success:function(data){
			alert("성공");
			console.log(data);
			location.href="./boardList";
		},
		error:function(error){
			alert("에러");
			console.log(error)
		} 
	})
}
</script>
</html>