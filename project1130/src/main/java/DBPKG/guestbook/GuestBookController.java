package DBPKG.guestbook;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GuestBookController
 */
@WebServlet("/GuestBookController")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		GuestBookService service = new GuestBookServiceImpl();
		
		if (sw.equals("list")) {
			System.out.println("GuestBookController --------> list");
			String ch1 = request.getParameter("ch1");
			String ch2 = request.getParameter("ch2");
			String start = request.getParameter("start");
			
			//페이징
			int startInt = 0;
			int pageSize = 10;
			int pageListSize = 15;
			if (start == null) {
				startInt = 1;
			} else {
				startInt = Integer.parseInt(request.getParameter("start"));
			}
			//NULL처리...
			if(ch1 == null) {
				ch1 = "A";
			}
			if(ch2 == null) {
				ch2 = "A";
			}

			//System.out.println(ch1 + ch2);
			//ch1 = request.getParameter("ch1");
			//ch2 = request.getParameter("ch2");
			GuestBookVO vo = new GuestBookVO();
			vo.setCh1(ch1);
			vo.setCh2(ch2);
			//페이징...
			vo.setStart(startInt);
			vo.setPageSize(pageSize);

			//String tp = String.valueOf(service.totalPage());

		    List<GuestBookVO> li = service.list(vo);
			int totalCount = service.rowCount(vo);
			int totalPage = (int) Math.ceil((double) totalCount / pageSize);
			int nowPage = startInt / pageSize + 1;
			int lastPage = (totalPage - 1) * pageSize + 1;
			int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
			int listEndPage = listStartPage + pageListSize - 1 ;

			request.setAttribute("li", li);
			request.setAttribute("totalCount", totalCount);

			request.setAttribute("ch1", ch1);
			if(ch2 != null) {
				ch2 = URLEncoder.encode(ch2, "UTF-8");
			}
			request.setAttribute("ch2", ch2);
			
			request.setAttribute("start", startInt);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("listStartPage", listStartPage);
			request.setAttribute("listEndPage", listEndPage);
			RequestDispatcher dispatcher
			   = request.getRequestDispatcher("/guestbook/list.jsp");
			dispatcher.forward(request, response);
		} else if (sw.equals("write")) {
			System.out.println("GuestBookController --------> write");
			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("/guestbook/write.jsp");
			dispatcher.forward(request, response);
		} else if (sw.equals("insert")) {
			String idx=request.getParameter("idx");
			String name=request.getParameter("name");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			GuestBookVO vo = new GuestBookVO();
			vo.setIdx(Integer.parseInt(idx));
			vo.setName(name);
			vo.setTitle(title);
			vo.setContent(content);
			
			service.write(vo);

			response.sendRedirect(path+"/GuestBookController?sw=list");
		} else if (sw.equals("del")) {
			String idx = request.getParameter("idx");
			service.delete(idx);
			String ch1 = "";
			String ch2 = "";
			ch1 = request.getParameter("ch1");
			ch2 = request.getParameter("ch2");
			//list.jsp에서처럼 한글처리...
			ch2 = URLEncoder.encode(ch2, "UTF-8");
			response.sendRedirect(path+"/GuestBookController?sw=list&ch1=" + ch1 + "&ch2=" + ch2 );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doGet(request, response);
	}

}
