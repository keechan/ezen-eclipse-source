package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.basicBoard.BoardServiceImpl;
import board.basicBoard.BoardService;
import board.basicBoard.BoardVO;

/**
 * Servlet implementation class BasicBoardController
 */
@WebServlet("/BasicBoardController")
public class BasicBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicBoardController() {
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
		String img = request.getParameter("img");
		
		vo.setIdx(idx);
		vo.setSname(sname);
		vo.setTitle(title);
		vo.setContent(content);		
		vo.setImg(img);
		
	    String realF =getServletContext().getRealPath("/basicBoard/files/");
	    
		if (sw.equals("I")) {
		
		 
		    System.out.println("realF(저장경로):" + realF);
		    int maxS =1024 * 1024 * 5 ; 
		    String encT = "UTF-8";
		    DefaultFileRenamePolicy DefaultF = new DefaultFileRenamePolicy();
		    
		    MultipartRequest multi = 
				   new MultipartRequest(request, realF, maxS, encT, DefaultF);
			
		        sname = multi.getParameter("sname");
			    title = multi.getParameter("title");
			    content = multi.getParameter("content");
			    
			    img = multi.getFilesystemName("img");
			    System.out.println("img:"+ img);
			    if (img == null) img = "space.png" ;
			    
			    vo.setSname(sname);
			    vo.setTitle(title);
			    vo.setContent(content);
			    vo.setImg(img);
			    
			service.insert(vo);
			response.sendRedirect(path+"/BasicBoardController?sw=S");
			
		} else if (sw.equals("U")) {
			
		    System.out.println("realF(저장경로):" + realF);
		    int maxS =1024 * 1024 * 5 ; 
		    String encT = "UTF-8";
		    DefaultFileRenamePolicy DefaultF = new DefaultFileRenamePolicy();
		    
		    MultipartRequest multi = 
				   new MultipartRequest(request, realF, maxS, encT, DefaultF);
			
		       idx = multi.getParameter("idx");
		       sname = multi.getParameter("sname");
			   title = multi.getParameter("title");
			   content = multi.getParameter("content");
			    
			   img = multi.getFilesystemName("img");
			   System.out.println("img:"+ img);
			   if (img == null) img = "space.png" ;
			    
			   vo.setIdx(idx);
			   vo.setSname(sname);
			   vo.setTitle(title);
			   vo.setContent(content);
			   vo.setImg(img);
			
			   BoardVO m=service.edit(vo);  
			   String delImg = m.getImg();
		
			   String delFile = realF + delImg;
			   System.out.println("realF(삭제파일명):" + delFile);
				 
			   File f =  new File(delFile);
			      			   
				if (img !=null) {
				  if (!img.equals("space.png")) {		
				      f.delete();
				  }	
				}			   
			   
				service.update(vo);			
				response.sendRedirect(path+"/BasicBoardController?sw=S");
			
		} else if (sw.equals("D")) {
			
			 String delFile = realF + img;
			 System.out.println("realF(삭제파일명):" + delFile);
		      File f =  new File(delFile);
		      if (f.exists()) {
		    	  if (!img.equals("space.png")) {
		            f.delete();
		    	  }
		      }
			
			service.delete(vo);
			response.sendRedirect(path+"/BasicBoardController?sw=S");
		} else if (sw.equals("E")) {
			service.cntCount(vo);
			request.setAttribute("m", service.edit(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/basicBoard/board_edit.jsp");
			Dispatcher.forward(request, response);
		} else if (sw.equals("S")) {
			// System.out.println("S확인");
			request.setAttribute("li", service.list(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/basicBoard/board_list.jsp");
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
