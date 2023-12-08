<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file = "/include/top.jsp" %>

<%
	List<TeacherVO> li = (List<TeacherVO>) request.getAttribute("li");
%>
<style>
table {
	width: 500px;
}
</style>

<section>
	<br>
	<div align="center">
		<h2>강사조회</h2>
		<br>

		<table border=1>
			<tr align="center">
				<th>순번</th>
				<th>강사코드</th>
				<th>강사명</th>
				<th>강의명</th>
				<th>수강료(원)</th>
				<th>강사자격취득일</th>
			</tr>
			<c:forEach items="${li}" var="m" >
				<c:set var="TRD" value="${m.TEACHER_REGIST_DATE}" />
				<c:set var="i" value="${i=i+1}" />
				<c:if test="${i%2==1}">
					<c:set var="bg" value="#B2CCFF"/>
				</c:if>
				<c:if test="${i%2==0}">
					<c:set var="bg" value="#D9E5FF"/>
				</c:if>
				<tr align="center" bgcolor="${bg}">
					<td>
							${i}
					</td>
					<td>
						<a href="ProjectController?name1=${m.TEACHER_CODE}&name2=${m.TEACHER_NAME}">
							${m.TEACHER_CODE}
						</a>						
					</td>
					<td>
					    <c:url var="url" value="ProjectController" >
							<c:param name="sw">jstl</c:param>
							<c:param name="code">${m.TEACHER_CODE}</c:param>
							<c:param name="name">${m.TEACHER_NAME}</c:param>
						</c:url>
					    
					    <a href="${url}">
							${m.TEACHER_NAME}
						</a>
						
					</td>
					<td>${m.CLASS_NAME}</td>
					<td>
						<fmt:formatNumber value="${m.CLASS_PRICE}" type="currency"/>
					</td>
					<td>
						${fn:substring(TRD, 0, 4)} 년
					    ${fn:substring(TRD, 4, 6)} 월 
					    ${fn:substring(TRD, 6, 8)} 일
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
</section>

<c:import url="/include/bottom.jsp"/>