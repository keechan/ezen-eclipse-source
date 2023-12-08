<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 

<section>
 <br>
   <div align="center">
   <h2>게시판 글 등록하기 </h2>
   
   <form  action="<%=path %>/ReBoardController"  method=get >
   <input type=hidden  name=sw value=I>
   <table border=1>
   <tr><td>제목 </td><td><input type=text  name=title size=30>  </td></tr>
   <tr><td>글쓴이 </td><td><input type=text  name=sname> </td></tr>
   <tr><td>글내용 </td><td><textarea cols=40  rows=5 name=content></textarea> </td></tr>
   <tr><td colspan=2 align="center">
       <input  type=submit value="글등록"> &emsp; 
       <input  type=reset value="다시작성">
       </td></tr>
   </table>   
   </form>      
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 