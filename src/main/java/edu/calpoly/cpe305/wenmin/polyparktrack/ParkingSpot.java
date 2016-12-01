package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

/**
 * Referring to the parkingspot object that has a number, a type, and availability attributes.
 * 
 * @author wenmin518
 *
 */
public class ParkingSpot {
  private int num;
  private int type;
  private boolean available;

  /**
   * Constructor.
   * 
   * @param num referring to the number of the spot.
   * @param type refer to the type of parking 1: Compact, 2: Electic, 3: Handicap, 4: Regular.
   * @param available tells whether the parking spot is currently available
   */
  public ParkingSpot(int num, int type, boolean available) {
    this.num = num;
    this.type = type;
    this.available = available;
  }

  /**
   * gets the number of parking spot.
   * 
   * @return an integer referring to the parking spot number.
   */
  public int getNum() {
    return num;
  }

  /**
   * gets the type of parking spot.
   * 
   * @return an integer referring to the parking spot type.
   */
  public int getType() {
    return type;
  }

  /**
   * gets the availability of parking spot
   * 
   * @return a boolean referring to the parking spot availability.
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   *
   * @param number referring to the parking spot number to set to the parking spot.
   */
  public void setNum(int number) {
    num = number;
  }

  /**
   * 
   * @param theType referring to the parking spot type to assign the the parking spot.
   */
  public void setType(int theType) {
    type = theType;
  }

  /**
   * 
   * @param avail referring to the parking spot availability to set to the parking spot.
   */
  public void setAvailability(boolean avail) {
    available = avail;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ParkingSpot)) {
      return false;
    }
    ParkingSpot ps = (ParkingSpot) obj;
    if (num != ps.getNum() || type != ps.getType() || available != ps.isAvailable()) {
      return false;
    }
    return true;
  }
  
  @Override
  public int hashCode() {
    return 3;
  }
}

