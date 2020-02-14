package logic.model;

import logic.bean.QuestionBean;
import logic.bean.QuestionProblemBean;


public class QuestionProblemFactory implements QuestionFactory {



	@Override
	public Question createQuestion() {
		return new QuestionProblem();
	}

	@Override
	public QuestionBean createBean() {
		return new QuestionProblemBean();
	}

}
