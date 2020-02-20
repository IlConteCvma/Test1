package logic.bean;

public class QuestionExerciseBean extends QuestionBean {
	
	private String text;
	private String image;

	public void setText(String text) {
		this.text=text;
	}
	public String getText() {
		return this.text;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
