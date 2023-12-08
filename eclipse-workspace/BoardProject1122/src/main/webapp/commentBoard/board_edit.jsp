<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.comment.*" %> 
<%@ page import ="java.util.*" %>
<%
BoardVO m =(BoardVO) request.getAttribute("m");
List<CommentVO>   li = (List<CommentVO>) request.getAttribute("li");
%>

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
 jQuery.ajaxSetup({cache:false});
 $(document).ready( function(){
	  $('#saveK').click( function(){
		  //alert("확인2:" + $('#sw').val() + "," + $('#comment_idx').val()   +"," +  $('#commentContent').val()   );
		  var  dataStr={
				 sw : $('#sw').val(),
				 comment_idx : $('#comment_idx').val(),
				 commentContent : $('#commentContent').val()	
		  };

		  $.ajax({
			  type: "GET",
			  url : "<%=path%>/CommentController",
			  data : dataStr,
			  success: function (data){
			  //alert("저장성공!!" + $('#sw').val() );
			  location.replace("<%=path%>/CommentController?sw=E&idx=" + $('#comment_idx').val()) ;
			  }	  
		  })		 
	  })
 })

 function  deleteK(cidx , comment_Idx) {
	 // 삭제(idx) ,  돌아올 곳(commentIdx)
	 //alert(idx +" : "+ commentIdx );
	 var dataStr={
		sw : "CD",
		cidx : cidx,
		comment_Idx : comment_Idx
	 };
	 
	 $.ajax({
		 type: "GET",
		 url : "<%=path%>/CommentController",
		 data : dataStr,
		 success : function (data) {
			 //alert("data" + data);
			 location.replace("<%=path%>/CommentController?sw=E&idx="+comment_Idx);
		 }
	 })
	 
	 location.href="<%=path%>/CommentController?sw=CD&cidx="+cidx+"&comment_Idx="+commentIdx;
 }
</script>

<section>
 <br>
   <div align="center">
   <h2>코멘트 글 상세보기/수정하기 </h2>
     <form  action="<%=path %>/CommentController"  method=get >
   <input type=hidden  name=sw value=U>
   <table border=1 width=500>
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
       <input  type=submit value="글수정하기"> &emsp; 
       <input  type=submit value="목록보기"  onClick="action='<%=path %>/CommentController?sw=S'" >
       </td></tr>
   </table>   
   </form>     

  <form>
  <input  type=hidden  name=sw  value="CI" id=sw> 
  <input  type=hidden  name=comment_idx  value=<%=m.getIdx() %> id=comment_idx> 
  <table border=1 width=500>
    <tr> <td>&nbsp; <input  type=text size=40  name=commentContent id=commentContent>  </td>
         <td align="center"><input  type=button id=saveK value="코멘트저장" >  </td> 
    </tr>
  </table>
  </form>
  
   
   <table  border=1  width=500>
   <tr><th width=20>번호</th><th width=340>코멘트 </th><th width=100>날짜 </th><th width=40>삭제 </th></tr>
   <% for(CommentVO m1: li) { %>
   <input type=hidden value=<%=m1.getComment_idx() %>>
   <tr align="center">
      <td><%=m1.getCidx() %> </td>
      <td align="left"><%=m1.getCommentContent() %> </td>
      <td > &nbsp; <%=m1.getCommentDate() %></td>
      <td> <input type=button  value="삭제"
                  onclick="deleteK('<%=m1.getCidx()%>','<%=m1.getComment_idx() %>')" > </td>
   </tr>   
   <% } %>
   </table>
   
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 