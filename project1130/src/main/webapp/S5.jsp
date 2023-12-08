<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/include/top.jsp"/>


	<style>
		table {
		  	width: 50%;
		}
	</style>

	<section>
		<br>
		<div align="center">
			<h2>수강정보현황[${k1}]</h2>
			<br>
	
			<table id="t1" border=1>
				<tr align="center">
					<th>수강월</th>
					<th>회원번호</th>
					<th>강사장소</th>
					<th>수강료</th>
					<th>강사코드</th>
					<th>강사명</th>
				</tr>
				<c:forEach items="${li}" var="m" >
					<fmt:formatNumber var="TUITION" value="${m.getTUITION()}" />
					<c:set var="j" value="${m.getC_NO()}" />
					<c:set var="TRD" value="${m.getREGIST_MONTH()}" />
					<c:set var="MONTH" value="${m.getREGIST_MONTH().substring(4, 6)}"/>
					<c:if test="${Integer.parseInt(MONTH) < 10}">
						<c:set var="trArr" value="${m.getREGIST_MONTH().substring(5, 6)}"/>
					</c:if>
					<c:if test="${Integer.parseInt(MONTH) >=10} }">
						<c:set var="trArr" value="${m.getREGIST_MONTH().substring(4, 6)}"/>
					</c:if>

					<c:set var="trbg" value="${arr[trArr]}" />
					<c:choose>
						<c:when test="${j=='10001' }">
							<c:set var="j" value="홍길동" />
						</c:when>
						<c:when test="${j=='10002' }">
							<c:set var="j" value="장발장" />
						</c:when>
						<c:when test="${j=='10003' }">
							<c:set var="j" value="임꺽정" />
						</c:when>
						<c:when test="${j=='20001' }">
							<c:set var="j" value="성춘향" />
						</c:when>
						<c:when test="${j=='20002' }">
							<c:set var="j" value="이몽룡" />
						</c:when>
						<c:otherwise>
							<c:set var="j" value="기타" />
						</c:otherwise>
					</c:choose>
					<tr align="center" bgcolor="${trbg}">
						<td>
							${fn:substring(TRD, 0, 4)}년
							${fn:substring(TRD, 4, 6)}월
						</td>
						<td>
							<c:url var="cnoUrl" value="ProjectController">
								<c:param name="sw">ME</c:param>
								<c:param name="cno">${m.getC_NO()}</c:param>								
							</c:url>
							<a href="${cnoUrl}">${m.getC_NO()}${j }</a>
						</td>
						<td>${m.getCLASS_AREA()}</td>
						<td>${TUITION}</td>
						<td>${m.getTEACHER_CODE()}</td>
						<td>${m.getTEACHER_NAME()}</td>
					</tr>					
				</c:forEach>
			</table>
		</div>
		<br>
	</section>

<c:import url="/include/bottom.jsp" />