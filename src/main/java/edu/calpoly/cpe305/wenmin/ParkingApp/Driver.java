package main.java.edu.calpoly.cpe305.wenmin.ParkingApp;

import java.util.*;


public class Driver {
  public static void main(String[] args) {
    ArrayList<ParkingStructure> strList = new ArrayList<ParkingStructure>();
    Random ran = new Random();
    int type, numStr = 8, num;
    boolean available;

    for (int i = 0; i < numStr; i++) {
      ArrayList<ParkingSpot> spotlist = new ArrayList<ParkingSpot>();

      for (int j = 0; j < 100; j++) {
        type = ran.nextInt(8) + 10;
        num = ran.nextInt();
        if (num % 2 == 1) {
          available = true;
        } else {
          available = false;
        }

        ParkingSpot ps = new ParkingSpot(new Geoloc(i, j), type % 4, available);
        spotlist.add(ps);
      }
      ParkingStructure parkingStr = new ParkingStructure(spotlist, new Geoloc(i, 1));
      strList.add(parkingStr);
    }

    for (int i = 0; i < numStr; i++) {

      System.out.print(
          "Parking " + i + " has " + strList.get(i).getNumavailable() + " spots available, ");
      System.out.print("and " + strList.get(i).getNumoccupied() + " spots occupied");
      System.out.println();
    }
  }

}