package logic.model;

public class Address {
	
	private String street;
	private String streetNumber;
	private String city;
	
	public Address(String street,String streetNumber,String city) {
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
	}
	
	public String getFullAddress() {
		return street+" "+streetNumber;
		
	}
	
	
	public String getStreet() {
		return this.street;
	}
	
	

	public String getStreetNumber() {
		return this.streetNumber;
	}
	public String getCity() {
		return this.city;
	}
	
}
