package wenmin;

public class ParkingSpot {
	private Geoloc position;
	private int type;
	
	public ParkingSpot(Geoloc position, int type) {
		this.position = position;
		this.type = type;
	}
	
	public Geoloc getPosition() {
		return position;
	}
	
	public int getType() {
		return type;
	}
}
