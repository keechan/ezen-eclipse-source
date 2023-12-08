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

import board.basicBoard.BoardService;
import board.basicBoard.BoardServiceImpl;
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
		
		vo.setIdx(idx);
		vo.setSname(sname);
		vo.setTitle(title);
		vo.setContent(content);

		String realF = getServletContext().getRealPath("/basicBoard/files/");		
		if (sw.equals("I")) {

			int max5 = 1024 * 1024 * 5;
			String encT = "UTF-8";
			DefaultFileRenamePolicy DefaultF = new DefaultFileRenamePolicy();
			MultipartRequest multi = new MultipartRequest(
				request, 
				realF, 
				max5, 
				encT, 
				DefaultF
			);
			
			//idx = multi.getParameter("idx");
			sname = multi.getParameter("sname");
			title = multi.getParameter("title");
			content = multi.getParameter("content");		
			String img = multi.getFilesystemName("img");

			if (img == null) 
				img = "space.png";
			
			vo.setSname(sname);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setImg(img);
			
			service.insert(vo);
			response.sendRedirect(path+"/BasicBoardController?sw=S");
		} else if (sw.equals("U")) {
			int max5 = 1024 * 1024 * 5;
			String encT = "UTF-8";
			DefaultFileRenamePolicy DefaultF = new DefaultFileRenamePolicy();
			MultipartRequest multi = new MultipartRequest(
				request, 
				realF, 
				max5, 
				encT, 
				DefaultF
			);
			
			idx = multi.getParameter("idx");
			sname = multi.getParameter("sname");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			String img = multi.getFilesystemName("img");
			
			//oldImg :  X, O => X
			//newImg : O
			if (img == null) 
				img = "space.png"; 
			
			vo.setIdx(idx);
			vo.setSname(sname);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setImg(img);
			
			//업데이트 전기존파일 정보 가져오기
			BoardVO m = service.edit(vo);
			String delImg = m.getImg();
			System.out.println("DaoImpl -------1> " + img);
			System.out.println("DaoImpl -------2> " + delImg);
			service.update(vo);

			//기존 파일 삭제 로직
			//if(파일이 넘어오면) {
			String delFile = realF + delImg;
			if(img != null) {
				//if(기존파일이 space.png 가 아니면 ..content. ) {
				if(!delImg.equals("space.png")) {
				  //--> 기존 파일 삭제
				  File f = new File(delFile);
				  f.delete();
				}
			}
			
			response.sendRedirect(path+"/BasicBoardController?sw=S");
		} else if (sw.equals("D")) {
			String imgParam = request.getParameter("img");
			System.out.println("getParameter===========>" + imgParam);
			
			String delFile = realF + imgParam;
			System.out.println("BasicBoardController ======> " + delFile);
			File f = new File(delFile);
			if(!delFile.equals("space.png")) {
				f.delete();
			}
			
			System.out.println("BasicBoardController =========>" + imgParam);
			service.delete(vo);
			System.out.println("BasicController---->(2)");
			response.sendRedirect(path+"/BasicBoardController?sw=S");
		} else if (sw.equals("E")) {
			//service.cntCount(vo);
			request.setAttribute("m", service.edit(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/basicBoard/board_edit.jsp");
			Dispatcher.forward(request, response);
		} else if (sw.equals("S")) {
			request.setAttribute("li", service.list(vo));
			RequestDispatcher Dispatcher 
			   = request.getRequestDispatcher("/basicBoard/board_list.jsp");
			Dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
