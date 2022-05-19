package trrsrt;

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
import login.loginVO;
import weather.sweatherVO;
import weather.sweatherDAO;
 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/trr/*")
public class trrsrtController extends HttpServlet{
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
		HttpSession session = request.getSession();
		String action = request.getPathInfo(); 
		if (action == null || action.equals("/main.do")) { 
			session.removeAttribute("check1");
			String main = "충청북도";
			String sub = "";
			String name = "";
			String checkFavor="";
			trrsrtDAO mapdao = new trrsrtDAO();
			if (session == null || session.getAttribute("checkFavor") == null || session.getAttribute("checkFavor").equals("")) {				
				
			}else {
				checkFavor = String.valueOf(session.getAttribute("checkFavor"));  
				session.removeAttribute("checkFavor");
				request.setAttribute("checkFavor",checkFavor);
			}
			trrsrtSearchVO tsv = new trrsrtSearchVO(main,sub,name);
			List<trrsrtVO> maplist = mapdao.trrsrtSearch(tsv);
			request.setAttribute("tsv", tsv);
			request.setAttribute("mapList", maplist);
			nextPage = "/trrsrtMain.jsp";
		}else if (action.equals("/trrSearch.do")) { 			  
			trrsrtDAO mapdao = new trrsrtDAO();	
			String main = request.getParameter("main");
			String sub = request.getParameter("sub");
			String name = request.getParameter("tname");
			trrsrtSearchVO tsv = new trrsrtSearchVO(main,sub,name);
			List<trrsrtVO> maplist = mapdao.trrsrtSearch(tsv);
			request.setAttribute("tsv", tsv);
			request.setAttribute("mapList", maplist);
			nextPage = "/trrsrtMain.jsp";
		}else if (action.equals("/trrDetail.do")) {
			trrsrtVO tdata = new trrsrtVO();
			trrsrtDAO mapdao = new trrsrtDAO();
			sweatherVO swdata = new sweatherVO();
			sweatherDAO swdao = new sweatherDAO();
			String tnum = "";
			String checkFavor = "";
			
			if (session == null || session.getAttribute("reDetailt") == null || session.getAttribute("reDetailt").equals("")) {
				tnum = request.getParameter("tnum");
				
			}else {
				if (session == null || session.getAttribute("checkFavor") == null || session.getAttribute("checkFavor").equals("")) {				
					tnum = String.valueOf(session.getAttribute("reDetailt"));    //
					session.removeAttribute("reDetailt");
				}else {
					checkFavor = String.valueOf(session.getAttribute("checkFavor")); 
					tnum = String.valueOf(session.getAttribute("reDetailt")); 
					session.removeAttribute("reDetailt");
					session.removeAttribute("checkFavor");
					request.setAttribute("check",checkFavor);
				}
			}
			tdata = mapdao.trrData(tnum);
			swdata = swdao.sweatherS(tdata.getRdnmadr(),tdata.getLnmadr());
			request.setAttribute("mapData",tdata);
			request.setAttribute("sweatherData",swdata);
			nextPage = "/trrsrtDetail.jsp";
		}else if (action.equals("/distance.do")) {
			List<trrsrtVO> tdata = new ArrayList<trrsrtVO>();
			trrsrtDAO mapdao = new trrsrtDAO();
			String fnum = "",latitude="",longitude="",km="",fname="";
			fnum = request.getParameter("fnum");
			fname = request.getParameter("fname");
			latitude = request.getParameter("lat");
			longitude = request.getParameter("lot");
			km = request.getParameter("km");

			tdata = mapdao.fstvltrrsrt(latitude, longitude, km);
			request.setAttribute("fnum", fnum);
			request.setAttribute("fname", fname);
			request.setAttribute("mapList", tdata);
			nextPage = "/trrsrtDistance.jsp";
		}   
		else {
			nextPage = "/main.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}