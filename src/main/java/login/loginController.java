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
			session.removeAttribute("msg");
			session.removeAttribute("check1");
			nextPage = "/map/main.do";
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
		}else {
			nextPage = "/main.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}