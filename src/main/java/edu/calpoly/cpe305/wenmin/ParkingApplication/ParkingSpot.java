package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


public class ParkingSpot {
  private Geoloc position;
  private int type;
  private boolean available;

  /**
   * Constructor.
   * @param position Geoloc of the parking spot
   * @param type refer to the type of parking 1: Compact, 2: Electic, 3: Handicap, 4: Regular.
   * @param available tells whether the parking spot is currently available
   */
  public ParkingSpot(Geoloc position, int type, boolean available) {
    this.position = position;
    this.type = type;
    this.available = available;
  }

  public Geoloc getPosition() {
    return position;
  }

  public int getType() {
    return type;
  }

  public boolean isAvailable() {
    return available;
  }
}

