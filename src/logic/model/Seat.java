package logic.model;

public class Seat {
	private boolean busy;
	private int index;
	
	public Seat(int index, boolean busy) {
		this.index = index;
		this.busy = busy;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void occupateSeat() {
		busy = true;
	}
	
	public void freeSeat() {
		busy = false;
	}
	
	public boolean getState() {
		return busy;
	}
}
