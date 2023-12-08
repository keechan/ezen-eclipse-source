<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp"%>
<%@ page import="DBPKG.guestbook.*"%>

<style>
table {
	width: 500px;
}
</style>

<Script>
</Script>

<section>
	<br>
	<div align="center">
		<h2>방명록작성</h2>
		<br>
		<form name="f1" action="GuestBookController">
			<input type=hidden name="sw" value="insert">
			<table border=1>
				<tr>
					<td>순번</td>
					<td><input type=text name="idx"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type=text name="name"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type=text name="title"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type=text name="content"></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type=submit value="등록">
						&emsp; <input type=reset value="다시쓰기"></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>

<%@ include file="/include/bottom.jsp"%>