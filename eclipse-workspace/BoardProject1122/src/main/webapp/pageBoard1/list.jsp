<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="board.pageBoard1.*" %> 
<%@ include file ="/include/top.jsp" %> 

<%
  @SuppressWarnings("unchecked")
  List<BoardVO>  li = (List<BoardVO>) request.getAttribute("li");
  
  int  start = (int) request.getAttribute("start");
  String  ch1 = (String) request.getAttribute("ch1");
  String  ch2 = (String) request.getAttribute("ch2");
  
  int  tc = (int) request.getAttribute("tc");
  int  totalPage = (int) request.getAttribute("totalPage");
  int  nowPage = (int) request.getAttribute("nowPage"); 
  int  pageSize = (int) request.getAttribute("pageSize");  
  int  pageListSize = (int) request.getAttribute("pageListSize"); 

          
  int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
  int listEndPage = listStartPage + pageListSize - 1 ;
%>
<script>
	function deleteK(idx, start, ch1 , ch2){
		location.href="<%=path %>/PageBoardController?sw=D&idx="+idx+"&start="+start+"&ch1="+ch1+"&ch2="+ch2
	}
</script>
<section>
 <br>
   <div align="center">
    1.페이지사이즈 : <%=pageSize %> &emsp;
    2.페이지 List 사이즈(숫자개수) :   <%=pageSize %> &emsp;
    3.전체레코드 수 : <%=tc %> &emsp;
    4.총 페이지수 : <%=totalPage %> 
    <br>
    5.현재 레코드 : <%=start %> &emsp;
    6.현재 페이지 : <%=nowPage %> &emsp;
    7.가로 하단 시작 : <%=listStartPage %> &emsp;
    8.가로 하단 마지막 :<%=listEndPage %>
   
   <h2>게시판 글 목록보기  </h2>
   <table  border=1  width=500>
   <tr>
     <th>rownum </th><th>rnum </th><th>번호 </th><th>이름 </th><th>제목 </th><th>조회수</th><th>삭제</th>
   </tr>
   <%
   if (ch2 != null ){
   ch2 = java.net.URLEncoder.encode(ch2, "UTF-8");
   }
   for(BoardVO m : li) { 
	  
   %>
   <tr align="center">
      <td><%=m.getRownum() %> </td> 
      <td><%=m.getRnum() %> </td> 
         
      <td><%=m.getIdx() %> </td>    
      <td><%=m.getSname() %> </td>
      <td align="left"> &nbsp;

	      <a href="<%=path %>/PageBoardController?sw=E&idx=<%=m.getIdx() %>&start=<%=start %>&ch1=<%=ch1%>&ch2=<%=ch2%>">
	         <%=m.getTitle() %>
	      </a>
      </td>      
      <td><%=m.getCnt() %></td>
      <td><input type=button value="삭제하기" onClick="deleteK('<%=m.getIdx() %>','<%=m.getStart() %>','<%=m.getCh1() %>','<%=ch2 %>')"></td>      
      
   </tr>   
   <% } %>
   </table> 
             
     <a href=PageBoardController?sw=S&start=1&ch1=<%=ch1 %>&ch2=<%=ch2 %>>처음으로</a>&emsp;&emsp;
   
      <%  // [1],[16], ..       15  : 15보다 큰경우 이전이 존재  
       if (listStartPage > pageListSize) { 
        start = (listStartPage - pageListSize -1) * pageSize  + 1;
                // 16 - 15 - 1 = 0 * 11 + 1 = 1
                // 31 - 15 - 1 = 15 * 11 + 1 = 166 		   
       %>           
      <a href=PageBoardController?sw=S&start=<%=start %>&ch1=<%=ch1 %>&ch2=<%=ch2 %>>이전<%=pageListSize %> </a> &emsp;&emsp;
      <% } else { %>
      이전 &emsp;&emsp;
      <% } %>     
     <% 
         // i =1 --> 1,  i = 2 --> 11 , i = 3 --> 21
         for (int i =listStartPage ; i <= listEndPage  ; i++ ) {
        	 start = (i-1) * pageSize + 1;
	         if ( i <= totalPage ) {	 
	     %>     
	          <a href=PageBoardController?sw=S&start=<%=start %>&ch1=<%=ch1 %>&ch2=<%=ch2 %>>[<%=i %>]</a>&nbsp;
	      <%
	          }
         }
     %>     
     <%     
     if ( listEndPage < totalPage ) { 
    	 start = listEndPage * pageSize + 1;  // 15*11+1 =166 , 30*11+1 = 331 , 
     %>
     &emsp; <a href=PageBoardController?sw=S&start=<%=start %>&ch1=<%=ch1 %>&ch2=<%=ch2 %>>다음<%=pageListSize %></a>
     <% } else { %>
     &emsp; 다음
      <% } %>      
      &emsp;&emsp;
      <%
      int    endPage = ( totalPage - 1) * pageSize + 1 ;
      %>
      <a href=PageBoardController?sw=S&start=<%=endPage %>&ch1=<%=ch1 %>&ch2=<%=ch2 %>>마지막</a>
          
          
      <form action = PageBoardController >
       <input  type=hidden   name=sw  value="S">
       <select name=ch1>
         <option value="sname" > 이 름 </option>
         <option value="title" > 제 목 </option>
       </select>
       <input  type=text name=ch2>
       <input  type=submit value="검색하기">
      </form>    
   </div>   
 <br>
</section>
<%@ include file ="/include/bottom.jsp" %> 