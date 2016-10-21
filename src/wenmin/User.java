package wenmin;

public class User {
	private Geoloc position;
	private int carType;
	
	public User(Geoloc position, int carType) {
		this.position = position;
		this.carType = carType;
	}
	
	public Geoloc getPosition() {
		return position;
	}
	
	public int getCarType() {
		return carType;
	}
	
}
