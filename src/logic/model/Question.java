package logic.model;



public abstract class Question {
	protected int id;
	protected boolean solved; 
	protected String title;
	protected Subject questionSubuject;
	protected Student student;
	
	public Question() {

	}
	
	public void setId(int newId) {
		this.id = newId;
	}
	public int getId() {
		return this.id;
	}
	

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	
	public Subject getQuestionSub() {
		return this.questionSubuject;
	}

	public void setQuestionSub(Subject questionSub) {
		this.questionSubuject = questionSub;
	}

	
	public Student getStudent() {
		return this.student;
	}

	
	public void setStudent(Student student) {
		this.student = student;
	}

	
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	public boolean isSolved() {
		return this.solved;
	}
	
	public abstract QuestionType whoAmI(); 
	
}
	
