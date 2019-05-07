<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardContentsAjax.jsp</title>
</head>
<body>
<h3>Board & Reply Contents</h3>
<a href="boardDelete?bnum=${board.b_num}">삭제</a>
<!-- <a href="boardDelete">삭제</a> --><!-- 쿼리로 데이터 안넘겨도 넘어간다 -->
<table>
	<tr height="30">
		<td bgcolor="pink" align="center">NUM</td>
		<td colspan="5">${board.b_num}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">WRITER</td>
		<td>${board.b_mid}</td>
		<td bgcolor="pink" align="center">DATE</td>
		<td>${board.b_date}</td>
		<td bgcolor="pink" align="center">VIEWS</td>
		<td>${board.b_views}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">TITLE</td>
		<td colspan="5">${board.b_title}</td>
	</tr>
	<tr height="200">
		<td bgcolor="pink" align="center">CONTENTS</td>
		<td colspan="5">${board.b_contents}</td>
	</tr>
	<!-- 첨부파일 사진을 출력하는것도 책보고 해보라 -->
	<tr>
		<th>첨부파일</th>
		<td>
			<c:set var="file" value="${bfList}"/>
			<c:if test="${empty file}">
				첨부된파일이 없어요
			</c:if>
			<c:if test="${!empty file}">
				<c:forEach var="file" items="${bfList}">
					<a href="download?oriFileName=${file.bf_oriname}
						&sysFileName=${file.bf_sysname}">${file.bf_oriname}</a>
				</c:forEach>
			</c:if>
		</td>
	</tr>
</table>
<form name="rFrm" id="rFrm">
	<table>
		<tr>
			<td><textarea rows="3" cols="50" name="r_contents" id="r_contents"></textarea></td>
			<!-- textarea에 <script>alert("hello")</script>같은거 못쓰게 구글링해서 네이버로 api설정해야함 -->
		</tr>
		<tr>
			<!-- textarea는 /textarea로 닫아줘야한다 그냥 /로는 에러뜸 -->
			<td>
				<input type="button" value="댓글전송" onclick="replyInsert(${board.b_num})"
					style="width:70px;height:50px"/>
			</td>
		</tr>
	</table>
</form>

<table>
	<tr bgcolor="skyblue" align="center" height="30">
		<td width="100">WRITER</td>
		<td width="100">CONTENTS</td>
		<td width="100">DATE</td>
	</tr>
</table>
<table id="rTable"> <!-- Ajax결과 여기에 쓰기 -->
	<c:forEach items="${rList}" var="reply">
		<tr height="25" align="center">
			<td width="100">${reply.r_mid}</td>
			<td width="200">${reply.r_contents}</td>
			<td width="200">${reply.r_date}</td>
		</tr>
	</c:forEach>
</table>
<script>
function replyInsert(bnum){
	//jquery.serializeObject는 폼안의 모든 데이터를 js객체화 한다.
	//반드시 name속성이 있어야 한다
	//폼 안에 파일태그가 없어야 한다.
	var obj=$("#rFrm").serializeObject(); //{속성:값,속성:값}
	obj.r_bnum=bnum;//r_bnum은 name에 없으니 따로 추가해준다.
	console.log(obj);
	//js객체-->json형태로 변환(문자열)
	var jsonStr=JSON.stringify(obj);
	console.log(jsonStr);
	$.ajax({
		type:"post",//json으로 넘길때는 반드시 post로 해야한다
		url:"ajax/replyInsert",//레스트풀 형식
		//data:{r_bnum:bnum,r_contents:$("#r_contents").val()},
		//data:$("#rFrm").serialize(),//폼 전체 데이터 전송
		//data:obj,
		data:jsonStr,
		//쿼리스트링이 아닌 json방식으로 전송시 contentType을 명시해야함
		contentType:"application/json",//; charset=UTF-8",
		dataType:"json",
		success:function(data,status,xhr){
			console.log(data);
			//console.log(status);
			//console.log(xhr);
			/* var rList='';
			for(var i=0;i<data.length;i++){
				rList+='<tr height="25" align="center">'
					  +'<td width="100">'+data[i].r_mid+'</td>'
					  +'<td width="200">'+data[i].r_contents+'</td>'
					  +'<td width="200">'+data[i].r_date+'</td>'
					  +'</tr>'
			}
			$('#rTable').html(rList); */
			var str='';
			var rList=data['rList'];
			for(var i in rList){
				str+='<tr height="25" align="center">'
					  +'<td width="100">'+rList[i]['r_mid']+'</td>'
					  +'<td width="200">'+rList[i]['r_contents']+'</td>'
					  +'<td width="200">'+rList[i]['r_date']+'</td>'
					  +'</tr>'
			}
			$('#rTable').html(str);
		},
		error:function(error,status,xhr){
			//console.log("error="+error);
			//console.log("status="+status);
			//console.log("xhr="+xhr);
		}
	})
}
</script>
</body>
</html>