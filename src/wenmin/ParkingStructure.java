package wenmin;

public class ParkingStructure{
   private ParkingSpot[] spotArr;
   private int numavailable;
   private int numoccupied;
   private Geoloc position;

   public ParkingStructure(ParkingSpot[] spotArr, int numavailable, int numoccupied, Geoloc position) 
   {
	   this.spotArr = spotArr;
	   this.numavailable = numavailable;
	   this.numoccupied = numoccupied;
	   this.position = position;
   }
   
   public ParkingSpot[] getspotArr() {
	   return spotArr;
   }
   
   public int getNumavailable() {
	   return numavailable;
   }
   
   public int getNumOccupied() {
	  return numoccupied; 
   }
   
   public Geoloc getParkingStructurePosition() {
	   return position;
   }
}
