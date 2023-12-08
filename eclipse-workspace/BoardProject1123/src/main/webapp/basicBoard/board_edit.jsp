<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.basicBoard.*" %> 
<%
BoardVO   m =(BoardVO) request.getAttribute("m");
%>
<section>
 <br>
   <div align="center">
   <h2>게시판 글 상세보기/수정하기 </h2>
   <form  action="<%=path %>/BasicBoardController?sw=U" 
     enctype="multipart/form-data" method=post >
   <input type=hidden  name=sw value=U>
   <table border=1>
   <tr><td>번호 </td>
      <td><input type=text  name=idx size=10 value=<%=m.getIdx() %> readonly></td>
      <td rowspan=3 align="center">  <img src="<%=path %>/basicBoard/files/<%=m.getImg() %>" width=100 height=100 > </td></tr>
   <tr><td>제목 </td><td><input type=text  name=title size=30  value="<%=m.getTitle() %>">  </td></tr>
   <tr><td>글쓴이 </td><td><input type=text  name=sname  value="<%=m.getSname() %>"> </td></tr>
   <tr><td>글내용 </td>
        <td colspan=2>
          <textarea cols=40  rows=5 name=content><%=m.getContent() %></textarea> 
   		</td>
   </tr>
   <tr><td>글쓴이 </td><td  colspan=2><input type=file  name=img > </td></tr>
   <tr><td colspan=3 align="center">
       <input  type=submit value="글수정하기"> &emsp; 
       <input  type=submit value="목록보기"  onClick="action='<%=path %>/BasicBoardController?sw=S'" >
       </td></tr>
   </table>   
   </form>     
   
   
   
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 