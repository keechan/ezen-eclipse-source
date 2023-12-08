package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.pageBoard1.*;
/**
 * Servlet implementation class PageBoardController
 */
@WebServlet("/PageBoardController")
public class PageBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String  path = request.getContextPath();
		
		String sw = request.getParameter("sw");
		
		String ch1 = request.getParameter("ch1");
		String ch2 = request.getParameter("ch2");
		String idx = request.getParameter("idx");
		String sname = request.getParameter("sname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setCh1(ch1);
		vo.setCh2(ch2);
		vo.setIdx(idx);
		vo.setSname(sname);
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardService service = new BoardServiceImpl();
		if (sw.equals("big")) {
			service.bigInsert(null);			
			response.sendRedirect(path + "/index.jsp");
		} else if (sw.equals("U")) {
			System.out.println("U확인");
		    int start =  Integer.parseInt(request.getParameter("start"));
			service.update(vo);
			response.sendRedirect(path+"/PageBoardController?sw=S&start="+start+"&ch1="+ch1+"&ch2="+ch2);
			
		} else if (sw.equals("S")) {
		 String start = request.getParameter("start");
		 int  tc = service.totalCount(vo)	;

	      int pagesize = 11;
	      int pageListSize = 15 ;
	      int totalPage = (int) Math.ceil((double) tc / pagesize)  ;
	      
	      int  startIdx = 0;
	      if (start == null) {
	    	startIdx = 1;
	      }else {
	    	startIdx = Integer.parseInt(start);
	      }
	      
	      int nowPage = startIdx / pagesize + 1;
	      
		  vo.setPageSize( pagesize );	
		  vo.setStart(startIdx);
		  
		  List<BoardVO>	li = service.select(vo);
		  
		  System.out.println("===[PageBoardController(S)]===");
		  System.out.println("ch1:"+ ch1 +"ch2:" + ch2 + "start:" + start);	
		  
		  request.setAttribute("li", li);

		  request.setAttribute("tc", tc);
		  request.setAttribute("totalPage", totalPage);
		  request.setAttribute("nowPage", nowPage);
		  request.setAttribute("pageSize", pagesize);
		  request.setAttribute("pageListSize", pageListSize);
		  request.setAttribute("start", startIdx);
		  request.setAttribute("ch1", ch1);
		  request.setAttribute("ch2", ch2);
			 
		  RequestDispatcher dispatcher 
		      = request.getRequestDispatcher("/pageBoard1/list.jsp");
		  dispatcher.forward(request, response);
		 
		} else if (sw.equals("E")) {
			  vo.setStart(Integer.parseInt(request.getParameter("start")));			  
			  BoardVO	m = service.edit(idx);
			  
              m.setCh1(ch1);
              m.setCh2(ch2);
			  m.setStart(Integer.parseInt(request.getParameter("start")));
			  
			  request.setAttribute("m", m);
				  
			  RequestDispatcher dispatcher 
			      = request.getRequestDispatcher("/pageBoard1/edit.jsp");
			  dispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
