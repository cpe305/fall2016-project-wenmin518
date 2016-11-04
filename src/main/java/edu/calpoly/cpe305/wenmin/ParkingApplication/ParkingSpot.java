package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


public class ParkingSpot {
  private int num;
  private int type;
  private boolean available;

  /**
   * Constructor.
   * @param position Geoloc of the parking spot
   * @param type refer to the type of parking 1: Compact, 2: Electic, 3: Handicap, 4: Regular.
   * @param available tells whether the parking spot is currently available
   */
  public ParkingSpot(int num, int type, boolean available) {
    this.num = num;
    this.type = type;
    this.available = available;
  }

  public int getNum() {
    return num;
  }

  public int getType() {
    return type;
  }

  public boolean isAvailable() {
    return available;
  }
}

