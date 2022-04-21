package fstvl;

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
 
import fstvl.fstvlDAO;
import fstvl.fstvlSearchVO;
import fstvl.fstvlVO;
 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/map/*")
public class fstvlController extends HttpServlet{
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); 
		if (action == null || action.equals("/main.do")) { 
			String start = "";
			String end = ""; 
			String main = "";
			String sub = "";
			String name = "";
			fstvlDAO mapdao = new fstvlDAO();
			LocalDate now = LocalDate.now();
			String yearNow = Integer.toString(now.getYear());
			String monNow = Integer.toString(now.getMonthValue());
			String dayNow = Integer.toString(now.getDayOfMonth());
			if(Integer.parseInt(monNow)<10){
				if(Integer.parseInt(dayNow)<10)
					start = yearNow+"-0"+monNow+"-0"+dayNow;
				else
					start = yearNow+"-"+monNow+"-"+dayNow;
			}else{
				if(Integer.parseInt(dayNow)<10)
					start = yearNow+"-"+monNow+"-0"+dayNow;
				else
					start = yearNow+"-"+monNow+"-"+dayNow;
			}			
			if(monNow.equals("12")) {
				if(Integer.parseInt(dayNow)<10)
					end = Integer.toString(now.getYear()+1)+"-01-0"+dayNow;	
				else
					end = Integer.toString(now.getYear()+1)+"-01-"+dayNow;								
			}else if(Integer.parseInt(monNow)<9){
				if(Integer.parseInt(dayNow)<10)
					end = yearNow+"-0"+Integer.toString(now.getMonthValue()+1)+"-0"+dayNow;	
				else
					end = yearNow+"-0"+Integer.toString(now.getMonthValue()+1)+"-"+dayNow;				
			}else{
				if(Integer.parseInt(dayNow)<10)
					end = yearNow+"-"+Integer.toString(now.getMonthValue()+1)+"-0"+dayNow;	
				else
					end = yearNow+"-"+Integer.toString(now.getMonthValue()+1)+"-"+dayNow;				
			}
			fstvlSearchVO fsv = new fstvlSearchVO(start,end,main,sub,name);
			List<fstvlVO> maplist = mapdao.fstvlSearch(fsv);
			request.setAttribute("fsv", fsv);
			request.setAttribute("mapList", maplist);
			nextPage = "/main.jsp";
		}else if (action == null || action.equals("/mapSearch.do")) { 			  
			fstvlDAO mapdao = new fstvlDAO();	
			String start = request.getParameter("trip-start");
			String end = request.getParameter("trip-end"); 
			String main = request.getParameter("main");
			String sub = request.getParameter("sub");
			String name = request.getParameter("fname");
			fstvlSearchVO fsv = new fstvlSearchVO(start,end,main,sub,name);
			List<fstvlVO> maplist = mapdao.fstvlSearch(fsv);
			request.setAttribute("fsv", fsv);
			request.setAttribute("mapList", maplist);
			nextPage = "/main.jsp";
		}else if (action == null || action.equals("/mapDetail.do")) { 	 
			fstvlVO fdata = new fstvlVO();
			fstvlDAO mapdao = new fstvlDAO();
			String fnum = request.getParameter("fnum");
			fdata = mapdao.mapData(fnum);
			
			request.setAttribute("mapData",fdata);
			nextPage = "/mapDetail.jsp";
		}  
		else {
			nextPage = "/main.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
