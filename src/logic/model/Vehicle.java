package logic.model;

public class Vehicle {
	private TypeVehicle type;
	private double speed;
	
	public Vehicle(TypeVehicle type) {
		this.type = type;
		setSpeed(this.type);
	}
	
	public Vehicle(TypeVehicle type, double speed) {
		this.type = type;
		this.speed = speed;
	}
	
	public void setSpeed(TypeVehicle type) {
		switch(type) {
			case CAR:		this.speed = 40.0;
							break;
			case BUS:		this.speed = 30.0;
							break;
			case SCOOTER:	this.speed = 35.0;
							break;
			default:		break;
			
		}
	}
	
	public TypeVehicle getType() {
		return this.type;
	}
	
	public double getSpeed() {
		return speed;
	}
}
