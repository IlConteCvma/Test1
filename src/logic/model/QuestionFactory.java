package logic.model;

import logic.bean.QuestionBean;


public interface QuestionFactory {
	public  Question createQuestion();
	public  QuestionBean createBean();
}
