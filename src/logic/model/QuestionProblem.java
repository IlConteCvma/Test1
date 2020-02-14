package logic.model;

public class QuestionProblem extends Question {
	
	private String text;
	
	public QuestionProblem() {
		
	}
	public QuestionProblem(String text) {
		this.setText(text);
	}

	
	public void setText(String newText) {
		this.text = newText;
	}
	public String getText() {
		return text;
	}
	
	@Override
	public QuestionType whoAmI() {
		return QuestionType.PROBLEM;
	}


}
