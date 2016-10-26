package edu.calpoly.cpe305.wenmin.ParkingApp;

public class Geoloc {
	private int x;
	private int y;
	public Geoloc(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean equals(Geoloc loc) {
		if (loc.getX() == x && loc.getY() == y) {
			return true;
		}
		return false;
		
	}
}

