package logic.model;

public class Room {
	
	private String name;
	private int capacity;
	private int numRow;
	private int numColumn;
	private Seat[][] griglia;
	
	private Room(String name, int capacity, int numRow, int numColumn) {
		this.name = name;
		this.capacity = capacity;
		this.numRow = numRow;
		this.numColumn = numColumn;
		griglia = new Seat[numRow][numColumn];
	}
}
