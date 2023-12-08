<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h2>글 저장하기</h2>
	<form action="insertBoard.do">
		<table border=1>
			<tr>
				<td>번호</td>
				<td><input type=text name="seq"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type=text name="title"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type=text name="writer"></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td>
					<textarea cols=40 rows=4 name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan=2 align="center">
				<input type=submit value="저장하기"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>