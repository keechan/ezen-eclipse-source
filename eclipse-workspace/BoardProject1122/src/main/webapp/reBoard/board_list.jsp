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
   <h2>답변형 글 목록보기 </h2>
   <table  border=1  width=500>
   <tr>
   	<th>번호</th><th>이름</th><th>제목</th><th>내용</th><th>조회수</th><th>그룹</th><th>답변순서</th><th>레벨</th>
   </tr>
   <% for(BoardVO m : li) { %>
   <tr align="center">
      <td><a href="<%=path %>/ReBoardController?sw=D&idx=<%=m.getIdx()%>"><%=m.getIdx() %></a></td>
      <td><%=m.getSname() %> </td>
      <td align="left"> &nbsp;
      	<img src="<%=path %>/img/space2.png" height=20 > 
	      <a href="<%=path %>/ReBoardController?sw=E&idx=<%=m.getIdx() %>">
	         <%=m.getTitle() %>
	      </a>
      </td>
      <td><%=m.getContent() %></td>
      <td><%=m.getCnt() %></td>
      <td><%=m.getRef() %></td>
      <td><%=m.getRe_step() %></td>
      <td><%=m.getRe_level() %></td>
   </tr>
   <% } %>
   </table> 

   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 