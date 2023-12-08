<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp"%>

<%
	MemberVO m = (MemberVO) request.getAttribute("m");
	List<MemoVO> li = (List<MemoVO>) request.getAttribute("li");
%>

<style>
table {
	width: 600px;
}
</style>

<section>
	<br>
	<div align="center">
		<h2>회원정보수정</h2>
		<br>

		<table border=1>
			<tr align="center">
				<td>회원번호</td><td><%=m.getC_NO()%></td></tr> 
			<tr align="center">
				<td>회원명</td><td><%=m.getC_NAME()%></td></tr>
			<tr align="center">
				<td>전화번호</td><td><%=m.getPHONE()%></td></tr>
			<tr align="center">
				<td>주소</td><td><%=m.getADDRESS()%></td></tr>
			<tr align="center"> 
				<td>등급</td><td><%=m.getGRADE()%></td></tr>
		</table>

		<form action="${path}/ProjectController">
		<input type=hidden name="sw" value="MI">
		<input type=hidden name="cno" value=<%=m.getC_NO()%>>
		<table border=1>
			<tr align=center><td>메모</td><td>작성자</td><td></td></tr>
			<tr><td><input type=text name=memo size=50></td>
				<td><input type=text name=c_writer size=10></td>
				<td><input type=submit value="저장하기"></td>
			</tr>
		</table>		
		</form>
		
		<table border=1>
			<tr align=center><td>번호</td><td>메모</td><td>작성자</td></tr>
			<% for(MemoVO vo : li )  {%>
				<tr><td><a href="${path}/ProjectController?sw=MD&cno=<%=m.getC_NO()%>&idx=<%=vo.getIdx() %>" >
							<%=vo.getIdx() %>
						</a>
					</td>
				    <td><%=vo.getMemo() %></td>
				    <td><%=vo.getWriter() %></td>
				</tr>
			<% } %>
		</table>
		</div>
	<br>
</section>

<%@ include file="/include/bottom.jsp"%>