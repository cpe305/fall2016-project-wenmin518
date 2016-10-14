package wenmin;
import java.util.*;

public class ParkingStructure{
   private ParkingSpot[] parkingSpotArr;
   private int numofavailableParkingSpots;
   private int numofoccupiedParkingSpots;
   private GeoLoc position;

   public ParkingStruture(ParkingSpot parkingSpotArr, 
	int numofavailableParkingSpots, int numofoccupiedParkingSpots,
	Point position) {
	   this.parkingSpotArr = parkingSpotArr;
	   this.nuumofavailableParkingSpots = numofavailableParkingSpots;
	   this.numofoccupiedParkingSpots = numofoccupirdParkingSpots;
	   this.position = position;
   }
   
   public int getNumavailable() {
	   reutrn numofavailableParkingSpots;
   }
   
   public int getNumOccupied() {
	  return numofoccupiedParkingSpots; 
   }
   
   public Point getParkingStructurePosition() {
	   return position;
   }
}
