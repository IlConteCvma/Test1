package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.Session;
import logic.bean.QuestionBean;
import logic.bean.QuestionExerciseBean;
import logic.bean.QuestionProblemBean;
import logic.model.Question;
import logic.model.QuestionType;
import logic.model.dao.QuestionDao;



public class AllQuestionController {
	
	
	public AllQuestionController() {
		// Do nothing because of not using 
	}
	
	public List<QuestionBean> getQuestions() {
		List<QuestionBean> bean = new ArrayList<>() ;
		
		try {
			List<Question> quest = QuestionDao.getQuestionsOfStudent(Session.getSession().getStudent().getUsername());
			if(quest == null) {
				bean = null;
			}
			else {
				for(int i=0; i<quest.size();i++) {
					
					Question appQuest = quest.get(i);
					QuestionBean appBean;
					if(appQuest.whoAmI() == QuestionType.EXERCISE) {
						appBean = new QuestionExerciseBean();
						
						Object returned = appQuest.getClass().getMethod("getText").invoke(appQuest);
						appBean.getClass().getMethod("setText", String.class).invoke(appBean,(String)returned);
						
						Object returned2 = appQuest.getClass().getMethod("getImage").invoke(appQuest);
						appBean.getClass().getMethod("setImage", String.class).invoke(appBean,(String)returned2);
					}
					else {
						appBean = new QuestionProblemBean();
						Object returned = appQuest.getClass().getMethod("getText").invoke(appQuest);
						appBean.getClass().getMethod("setText", String.class).invoke(appBean,(String)returned);
						
					}
					
					appBean.setId(appQuest.getId());
					appBean.setSolved(appQuest.isSolved());
					appBean.setTitle(appQuest.getTitle());
					appBean.setStudent(appQuest.getStudent().getName());
					appBean.setSubject(appQuest.getQuestionSub().getAbbrevation());
					appBean.setType(appQuest.whoAmI());

					bean.add(appBean);
				}
			}
		
		}
		catch(SQLException | ReflectiveOperationException ex) {
			bean = null;
		}
	
		
		
		
		return bean;
		
	}
	
	
	
}
