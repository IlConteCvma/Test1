package logic.model;

public class Student {
	
	private String name;
	private String surname;
	private String username;
	private String password;
	private Address myAddress;
	private Vehicle vehicle;
	
	public Student(String name, String surname, String username, String password,Address myAddress,Vehicle vehicle) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.myAddress = myAddress;
		this.vehicle = vehicle;	
	}
	
	public Address getAddress() {
		return myAddress;
	}
	

	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	

}