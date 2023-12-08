<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.reBoard.*" %> 
<%
BoardVO   m =(BoardVO) request.getAttribute("m");
%>
<script>
	function writeK(){		
		location.href="<%=path%>/ReBoardController?sw=F"
	}
	function listK(){		
		location.href="<%=path%>/ReBoardController?sw=S"
	}
	
	function deleteK(k){
		location.href="<%=path%>/ReBoardController?sw=D&idx="+k
	}

	function reWriteK(){
		f1.sw.value="RW";
		f1.action="<%=path%>/ReBoardController"
	}
	
	function editK(){
		if(f1.title.value=="") {
		alert("수정하기");	
		return  false;
		}
		f1.sw.value="U";
		f1.action="<%=path%>/ReBoardController"
	}

</script>


<section>
 <br>
   <div align="center">
   <h2>게시판 글 상세보기/수정하기 </h2>
   <form  name="f1"  method=get > 
   <input type=hidden  name=sw >
   ref : <input type=text  name=ref value=<%=m.getRef() %>> <br>
   re_level : <input type=text  name=re_level value=<%=m.getRe_level() %>> <br>
   re_step : <input type=text  name=re_step value=<%=m.getRe_step() %>> <br>
   
   <table border=1>
   <tr><td>번호 </td>
      <td><input type=text  name=idx size=10 value=<%=m.getIdx() %> readonly></td></tr>
   <tr><td>제목 </td><td><input type=text  name=title size=30  value="<%=m.getTitle() %>">  </td></tr>
   <tr><td>글쓴이 </td><td><input type=text  name=sname  value="<%=m.getSname() %>"> </td></tr>
   <tr><td>글내용 </td>
        <td>
          <textarea cols=40  rows=5 name=content><%=m.getContent() %></textarea> 
   		</td>
   </tr>
   <tr><td colspan=2 align="center">
       <input  type=button value="글쓰기"     onClick="return writeK()" > &emsp; 
       <input  type=submit value="글수정하기"  onClick="return editK()" > &emsp; 
       <input  type=button value="목록보기"   onClick="return listK()" > &emsp; 
       <input  type=button value="글삭제"    onClick="return deleteK('<%=m.getIdx() %>')"> &emsp; 
       <input  type=submit value="답글쓰기"   onClick="return reWriteK()">
       </td></tr>
   </table>   
   </form>     
   
   
   
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 