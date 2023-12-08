<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:remove var="k1" scope="session"/>

<%
	List<MemberVO> li = (List<MemberVO>) request.getAttribute("li");
%>

<style>
table {
	width: 700px;
}
</style>

<section>
	<br>
	<div align="center">
		<h2>회원정보조회</h2>
		<br>

		<table border=1>
			<tr align="center">
				<th>수강월</th>
				<th>회원번호</th>
				<th>회원명</th>
				<th>강의명</th>
				<th>강의장소</th>
				<th>수강료</th>
				<th>등급</th>
			</tr>
			<%
			for (MemberVO m : li) {
				//  REGIST_MONTH, M.C_NO as C_NO, C_NAME, CLASS_NAME, CLASS_AREA, TUITION ,GRADE 

				DecimalFormat dc = new DecimalFormat("##,###");
				String TUITION = dc.format(m.getTUITION());

				String date = String.valueOf(m.getREGIST_MONTH());
				String yy = date.substring(0, 4);
				String mm = date.substring(4, 6);
			%>
			<tr align="center">
				<td><%=yy%>년 <%=mm%>월</td>
				<td><%=m.getC_NO()%></td>
				<td><%=m.getC_NAME()%></td>
				<td><%=m.getCLASS_NAME()%></td>
				<td><%=m.getCLASS_AREA()%></td>
				<td><%=TUITION%></td>
				<td><%=m.getGRADE()%></td>

			</tr>
			<%
			}
			%>

		</table>

	</div>
	<br>
</section>

<%@ include file="/include/bottom.jsp"%>