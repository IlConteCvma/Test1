package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import execption.QuestionException;
import logic.Session;
import logic.bean.QuestionBean;
import logic.bean.SubjectBean;
import logic.model.Question;
import logic.model.QuestionFactory;
import logic.model.Subject;
import logic.model.dao.QuestionDao;
import logic.model.dao.SubjectDao;


public class InsertQuestionController {
	protected QuestionBean dataBean;
	protected Question question;
	protected QuestionFactory factory;
	
	 
	
	
	public InsertQuestionController(){
		
	}
	
	public InsertQuestionController(QuestionFactory factory,QuestionBean dataBean ) {
		this.dataBean = dataBean;
		this.factory = factory;
		
		
	}
	
	public  void startSave(String subject) throws QuestionException{
		
		this.question = this.factory.createQuestion();
		
		
		this.question.setSolved(false);
		
		try {
			this.question.setId(QuestionDao.getNewId());

		}
		
		catch (SQLException e) {
			throw new QuestionException("SQL problem",e.getStackTrace());
		}
		
		try {
						
			this.question.setQuestionSub(SubjectDao.getSubjectByName(subject));
			
		} catch (SQLException e1) {
			throw new QuestionException("SQL problem");
		}
		
		this.question.setTitle(this.dataBean.getTitle());
		this.question.setStudent(Session.getSession().getStudent());
		
		switch(this.dataBean.getType()) {
		
			case EXERCISE:
				try {
					saveText();
					
				}
				catch(ReflectiveOperationException e) {
					throw new QuestionException("Error in reflection for text");
				}
				try {
					saveImage();
					
				}
				catch(ReflectiveOperationException e) {
					throw new QuestionException("Error in reflection for image");
				}
				
				try {
					saveQuestion();
				}
				catch(QuestionException e ) {
					throw new QuestionException(e.getCause());
				}
				break;
				
			case PROBLEM:
				try {
					saveText();
				}
				catch(ReflectiveOperationException e) {
					
					throw new QuestionException("Error in reflection for text");
				}
				try {
					saveQuestion();
				}
				catch(QuestionException e ) {
					throw new QuestionException(e.getCause());
				}
				break;
				
			default:
				throw new QuestionException("Error in question type");
				
			
		}
		
		
		
		
		}
	
	
	public List<SubjectBean> getSubjects() {
		List<SubjectBean> sBean = new ArrayList<>() ;
		
		
		try {
			List<Subject> subj = SubjectDao.getSubjectOfStudent(Session.getSession().getStudent().getUsername());
			if(subj == null) {
				sBean = null;
			}
			else {
				for(int i=0; i<subj.size();i++) {
					SubjectBean appBean = new SubjectBean();
					appBean.setName(subj.get(i).getName());
					appBean.setIndexOfStudents(subj.get(i).getIndexOfStudents());
					appBean.setAbbrevation(subj.get(i).getAbbrevation());
					sBean.add(appBean);
				}
			}
			
		}
		catch(SQLException e) {
			sBean=null;
		}
		
		
		return sBean;
	}
	
	
	private void saveText() throws ReflectiveOperationException {
		//public for test
		Object returned = this.dataBean.getClass().getMethod("getText").invoke(this.dataBean);
		this.question.getClass().getMethod("setText", String.class).invoke(this.question, (String) returned);
		
	}
	
	private void saveImage()throws ReflectiveOperationException {
		
		Object returned = this.dataBean.getClass().getMethod("getImage").invoke(this.dataBean);
		this.question.getClass().getMethod("setImage", String.class).invoke(this.question, (String)returned);
	}
	
	private void saveQuestion() throws QuestionException{
		
		QuestionDao.saveOnDB(this.question, this.dataBean.getType());		
		
	}
	
	public Question getQuestion() {
		//function for test
		return this.question;
	}
	
	public void saveTextReflection(String text) throws ReflectiveOperationException{
		//public for test
		this.question = this.factory.createQuestion();
		this.question.getClass().getMethod("setText", String.class).invoke(this.question, text);
	}
	

	
	
}
