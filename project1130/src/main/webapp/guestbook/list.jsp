<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/include/top.jsp" /> 

<style>
 table{
   width:550px;
 } 
 
</style>
<script>
function delK(k, ch1, ch2){
	alert(k + ":" + ch1 + ":" + ch2) 
	location.href="${path}/GuestBookController?sw=del&idx="+k +"&ch1="+ch1+"&ch2="+ch2
}

</script>

<section>
		<br>
		<div align="center">
		1.페이지 사이즈 : ${pageSize} &emsp;&emsp;
		2.페이지 List사이즈(아래숫자갯수) : ${pageListSize}&emsp;&emsp;
		3.전체레코두 수 : ${totalCount}&emsp;&emsp;
		4.총페이지수 : ${totalPage}  <br>
		5.현재레코드 : ${start}&emsp;&emsp;
		6.현재페이지 : ${nowPage}&emsp;&emsp;
		7.가로하단 시작 :${listStartPage}&emsp;&emsp;
		8.가로 하단 마지막 : ${listEndPage}
		
		<h2>방명록 목록보기 </h2>
		
		<form action="${path}/GuestBookController">
		<input type=hidden name=sw  value=list>
		<select name=ch1>
		  <option value=name> 이 름  </option>
		  <option value=title> 제 목  </option>
		</select>
		<input  type=text  name=ch2 >
		<input  type=submit value="검색하기">
		
		</form>
		<br>
		
		<table border=1  >
		<tr align="center">
		 <th>순번</th><th>제목</th><th>작성자</th><th>삭제</th>
		 </tr>
		<c:forEach var="m"  items="${li}">
		<tr align="center">
		
		   <td> ${m.getIdx()}</td> 
		   <td align=left>&emsp; ${m.getTitle()}</td> 
		   <td> ${m.getName()}</td> 
		   <td> <input  type=button  value="삭제" onClick="delK('${m.getIdx()}','${ch1}','${ch2}')" >  </td>
		
		</tr>
		</c:forEach>
		
		</table>

		<c:if test="${start == 1}">
			처음페이지 &emsp;
			이전 &emsp;
		</c:if>		
		<c:if test="${start != 1}">
			<a href=GuestBookController?sw=list&start=1&ch1=${ch1}&ch2=${ch2}>처음페이지</a> &emsp;
		</c:if>
		<c:if test="${listStartPage > pageListSize}">
		   <c:set var="start"  value="${(listStartPage - pageListSize - 1) * pageSize  + 1}" />
		   <a href=GuestBookController?sw=list&start=${start-pageSize}&ch1=${ch1}&ch2=${ch2}>
		   	이전
		   </a> &emsp;
		</c:if>
		
		<c:forEach var="i" begin="${listStartPage}" end="${listEndPage}" >
		  <c:set var="start"  value="${(i-1) * pageSize + 1}" />
		  <c:if test="${i <= totalPage}">
		    <a href=GuestBookController?sw=list&start=${start}&ch1=${ch1}&ch2=${ch2}>[${i}]</a>&nbsp;
		  </c:if>
		</c:forEach>
		
		<c:if test="${listEndPage < totalPage}">
		<c:set  var="start" value="${listEndPage * pageSize + 1}" />
		   <a href=GuestBookController?sw=list&start=${start+pageSize}&ch1=${ch1}&ch2=${ch2}>다음</a>&emsp;
		</c:if>
		<c:if test="${listEndPage >= totalPage}">
		   다음&emsp;
		</c:if>
		
		<c:set var="endPage"  value="${( totalPage - 1) * pageSize + 1}" />
		<a href=GuestBookController?sw=list&start=${endPage}&ch1=${ch1}&ch2=${ch2}>마지막페이지</a>
		</div>
		<br>
	</section>

<%@ include  file="/include/bottom.jsp" %>
