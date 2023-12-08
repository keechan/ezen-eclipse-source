<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/include/top.jsp"%>

<%
List<MoneyVO> li = (List<MoneyVO>) request.getAttribute("li");
%>

<style>
table {
	width: 500px;
}
</style>

<section>
	<br>
	<div align="center">
		<h2>강사매출현황</h2>
		<br>

		<table border=1>
			<tr align="center">
				<th>강사코드</th>
				<th>강의명</th>
				<th>강사명</th>
				<th>총매출</th>
			</tr>
			<%
			for (MoneyVO m : li) {

				DecimalFormat dc = new DecimalFormat("##,###");
				String t4 = dc.format(m.getT4());
			%>
			<tr align="center">

				<td><%=m.getT1()%></td>
				<td><%=m.getT2()%></td>
				<td><%=m.getT3()%></td>
				<td><%=t4%></td>

			</tr>
			<%
			}
			%>

		</table>

	</div>
	<br>
</section>

<%@ include file="/include/bottom.jsp"%>