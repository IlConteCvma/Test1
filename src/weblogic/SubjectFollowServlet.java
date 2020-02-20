package weblogic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controller.RegistrationController;

/**
 * Servlet implementation class SubjectFollowServlet
 */
@WebServlet("/SubjectFollowServlet")
public class SubjectFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectFollowServlet() {
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
		String[] names = request.getParameterValues("subject");
		List<String> list = Arrays.asList(names);
		RegistrationController regCtrl = new RegistrationController();
		try {
			regCtrl.followSubject(list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("exit", 2);
		}
	}

}
