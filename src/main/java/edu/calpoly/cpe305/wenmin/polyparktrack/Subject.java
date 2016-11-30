package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

/**
 * Subject that notifies the observer that subject has change its location.
 * 
 * @author wenmin518
 *
 */
public interface Subject {
  /**
   * notify observer to change location
   * 
   * @param loc referrin to the location to be changed.
   */
  public void notifyObsLoc(Geoloc loc);

  public void notifyObsType(int type);
}
