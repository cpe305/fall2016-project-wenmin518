package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.util.ArrayList;


public class ParkingStructure {
  private ArrayList<ParkingSpot> spotArr;
  private Geoloc entPos;

  public ParkingStructure(ArrayList<ParkingSpot> spotArr, Geoloc entPos) {
    this.spotArr = spotArr;
    this.entPos = entPos;
  }

  public ArrayList<ParkingSpot> getspotArr() {
    return spotArr;
  }

  /**
   * @return number of parking spots available in that parking structure.
   */
  public int getNumavailable() {
    int count = 0;
    for (int i = 0; i < spotArr.size(); i++) {
      if (spotArr.get(i).isAvailable()) {
        count++;
      }
    }
    return count;
  }

  public int getNumoccupied() {
    return spotArr.size() - getNumavailable();
  }

  public Geoloc getPosition() {
    return entPos;
  }

}

