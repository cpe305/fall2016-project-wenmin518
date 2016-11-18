package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

/**
 * the observer interface that has updateUserpos method to the observer which is the calculation
 * object.
 * 
 * @author wenmin518
 *
 */
public interface Observer {
  /**
   * updating calculation and info if the subject changes their location
   * @param pos referring to the position of subject.
   */
  public void updateUserPos(Geoloc pos);
}
