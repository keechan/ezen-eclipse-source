package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.reBoard.BoardService;
import board.reBoard.BoardServiceImpl;
import board.reBoard.BoardVO;

/**
 * Servlet implementation class ReBoardController
 */
@WebServlet("/ReBoardController")
public class ReBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReBoardController() {
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
		
		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		BoardService service =  new  BoardServiceImpl();
		
		BoardVO vo = new BoardVO();
		String idx = request.getParameter("idx");
		String sname = request.getParameter("sname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		vo.setIdx(idx);
		vo.setSname(sname);
		vo.setTitle(title);
		vo.setContent(content);		
		
		if (sw.equals("I")) {
			service.insert(vo);
			response.sendRedirect(path+"/ReBoardController?sw=S");
			
		} else if (sw.equals("RW")) {
			int ref = Integer.parseInt(request.getParameter("ref")) ;
			int re_level = Integer.parseInt(request.getParameter("re_level"));
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			vo.setRef(ref);
			vo.setRe_level(re_level);
			vo.setRe_step(re_step);
			
			System.out.println("RW확인" + vo);
			service.rwUpdate(vo);
			service.rwInsert(vo);
			response.sendRedirect(path+"/ReBoardController?sw=S");
			
		} else if (sw.equals("F")) {
			System.out.println("F확인");
			response.sendRedirect(path+"/reBoard/board_write.jsp");
			
		} else if (sw.equals("S")) {
			System.out.println("S확인");
			request.setAttribute("li", service.list(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/reBoard/board_list.jsp");
			Dispatcher.forward(request, response);
		} else if (sw.equals("D")) {
			service.delete(vo);
			response.sendRedirect(path+"/ReBoardController?sw=S");
		} else if (sw.equals("E")) {
			// service.cntCount(vo);
			request.setAttribute("m", service.edit(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/reBoard/board_edit.jsp");
			Dispatcher.forward(request, response);
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
