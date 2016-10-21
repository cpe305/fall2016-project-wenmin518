package wenmin;
import java.util.*;

public class FindClosestSpot implements Observer {
	private Geoloc userloc;
	private ArrayList<ParkingStructure> strArr;
	
	public FindClosestSpot(Geoloc userloc, ArrayList<ParkingStructure> strArr) {
		this.userloc = userloc;
		this.strArr = strArr;
	}
	@Override
	public void updateUserloc(Geoloc loc) {
		userloc = loc;
	}
	

}
