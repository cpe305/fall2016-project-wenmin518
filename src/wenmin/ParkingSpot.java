package wenmin;

public class ParkingSpot {
  private Geoloc position;
  private int type;
  private boolean available;

  /**
   * @param position referring to the actual position of the parkingspot.
   * @param type 1: compact; 2: Regular; 3: Electric; 4: Handicap
   * @param available referring to the availability of the spot
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
