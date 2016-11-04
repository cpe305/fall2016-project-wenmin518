package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


public class Geoloc {
  private int xloc;
  private int yloc;

  public Geoloc(int xloc, int yloc) {
    this.xloc = xloc;
    this.yloc = yloc;
  }

  public int getX() {
    return xloc;
  }

  public int getY() {
    return yloc;
  }
  
  
  /**
   * @param loc loction of the user.
   * @return whether Geoloc objects are the same 
   */
  //Override the equals method instead of creating my own
  @Override
  public boolean equals(Object loc) {
    if (((Geoloc)loc).getX() == xloc && ((Geoloc)loc).getY() == yloc) {
      return true;
    }
    return false;

  }
}
