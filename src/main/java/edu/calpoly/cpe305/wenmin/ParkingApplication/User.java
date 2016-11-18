package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

/**
 * User object has the position and car type.
 * 
 * @author wenmin518
 *
 */
public class User implements Subject {

  private Geoloc position;
  private int carType;
  private Observer obs;

  /**
   * Constructor
   * 
   * @param position referring to the position of User
   * @param carType referring to the car type of User has.
   */
  public User(Geoloc position, int carType) {
    this.position = position;
    this.carType = carType;
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
   * @param referring the location that subject is changing to.
   */
  public void notifyObs(Geoloc loc) {
    obs.updateUserPos(loc);
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
}
