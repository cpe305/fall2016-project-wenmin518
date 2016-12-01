package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import java.util.ArrayList;


/**
 * Parking structure object contains the location and parking spots.
 * 
 * @author wenmin518
 *
 */
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
   * Setter method for spot array.
   * 
   * @param index referring to the index of the parking spot
   * @param spot referring to the spot object to change to
   */
  public void setSpotAt(int index, ParkingSpot spot) {
    if (index < spotArr.size() - 1) {
      spotArr.set(index, spot);
    }
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
    int count = 0;
    for (int i = 0; i < spotArr.size(); i++) {
      if (!(spotArr.get(i).isAvailable())) {
        count++;
      }
    }
    return count;
  }

  /**
   * gets the position of parking structure.
   * 
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
        if (spotArr.get(i).isAvailable() && (spotArr.get(i).getType() == type)) {
          return i;
        }
      }
    }
    return -1;

  }

  @Override
  public boolean equals(Object obj) {
    // Supply details as in the assignment description

    if (obj == null) {
      return false;
    }
    if (!(obj instanceof LinkedList)) {
      return false;
    }

    ParkingStructure ps = (ParkingStructure) obj;
    if (!(pos.equals(ps.getPosition()))) {
      return false;
    }
    if (spotArr.size() != ps.getspotArr().size()) {
      return false;
    }
    for (int idx = 0; idx < spotArr.size(); idx++) {
      if (spotArr.get(idx).equals(ps.getspotArr().get(idx))) {
        return false;
      }
    }
    return true;
  }

}

