package logic.model;

public class Seat {
	private boolean busy;
	private int row;
	private int column;
	
	private Seat(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void occupateSeat() {
		busy = true;
	}
	
	public void freeSeat() {
		busy = false;
	}
}
