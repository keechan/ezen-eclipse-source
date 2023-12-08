<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="DBPKG.project.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="<%=request.getContextPath() %>" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header {
	background: #0000ff;
	height: 90px;
	line-height: 90px;
	color: #ffffff;
	font-size: 30px;
	text-align: center;
}

nav {
	background: #0088ff;
	height: 35px;
	line-height: 35px;
	color: #ffffff;
	font-size: 16px;
	text-align: left;
}

section {
	background: #eaeaea;
	min-height: 500px;
	line-height: 25px;
	color: #000000;
	font-size: 16px;
	text-align: left;
}

footer {
	background: #0033dd;
	height: 50px;
	line-height: 50px;
	color: #000000;
	font-size: 16px;
	text-align: center;
}

nav a {
	color: #ffffff;
	text-decoration:none;	
}

section a {
	color: Tomato;

}

</style>

</head>
<body>
	<header> 골프연습장 회원관리 ver1.0 </header>
	<nav>
		&emsp; <a href=ProjectController?sw=S1>강사조회</a> &emsp;&emsp;
		       <a href=ProjectController?sw=S2>수강신청</a> &emsp;&emsp;
		       <a href=ProjectController?sw=S3>회원정보조회</a> &emsp;&emsp;
		       <a href=ProjectController?sw=S4>강사매출현황</a> &emsp;&emsp;
		       <a href=ProjectController?sw=S5>수강정보현황</a> &emsp;&emsp;
		       
		       <a href=GuestBookController?sw=write>방명록작성</a> &emsp;&emsp;
		       <a href=GuestBookController?sw=list>방명록목록</a> &emsp;&emsp;
		       
		       <a href=ProjectController?sw=INDEX>홈으로 </a>
	</nav>