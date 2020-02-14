package logic.model;

import java.util.ArrayList;
import java.util.List;
public class Room {
	
	private String name;
	private int numRow;
	private int numColumn;
	private List<Seat> places;
	
	public Room(String name, int numRow, int numColumn) {
		this.name = name;
		this.numRow = numRow;
		this.numColumn = numColumn;
	}
	
	public Room(String name, int numRow, int numColumn, List<Seat>places) {
		this.name = name;
		this.numRow = numRow;
		this.numColumn = numColumn;
		this.places = places;
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public int getNumColumn() {
		return numColumn;
	}
	
	public void setPlaces(List<Seat> places){
		this.places = places;
	}
	
	public List<Seat> getPlaces(){
		return this.places;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumberOfPlaces() {
		return numRow * numColumn;
	}
	
	public List<Integer> getSeatOfPriority(int priority) {
		//priority = 0(best), 1(good), 2(bad) 
		List<Integer> range = new ArrayList<>(2);
		int startRange = (int) (priority * 0.334  * getNumberOfPlaces());
		int endRange = (int) ((priority+1) * 0.334  * getNumberOfPlaces());
		range.add(startRange);
		range.add(endRange);
		return range;
	}
	
	public int getNumberOfFreePlacesForPriority(int priority) {
		List<Integer> range = getSeatOfPriority(priority);
		int numberOfFreePlaces = 0;
		for(int i=range.get(0);i<range.get(1);i++) {
			if(!getPlaces().get(i).getState()) {
				numberOfFreePlaces++;
			}
		}
		return numberOfFreePlaces;
	}
}
