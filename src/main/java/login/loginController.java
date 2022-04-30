package login;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fstvl.fstvlVO;
 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/login/*")
public class loginController extends HttpServlet{
	private static final long serialVersionUID = 1L; 
	
	public void init(){ 
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;  
		loginDAO logindao = new loginDAO();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); 
		if (action.equals("/login.do")) { 
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd"); 	
			loginVO login = logindao.login(id,pwd);
			String check1="";
			check1 = login.getIdnum();
			session.setAttribute("check1",check1);
			if(check1.equals("0")) {				
			}else {
				session.setAttribute("msg",login);
			}
			nextPage = "/map/remain.do";
		}else if (action.equals("/memberForm.do")) { 
			nextPage = "/memForm.jsp";
		}else if (action.equals("/logout.do")) { 
			session.invalidate();
			nextPage = "/map/main.do";
		}else if (action.equals("/favor.do")) {
			String checkFavor ="";
			String fnum ="0";
			String tnum ="0";
			loginVO vo = (loginVO)session.getAttribute("msg"); 
			String idnum = vo.getIdnum();
			if(request.getParameter("fnum")!=null) {
				fnum=request.getParameter("fnum");
			}
			if(request.getParameter("tnum")!=null) {
				fnum=request.getParameter("tnum");
			}
			checkFavor=	logindao.addFavor(idnum,fnum,tnum);
			session.setAttribute("checkFavor",checkFavor);
			nextPage = "/map/remain.do";
		}else if (action.equals("/favor2.do")) {
			String checkFavor ="";
			String fnum ="0";
			String tnum ="0";
			loginVO vo = (loginVO)session.getAttribute("msg"); 
			String idnum = vo.getIdnum();
			if(request.getParameter("fnum")!=null) {
				fnum=request.getParameter("fnum");
				session.setAttribute("reDetail",fnum);
			}
			if(request.getParameter("tnum")!=null) {
				fnum=request.getParameter("tnum");
				session.setAttribute("reDetailt",tnum);
			}
			checkFavor=	logindao.addFavor(idnum,fnum,tnum); 
			session.setAttribute("checkFavor",checkFavor);
			nextPage = "/map/mapDetail.do";
		}else if (action.equals("/addMember.do")) { 
			loginVO log= new loginVO(request.getParameter("id"),request.getParameter("pass")); 
			String check="0";
			if(request.getParameter("id").equals("") || request.getParameter("pass").equals("")) {
				check="2";
				request.setAttribute("check",check);
				nextPage = "/memForm.jsp";
			}else {
				check = logindao.addMember(log);
				if(check.equals("1")) {
					request.setAttribute("check",check);
					nextPage = "/memForm.jsp";
				}else { 
					nextPage = "/map/remain.do";
				}
			}			
		}else if (action.equals("/mypage.do")) { 
			favorDAO favordao = new favorDAO();			
			loginVO loginvo = (loginVO)session.getAttribute("msg");
			List<fstvlVO> maplist = favordao.fstvlSearch(loginvo.getIdnum());
			request.setAttribute("mapList", maplist);
			nextPage = "/mypage.jsp";
		}else if (action.equals("/favorDel.do")) { 
			favorDAO favordao = new favorDAO();			
			loginVO loginvo = (loginVO)session.getAttribute("msg");
			if(request.getParameter("fnum").equals("null")) {
				
			}else {
				favordao.fnumdel(loginvo.getIdnum(),request.getParameter("fnum"));
			}			
			List<fstvlVO> maplist = favordao.fstvlSearch(loginvo.getIdnum());
			request.setAttribute("mapList", maplist);
			nextPage = "/mypage.jsp";
		}else {
			nextPage = "/main.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}