<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>

<body>
<table border=1>
	<tr>
		<td align=center colspan=10 bgcolor="yellow">구구단</td>
	</tr>	
	<c:forEach var="dan" begin="2" end="9" step="1">
		<c:if test="${dan%3 == 0 }" >
			<c:set var="bg" value="grey"/>
		</c:if>
		<c:if test="${dan%3 == 1 }" >
			<c:set var="bg" value="white"/>
		</c:if>
		<tr>
		<td align=center bgcolor=${bg }>${dan }단</td>
		<c:forEach var="j" begin="1" end="9" step="1">
			<td bgcolor=${bg}>${dan } * ${j } = ${dan*j}</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>	
</body>
</html>