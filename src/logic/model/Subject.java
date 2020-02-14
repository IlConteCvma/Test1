package logic.model;

public class Subject {
	private String name;
	private String abbrevation;
	private double indexOfStudents;
	
	public Subject(String name, String abbrevation, double index) {
		this.name = name;
		this.abbrevation = abbrevation;
		this.indexOfStudents = index;
	}
	
	public Subject() {
		
	}

	public double getIndexOfStudents() {
		return this.indexOfStudents;
	}
	
	public String getAbbrevation() {
		return this.abbrevation;
	}
	
	public String getName() {
		return this.name;
	}

}
