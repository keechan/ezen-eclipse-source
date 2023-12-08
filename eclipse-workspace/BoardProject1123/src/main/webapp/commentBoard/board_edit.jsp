<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/include/top.jsp" %> 
<%@ page import ="board.comment.*" %> 
<%@ page import ="java.util.*" %> 
<%
BoardVO   m =(BoardVO) request.getAttribute("m");
List<CommentVO>   li =(List<CommentVO>) request.getAttribute("li");
%>

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
jQuery.ajaxSetup({cache:false});
$(document).ready( function(){
	
	  $('#saveK').click( function(){		 		 
		  alert("sw확인:" + $('#cisw').val() );
		  var  dataStr={
				  sw : $('#cisw').val(),
				  comment_idx : $('#comment_idx').val(),
				  commentContent : $('#commentContent').val()	
		  };
		  		 
		  $.ajax({			 
			  type: "GET",
			  url : "<%=path%>/CommentController",
			  data : dataStr,
			  success: function (data){
			  // alert("저장성공!!" + $('#sno').val() );
			  location.replace("<%=path%>/CommentController?sw=E&idx="+$('#comment_idx').val()) ;
			  }	  
		  })		 
	  }) /* saveK 끝  */
	  
	  $('#updateK').click( function(){		 		 
		  alert("usw확인:" + $('#usw').val());
		  var  dataStr={
				  sw : $('#usw').val(),
				  idx : $('#idx').val(),
				  title : $('#title').val(),
				  sname : $('#sname').val(),
				  content : $('#content').val()	
		  };
		  		 
		  $.ajax({			 
			  type: "GET",
			  url : "<%=path%>/CommentController",
			  data : dataStr,
			  success: function (data){
			  alert("data" + data )
			  location.replace("<%=path%>/CommentController?sw=S") ;
			  }	  
		  })		 
	  }) /* updateK 끝  */
	  
	  
	  $('#listK').click( function(){
			  location.replace("<%=path%>/CommentController?sw=S") ;
	  }) /* listK 끝  */
	  
	  
	  
})


 function  deleteK(cidx , comment_Idx){	
	  var  dataStr={
			  sw : "CD",
			  cidx : cidx,
			  comment_Idx : comment_Idx
	  };
	  		 
	  $.ajax({			 
		  type: "GET",
		  url : "<%=path%>/CommentController",
		  data : dataStr,
		  success: function (data){
		  alert("data" + data + ":" + comment_Idx)
		  location.replace("<%=path%>/CommentController?sw=E&idx="+comment_Idx) ;
		  }	  
	  })		
	
 }

</script>

<section>
 <br>
   <div align="center">
   <h2>코맨트 글 상세보기/수정하기 </h2>
   

   <input type=hidden  name=sw value=U  id=usw>
   <table border=1 width=560>
   <tr><td>번호 </td>
      <td><input type=text  name=idx  id=idx  size=10 value=<%=m.getIdx() %> readonly ></td></tr>
   <tr><td>제목 </td><td><input type=text  name=title id=title  size=30  value="<%=m.getTitle() %>">  </td></tr>
   <tr><td>글쓴이 </td><td><input type=text  name=sname id=sname  value="<%=m.getSname() %>"> </td></tr>
   <tr><td>글내용 </td>
        <td>
          <textarea cols=40  rows=5 name=content id=content><%=m.getContent() %></textarea> 
   		</td>
   </tr>
   <tr><td colspan=2 align="center">
       <input  type=button value="글수정하기" id="updateK" > &emsp; 
       <input  type=button value="목록보기"  id="listK"  >
       </td></tr>
   </table>   
     

  <input  type=hidden  name=sw  value="CI"  id=cisw > 
  <input  type=hidden  name=comment_idx  value=<%=m.getIdx() %> id=comment_idx > 
  <table border=1 width=560>
    <tr> <td>&nbsp; <input  type=text size=57  name=commentContent id=commentContent>  </td>
         <td align="center"><input  type=button id="saveK"  value="코맨트저장" >  </td> 
    </tr>
  </table>   
  
   
   <table  border=1  width=560>
   <tr><th width=20>번호 </th><th width=340>코맨트 </th><th width=100>날짜 </th><th width=40>삭제 </th></tr>
   <% for(CommentVO m1: li) { %>
   <tr align="center">
      <td><%=m1.getCidx() %> </td>
      <td align="left"><%=m1.getCommentContent() %> </td>
      <td > &nbsp; <%=m1.getCommentDate() %></td>
      <td> <input type=button  value="삭제"
                  onclick="deleteK('<%=m1.getCidx()%>','<%=m.getIdx()%>')" > </td>
   </tr>   
   <% } %>
   </table>      
   
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 