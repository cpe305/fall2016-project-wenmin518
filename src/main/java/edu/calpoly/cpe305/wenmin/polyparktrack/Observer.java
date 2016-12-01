package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

/**
 * Observer which observes the subject behavior
 * @author wenmin518
 *
 */
public interface Observer {
  /**
   * updates subject location.
   * @param loc referring to the new user location.
   */
  public void updateUserLoc(Geoloc loc);

  /**
   * updates subject car type.
   * @param loc referring to the new user car type.
   */
  public void updateCartype(int type);
}
