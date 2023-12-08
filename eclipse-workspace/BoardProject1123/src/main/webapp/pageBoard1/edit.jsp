<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.pageBoard1.*" %> 
<%
BoardVO   m =(BoardVO) request.getAttribute("m");
%>
<script>
  function  listK(start, ch1 , ch2){
	  location.href="<%=path %>/PageBoardController?sw=S&start="+start+"&ch1="+ch1+"&ch2="+ch2
  }

</script>
<section>
 <br>
   <div align="center">
   <h2>게시판 글 상세보기/수정하기 </h2>
   <form  action="<%=path %>/PageBoardController" method=get >
   <input type=hidden  name=sw value=U>
    <input type=hidden  name=start value="<%=m.getStart() %>">
    <input type=hidden  name=ch1 value="<%=m.getCh1() %>">
    <input type=hidden  name=ch2 value="<%=m.getCh2() %>"> 
   <table border=1>
   <tr><td>번호 </td>
      <td><input type=text  name=idx size=10 value=<%=m.getIdx() %> readonly></td>
    </tr>
   <tr><td>제목 </td><td><input type=text  name=title size=30  value="<%=m.getTitle() %>">  </td></tr>
   <tr><td>글쓴이 </td><td><input type=text  name=sname  value="<%=m.getSname() %>"> </td></tr>
   <tr><td>글내용 </td>
        <td >
          <textarea cols=40  rows=5 name=content><%=m.getContent() %></textarea> 
   		</td>
   </tr>   
   	  <%
   	   String  ch2 ="";
	   if (m.getCh2() != null ){
		  ch2 = java.net.URLEncoder.encode(m.getCh2(), "UTF-8");
	   }
	  %>
	  
   <tr><td colspan=2 align="center">
       <input  type=submit value="글수정하기"> &emsp; 
       <input  type=button value="목록보기" 
               onClick="listK('<%=m.getStart() %>','<%=m.getCh1() %>','<%=ch2 %>')" >
       </td></tr>
   </table>   
   </form>     
   
   
   
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 