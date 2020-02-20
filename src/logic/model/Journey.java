package logic.model;

import java.util.ArrayList;
import java.util.List;

public class Journey {

	private List<Double> originAddress;
	private List<Double> destinationAddress;
	private Double distance;
	private int lateForWeather;
	
	public Journey(List<Double> originAddress) {
		this.setOriginAddress(originAddress);
		//Set destination address with data of University Of Tor Vergata
		this.destinationAddress = new ArrayList<>(2);
		this.destinationAddress.add(41.8546187);
		this.destinationAddress.add(12.6208633);
		lateForWeather = 0;
	}

	public List<Double> getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(List<Double> originAddress2) {
		this.originAddress = originAddress2;
	}
	
	public List<Double> getDestinationAddress() {
		return destinationAddress;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getLateForWeather() {
		return lateForWeather;
	}

	public void setLateForWeather(int lateForWeather) {
		this.lateForWeather = lateForWeather;
	}
	
}
