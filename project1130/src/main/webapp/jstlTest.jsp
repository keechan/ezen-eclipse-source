<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="k" begin="1" end="5" step="1">
		${k} EL표기법, 안녕하세요.<br>
		<c:if test="${ k%2==1 }">
			${k}
		</c:if>
		<c:set var="p" value="${j = j + k }"/>
	</c:forEach>
	
	P결과값 출력 : ${p} 
	<c:out value="${p}" />
</body>
</html>