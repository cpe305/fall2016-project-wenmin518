package wenmin;
import java.util.*;

public class ParkingStructure{
   private ArrayList<ParkingSpot> spotArr;
   private Geoloc position;

   public ParkingStructure(ArrayList<ParkingSpot> spotArr, Geoloc position) 
   {
	   this.spotArr = spotArr;
	   this.position = position;
   }
   
   public ArrayList<ParkingSpot> getspotArr() {
	   return spotArr;
   }
   
   public int getNumavailable() {
	   int count = 0;
	   for (int i = 0; i < spotArr.size(); i++) {
		   if (spotArr.get(i).isAvailable()) {
			   count++;
		   }
	   }
	   return count;
   }
   
   public int getNumOccupied() {
	   return spotArr.size() - getNumavailable();
   }
   
   public Geoloc getParkingStructurePosition() {
	   return position;
   }
   
}
