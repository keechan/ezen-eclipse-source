<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="board.comment.*" %> 
<%@ include file ="/include/top.jsp" %> 

<%
  @SuppressWarnings("unchecked")
  List<BoardVO>  li = (List<BoardVO>) request.getAttribute("li");
%>
<section>
 <br>
   <div align="center">
   <h2>코맨트 게시판 글 목록보기 </h2>
   <table  border=1  width=500>
   <tr><th>번호 </th><th>이름 </th><th>제목 </th><th>조회수</th></tr>
   <% for(BoardVO m : li) { %>
   <tr align="center">
      <td><%=m.getIdx() %> </td>
      <td><%=m.getSname() %> </td>
      <td align="left"> &nbsp; 
	      <a href="<%=path %>/CommentController?sw=E&idx=<%=m.getIdx() %>">
	         <%=m.getTitle() %>
	      </a>
      </td>
      
      <td><%=m.getCnt() %></td>
   </tr>   
   <% } %>
   </table> 
     
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 