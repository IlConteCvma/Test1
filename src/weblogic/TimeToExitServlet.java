package weblogic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import execption.EntityNotFoundException;
import execption.TimeException;
import logic.Session;
import logic.bean.StudentBean;
import logic.bean.TimeToExitBean;
import logic.controller.ViewTimeToExitController;

/**
 * Servlet implementation class TimeToExitServlet
 */
@WebServlet("/TimeToExitServlet")
public class TimeToExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GOBACK = "/prova.jsp";
	private int seatOccuped;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeToExitServlet() {
		super();
		seatOccuped = -1;
	}
	
	public TimeToExitServlet(int index) {
		super();
		seatOccuped = index;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentBean sBean = new StudentBean();
		ViewTimeToExitController useCaseController = new ViewTimeToExitController();
		sBean.setUsername(Session.getSession().getStudent().getUsername());

		if(seatOccuped != -1) {
			if(seatOccuped == 0) {
				request.setAttribute("exit", 7);
			}else {
				request.setAttribute("exit", 8);
			}
		}
		
		// Passa il controllo alla nuova pagina
		try {
			TimeToExitBean timeBean = useCaseController.estimateTimeToExit();
			if(seatOccuped!=-1) {
				Session.getSession().setIndexOfSeat(seatOccuped);
			}
			request.getSession().setAttribute("timeBean2", timeBean);
			request.getRequestDispatcher("timeToExit.jsp").forward(request,response);
		} catch (TimeException t) {
			request.setAttribute("exit", 2);
			request.getRequestDispatcher(GOBACK).forward(request,response);
		} catch (EntityNotFoundException l) {
			request.setAttribute("exit", 3);
			request.getRequestDispatcher(GOBACK).forward(request,response);
		} catch(JSONException e) {
			request.setAttribute("exit", 4);
			request.getRequestDispatcher(GOBACK).forward(request,response);
		}catch (IOException e) {
			request.setAttribute("exit", 5);
			request.getRequestDispatcher(GOBACK).forward(request,response);
		} catch (SQLException e) {
			request.setAttribute("exit", 6);
			request.getRequestDispatcher(GOBACK).forward(request,response);
		}

	}

}
