package main.java.edu.calpoly.cpe305.wenmin.ParkingApp;

public class ParkingSpot {
	private Geoloc position;
	private int type;
	private boolean available;
	
	public ParkingSpot(Geoloc position, int type, boolean available) {
		this.position = position;
		this.type = type;
		this.available = available;
	}
	
	public Geoloc getPosition() {
		return position;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean isAvailable() {
		return available;
	}
}
