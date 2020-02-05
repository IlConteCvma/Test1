package logic.model;

public class Student {
	
	private String name;
	private String surname;
	private String username;
	private String password;
	
	public Student(String name, String surname, String username, String password) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
}