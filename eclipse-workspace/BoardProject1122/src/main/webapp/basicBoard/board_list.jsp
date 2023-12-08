<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="board.basicBoard.*" %> 
<%@ include file ="/include/top.jsp" %> 

<%
  @SuppressWarnings("unchecked")
  List<BoardVO>  li = (List<BoardVO>) request.getAttribute("li");
%>
<script>
	function deleteK(idx, str) {
		alert(idx);
		 // 삭제(idx)

		 location.href="<%=path%>/BasicBoardController?sw=D&idx="+idx+"&img="+str;
	}
</script>
<section>
 <br>
   <div align="center">
   <h2>게시판 글 목록보기 </h2>
   <table  border=1  width=500>
   <tr><th>번호 </th><th>사진</th><th>이름 </th><th>제목 </th><th>내용</th><th>조회수</th><th>삭제</th></tr>
   <% for(BoardVO m : li) { %>
   <tr align="center">
      <td><%=m.getIdx() %></td>
      <td>
      	<img src="<%=path%>/basicBoard/files/<%=m.getImg() %>" width=50>
      </td>
      <td><%=m.getSname() %></td>
      <td align="left"> &nbsp; 
	      <a href="<%=path %>/BasicBoardController?sw=E&idx=<%=m.getIdx() %>">
	         <%=m.getTitle() %>
	      </a>
      </td>
   	  <td><%=m.getContent() %></td>   
      <td><%=m.getCnt() %></td>
      <%
      	String str =  java.net.URLEncoder.encode(m.getImg(), "UTF-8");
      %>
      <td><input type=button value=삭제 onclick="deleteK('<%=m.getIdx()%>', '<%=str%>')"></td>
      
   </tr>   
   <% } %>
   </table> 
     
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 