package logic.bean;

import logic.model.TypeVehicle;

public class UserBean {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String address;
	private String numberOfStreet;
	private String city;
	private TypeVehicle vehicle;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNumberOfStreet() {
		return numberOfStreet;
	}
	
	public void setNumberOfStreet(String numberOfStreet) {
		this.numberOfStreet = numberOfStreet;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public TypeVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(TypeVehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
