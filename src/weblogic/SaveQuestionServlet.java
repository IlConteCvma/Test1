package weblogic;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import execption.QuestionException;
import logic.bean.QuestionBean;
import logic.controller.InsertQuestionController;
import logic.model.QuestionExerciseFactory;
import logic.model.QuestionFactory;
import logic.model.QuestionProblemFactory;
import logic.model.QuestionType;


/**
 * Servlet implementation class SaveQuestionServlet
 */
@WebServlet("/SaveQuestionServlet")
public class SaveQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 647201;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveQuestionServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject = request.getParameter("subject");
		String title = request.getParameter("title");
		String body = request.getParameter("body");
	
		
		String image = null;
		QuestionFactory factory;
		QuestionBean qBean;
		if(!title.isEmpty() && !body.isEmpty()) {
			
			if(request.getParameter("typeQuest") != null) {
				factory = new QuestionExerciseFactory();
				image = request.getParameter("image");
			}
			else {
				factory = new QuestionProblemFactory();
			}
			
			qBean=factory.createBean();
			qBean.setTitle(title);
			try {
				qBean.getClass().getMethod("setText", String.class).invoke(qBean,body);
			}catch(ReflectiveOperationException e) {
				request.setAttribute("exit", -1);
			}
			if(image != null) {
				setImage(request,image,qBean);
				
			}else {
				qBean.setType(QuestionType.PROBLEM);
			}
			
			try {
				InsertQuestionController controller = new InsertQuestionController(factory,qBean);
				controller.startSave(subject);
			
			}
			
		
			catch(QuestionException e){
				request.setAttribute("exit", 2);
				
			}
			request.getRequestDispatcher("AllQuestion.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("exit", 3);
			request.getRequestDispatcher("questionPage.jsp").forward(request, response);
		}
	
		
	}
	
	private void setImage(HttpServletRequest request,String image,QuestionBean qBean) {
		
		qBean.setType(QuestionType.EXERCISE);
		try {
			qBean.getClass().getMethod("setImage", String.class).invoke(qBean, image);
		}
		catch(ReflectiveOperationException e) {
			request.setAttribute("exit", -1);
		}
	}

}
