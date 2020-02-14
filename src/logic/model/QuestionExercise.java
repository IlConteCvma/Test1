package logic.model;

public class QuestionExercise extends Question {

	protected String text;
	protected String image;
	
	public QuestionExercise() {

	}
	
	public QuestionExercise(String text) {
		this.text = text;
	}
	
	public QuestionExercise(String text, String image) {
		this.text = text;
		this.image = image;
	}
	
	
	public void setText(String newText) {
		this.text = newText;
	}
	public String getText() {
		return this.text;
	}
	
	public void setImage(String newImage) {
		this.image = newImage;
	}
	
	public String getImage() {
		return this.image;
	}
	
	@Override
	public QuestionType whoAmI() {
		return QuestionType.EXERCISE;
	}


}
