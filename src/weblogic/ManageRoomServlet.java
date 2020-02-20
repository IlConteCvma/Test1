package weblogic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.SeatBean;
import logic.bean.TimeToExitBean;
import logic.controller.BookSeatController;

/**
 * Servlet implementation class ManageRoomServlet
 */
@WebServlet("/ManageRoomServlet")
public class ManageRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageRoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int indexSeat = Integer.parseInt(request.getParameter("seat"));
		String color = request.getParameter(""+indexSeat);
		BookSeatController controlSeat = new BookSeatController();
		TimeToExitBean req = (TimeToExitBean)request.getSession().getAttribute("timeBean2");
		SeatBean seatOfStudent = new SeatBean();
		seatOfStudent.setIndex(indexSeat);
		seatOfStudent.setRoom(req.getNextLesson().getRoomLesson());
		TimeToExitServlet service = null;
		if(color.equals("green")) {
			try {
				controlSeat.occupateSeat(seatOfStudent);
				service = new TimeToExitServlet(indexSeat);
			} catch (SQLException e) {
				request.setAttribute("exit", 2);
			}
		}else {
			try {
				controlSeat.freeSeat(seatOfStudent);
				service = new TimeToExitServlet(0);
			} catch (SQLException e) {
				request.setAttribute("exit", 2);
			}
		}
		service.doPost(request, response);
	}

}
