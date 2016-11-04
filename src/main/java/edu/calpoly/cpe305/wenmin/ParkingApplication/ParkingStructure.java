package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.util.ArrayList;


public class ParkingStructure {
  private ArrayList<ParkingSpot> spotArr;
  private Geoloc pos;

  public ParkingStructure(Geoloc pos) {
    spotArr = new ArrayList<ParkingSpot>();
    this.pos = pos;
  }

  public ArrayList<ParkingSpot> getspotArr() {
    return spotArr;
  }

  public void addtoSpotArr(ParkingSpot spot) {
    spotArr.add(spot);
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
    return pos;
  }

  /**
   * returns the parking spot that is closest to the parking entrance.
   * 
   * @return the parkingSpot that is available
   */
  public int getSmallestSpotNum() {
    if (getNumavailable() >= 1) {
      for (int i = 0; i < spotArr.size(); i++) {
        if (spotArr.get(i).isAvailable()) {
          return i;
        }
      }
    }
    return -1;
  }
  
  public int getSmallestTypeNum(int type) {
    if (getNumavailable() >= 1) {
      for (int i = 0; i < spotArr.size(); i++) {
        if (spotArr.get(i).isAvailable() ) {
          if (spotArr.get(i).getType() == type) {
            return i;
          }
        }
      }
    }
    return -1;
  }

}

