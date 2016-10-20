package wenmin;

public class ParkingSpot {
	private Geoloc position;
	private int type;
	private int available;
	
	public ParkingSpot(Geoloc position, int type, int available) {
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
		if (available == 0)
			return false;
		return true;
	}
}
