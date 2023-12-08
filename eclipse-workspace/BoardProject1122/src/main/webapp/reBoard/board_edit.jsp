<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.reBoard.*" %> 
<%
	BoardVO   m =(BoardVO) request.getAttribute("m");
%>

<script>
	function writeK() {
		alert("write");
		location.href="<%=path%>/ReBoardController?sw=F";
	}
	
	function editK() {
		alert("edit");
		if(f1.title.value="") {
			return false;
		}
		location.href="<%=path%>/ReBoardController?sw=U";
	}

	function listK() {
		alert("list");
		f1.action="<%=path%>/ReBoardController?sw=S";
	}
	
	function deleteK() {
		alert("delete");
		f1.action="<%=path%>/ReBoardController?sw=D&idx="+k;
	}
	
	function rewriteK() {
		alert("rewrite");
		f1.sw.value=RW;
		f1.action="<%=path%>/ReBoardController";
	}
</script>

<section>
 <br>
   <div align="center">
   <h2>답변형 글 상세보기/수정하기 </h2>
   <form name="f1" method=get >
	   <input type=hidden  name=sw>
	   <table>
		   <tr><td>ref :      <input type=text  name=ref      value=<%=m.getRef() %>></td></tr>
		   <tr><td>re_step :  <input type=text  name=re_step  value=<%=m.getRe_step() %>></td></tr>
		   <tr><td>re_level : <input type=text  name=re_level value=<%=m.getRe_level() %>></td></tr>
	   </table>
	   <table border=1>
	   <tr><td align=center>번호 </td>
	       <td>
	         <input type=text  name=idx size=10 value=<%=m.getIdx() %> readonly>
	       </td>
	   </tr>
	   <tr><td align=center>제목 </td><td><input type=text  name=title size=30  value="<%=m.getTitle() %>">  </td></tr>
	   <tr><td align=center>글쓴이 </td><td><input type=text  name=sname  value="<%=m.getSname() %>"> </td></tr>
	   <tr><td align=center>글내용 </td>
	        <td>
	          <textarea cols=40  rows=5 name=content><%=m.getContent() %></textarea> 
	   		</td>
	   </tr>
	   <tr><td colspan=2 align="center">
		       <input  type=button value="글쓰기"    onclick="writeK()"> &emsp; 
		       <input  type=submit value="글수정하기" onclick="return editK()">  &emsp;
		       <input  type=button value="목록보기"  onclick="return listK()">   &emsp;
		       <input  type=button value="글삭제"    onclick="return deleteK()"> &emsp;
		       <input  type=submit value="답글쓰기"  onclick="return rewriteK()">
	       </td>
	   </tr>
	   </table>   
   </form>     
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %>