package logic.bean;

public class SubjectBean {
	private String name;
	private double indexOfStudents;
	private String abbrevation;
	
	public SubjectBean() {
		
	}
	public SubjectBean(String name) {
		this.name=name;
	}



	public double getIndexOfStudents() {
		return this.indexOfStudents;
	}

	public void setIndexOfStudents(double indexOfStudents) {
		this.indexOfStudents = indexOfStudents;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrevation() {
		return this.abbrevation;
	}
	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}
}
