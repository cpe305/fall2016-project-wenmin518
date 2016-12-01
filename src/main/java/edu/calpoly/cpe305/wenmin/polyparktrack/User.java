package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

/**
 * User object has the position and car type.
 * 
 * @author wenmin518
 *
 */
public class User implements Subject {

  private Geoloc position;
  private int carType;
  private Calculation obs;
  private boolean hasObs;

  /**
   * Constructor
   * 
   * @param position referring to the position of User
   * @param carType referring to the car type of User has.
   */
  public User(Geoloc position, int carType) {
    this.position = position;
    this.carType = carType;
    obs = null;
    hasObs = false;
  }

  /**
   * gets the user location.
   * 
   * @return the user location.
   */
  public Geoloc getPosition() {
    return position;
  }

  /**
   * gets the user car type.
   * 
   * @return the user car type.
   */
  public int getCarType() {
    return carType;
  }

  /**
   * @param loc referring the location that subject is changing to.
   */
  @Override
  public void notifyObsLoc(Geoloc loc) {
    if (hasObs) {
      obs.updateUserLoc(loc);
    }
  }

  /**
   * tells the observer to change car type.
   * 
   * @param type referring to the car type that subject has changed to.
   */
  @Override
  public void notifyObsType(int type) {
    if (hasObs) {
      obs.updateCartype(type);
    }
  }

  /**
   * Register the calculation observer, but only register once
   * 
   * @param cal referring to the calculation observer to be added if there is no calculation.
   *        observer in the user, then add the calculation observer. if there is a calculation
   *        observer already, then do not do anything.
   */
  public void registerObs(Calculation cal) {
    if (!hasObs) {
      obs = cal;
      hasObs = true;
    }
  }

  /**
   * To unregister the calculation observer. If there is observer to be removed, then remove the
   * observer else do not do anything.
   */
  public void unregisterObs() {
    if (hasObs) {
      obs = null;
      hasObs = false;
    }
  }

  /**
   * 
   * @param loc referring to the position that user change to.
   */
  public void setPosition(Geoloc loc) {
    position = loc;
  }

  /**
   * 
   * @param type referring to the car type that user change to.
   */
  public void setcarType(int type) {
    carType = type;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User user = (User) obj;
    if ((!position.equals(user.getPosition())) && carType != user.getCarType()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7 * 3 + this.getPosition().hashCode();
    return hash;
  }


}
