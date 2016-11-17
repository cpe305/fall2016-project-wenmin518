package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.util.ArrayList;


public class ParkingStructure {
  private ArrayList<ParkingSpot> spotArr;
  private Geoloc pos;

  /**
   * Constructor.
   * 
   * @param pos referring to the location of parking structure.
   */
  public ParkingStructure(Geoloc pos) {
    spotArr = new ArrayList<ParkingSpot>();
    this.pos = pos;
  }

  /**
   * gets the parkign spot arraylist of parking Strucutre.
   * 
   * @return memory address that contains parking spot array.
   */
  public ArrayList<ParkingSpot> getspotArr() {
    return spotArr;
  }

  /**
   * 
   * @param spot referring to the spot to the spot array.
   */
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

  /**
   *
   * @return integer indicates number of parking spots occupied.
   */
  public int getNumoccupied() {
    return spotArr.size() - getNumavailable();
  }

  /**
   * gets the position of parking structure.
   * @return the position of parking structure.
   */
  public Geoloc getPosition() {
    return pos;
  }

  /**
   * 
   * @param newLoc referring to the location of parking location to be changed to.
   */
  public void setPosition(Geoloc newLoc) {
    pos = newLoc;
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

  /**
   * finds the nearest parking spot with specified type.
   * 
   * @param type referring to the type of the car
   * @return the smallest number of parking spot that is available with specified type
   */
  public int getSmallestTypeNum(int type) {
    if (getNumavailable() >= 1) {
      for (int i = 0; i < spotArr.size(); i++) {
        if (spotArr.get(i).isAvailable()) {
          if (spotArr.get(i).getType() == type) {
            return i;
          }
        }
      }
    }
    return -1;
  }
}

