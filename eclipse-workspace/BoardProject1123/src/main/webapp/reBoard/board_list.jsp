<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="board.reBoard.*" %> 
<%@ include file ="/include/top.jsp" %> 

<%
  @SuppressWarnings("unchecked")
  List<BoardVO>  li = (List<BoardVO>) request.getAttribute("li");
%>

<section>
 <br>
   <div align="center">
   <h2>게시판 글 목록보기 </h2>
   <table  border=1  width=700>
   <tr>
   <th>번호 </th><th>이름 </th><th>제목 </th><th>조회수</th>
   <th>ref </th><th>level </th><th>step</th><th>삭제</th>
   </tr>
   <%  
      int w =0 ;
      for(BoardVO m : li) {
    	  w =m.getRe_level() * 20 ;   
	   %>
   <tr align="center">
      <td><%=m.getIdx() %> </td>
      <td><%=m.getSname() %> </td>
      <td align="left">
 
       <img src="<%=path %>/img/space2.png" width="<%=w %>"  height=20>
       <% if (m.getRe_level() > 1) { %>
        	<img src="<%=path %>/img/re2.png" width=20 height=20>
       <%} %>
     
    
       <a href="<%=path %>/ReBoardController?sw=E&idx=<%=m.getIdx() %>">
	         <%=m.getTitle() %>
	   </a>
	 
	         
      </td>
      
      <td><%=m.getCnt() %></td>
      
      <td><%=m.getRef() %></td>
      <td><%=m.getRe_level() %></td>
      <td><%=m.getRe_step() %></td>
      <td>
       <a href="<%=path %>/ReBoardController?sw=D&idx=<%=m.getIdx() %>">삭제</a> 
      </td>
   </tr>   
   <% } %>
   </table> 
     
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 