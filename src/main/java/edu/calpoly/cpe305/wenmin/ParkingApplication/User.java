package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


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
   * changes the location of observer.
   */
  public void notifyObs(Geoloc loc) {
    // TODO Auto-generated method stub
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
}
