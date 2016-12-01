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
   * @param loc referring to the location to be changed.
   */
  public void notifyObsLoc(Geoloc loc);

  /**
   * notify observer to change car type
   * 
   * @param loc referring to the car type to be changed.
   */
  public void notifyObsType(int type);
}
