package DBPKG.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectController
 */
@WebServlet("/ProjectController")
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectController() {
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
		String sw =request.getParameter("sw");
		ProjectService service = new ProjectServiceImpl();
		
		if (sw.equals("S1")) {			
		    List<TeacherVO>	li = service.teacherSelect();		    
			request.setAttribute("li", li);
		    System.out.println("Controller(S1)------------->");
			RequestDispatcher	dispatcher
			   =request.getRequestDispatcher("S1.jsp");
			dispatcher.forward(request, response);
		}else if (sw.equals("jstl")) {
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			System.out.println("----------->" + code + ":" + name);
		}else if (sw.equals("S2")) {
			
			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("/member/S2.jsp");
			dispatcher.forward(request, response);
		
		}else if (sw.equals("S3")) {
			List<MemberVO> li = service.memberSelect();
			request.setAttribute("li", li);
			
			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("S3.jsp");
			dispatcher.forward(request, response);
		}else if (sw.equals("S4")) {			
			List<MoneyVO> li = service.moneySelect();
			request.setAttribute("li", li);
			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("S4.jsp");
			dispatcher.forward(request, response);
		}else if (sw.equals("S5")) {
			System.out.println("Controller S5 -------> ");
			List<ClassVO> li = service.classList();
			String[] arr = {"#ff00ff", "#223322", "#00ffBB", "#CECCff",
				            "#ff11ff", "#224422", "#01ffBB", "#CFCCff",
				            "#ff22ff", "#224422", "#02ffBB", "#CGCCff", "#443333"};
			request.setAttribute("li", li);
			request.setAttribute("arr", arr);
			for(ClassVO m : li) {
				System.out.println(m.C_NO);
				System.out.println(m.CLASS_AREA);
			}
			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("S5.jsp");
			dispatcher.forward(request, response);
		}else if (sw.equals("INDEX")) {
			//request.setAttribute(value, "철수");
			response.sendRedirect(path+"/index.jsp");
			
		}else if (sw.equals("INSERT")) {
			// REGIST_MONTH, C_NO,CLASS_AREA,TUITION,TEACHER_CODE
			String REGIST_MONTH=request.getParameter("REGIST_MONTH");
			String C_NO=request.getParameter("C_NO");
			String CLASS_NAME=request.getParameter("CLASS_NAME");
			String ADDRESS=request.getParameter("ADDRESS");
			int TUITION= Integer.parseInt(request.getParameter("TUITION")) ;
			
			ClassVO vo = new ClassVO();
			vo.setC_NO(C_NO);
			vo.setCLASS_AREA(ADDRESS);
			vo.setREGIST_MONTH(REGIST_MONTH);
			vo.setTEACHER_CODE(CLASS_NAME);
			vo.setTUITION(TUITION);
			
			service.insert(vo);
			
			response.sendRedirect(path+"/index.jsp");
		} else if (sw.equals("ME")) {
			String cno = request.getParameter("cno");
			//System.out.println("ProjectController ----> "+cno);
			MemberVO m = service.memberEdit(cno); 
			//System.out.println("ProjectController ----> "+m);
			request.setAttribute("m", m);
			
			List<MemoVO> li = service.memoList(cno);
			System.out.println("ProjectController ----> "+li);
			request.setAttribute("li", li);

			RequestDispatcher dispatcher
			   =request.getRequestDispatcher("/member/MemberEdit.jsp");
			dispatcher.forward(request, response);
		} else if (sw.equals("MI")) {
			// IDX, C_NO cno, WRITER, MEMO 
			String cno = request.getParameter("cno");
			String writer = request.getParameter("c_writer");
			String memo = request.getParameter("memo");
			
			MemoVO vo = new MemoVO();
			vo.setCno(cno);
			vo.setWriter(writer);
			vo.setMemo(memo);
			System.out.println("Controller(MI) ----------> " + cno);
			service.memoInsert(vo);			
			response.sendRedirect(path+"/ProjectController?sw=ME&cno="+cno);
		} else if (sw.equals("MD")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String cno = request.getParameter("cno");
			System.out.println("Controller(MD) ----------> " + cno);
			service.memoDelete(idx);
			response.sendRedirect(path+"/ProjectController?sw=ME&cno="+cno);
			
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
