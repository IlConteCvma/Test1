package weblogic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.CourseBean;
import logic.bean.UserBean;
import logic.controller.RegistrationController;
import logic.model.TypeVehicle;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String street = request.getParameter("street");
		String streetNumber = request.getParameter("streetNumber");
		String city = request.getParameter("city");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String typeVehichle = request.getParameter("vehicle");
		String course = request.getParameter("course");
		if(name.isEmpty() || surname.isEmpty() || street.isEmpty() || streetNumber.isEmpty() || username.isEmpty() || city.isEmpty() || password.isEmpty()) {
			request.setAttribute("exit", 0);
			request.getRequestDispatcher("/registration.jsp").forward(request, response);
		}else {
			UserBean userB = new UserBean();
			userB.setName(name);
			userB.setSurname(username);
			userB.setAddress(street);
			userB.setNumberOfStreet(streetNumber);
			userB.setCity(city);
			userB.setUsername(username);
			userB.setPassword(password);
			userB.setVehicle(TypeVehicle.valueOf(typeVehichle));
			RegistrationController regCtrl = new RegistrationController();
			try {
				regCtrl.createStudent(userB);
				CourseBean cBean = regCtrl.findCourse(course);
				request.setAttribute("courseBean", cBean);
				request.getRequestDispatcher("/registration2.jsp").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("exit", 2);
			}
		}
	}

}
