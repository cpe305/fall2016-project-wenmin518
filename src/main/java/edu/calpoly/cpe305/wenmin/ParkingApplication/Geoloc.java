package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


public class Geoloc {
  private int xloc;
  private int yloc;

  /**
   * Constructor.
   * 
   * @param xloc referring to the first attribute x in the Point object.
   * @param yloc referring to the second attribute y in the Point object.
   */
  public Geoloc(int xloc, int yloc) {
    this.xloc = xloc;
    this.yloc = yloc;
  }

  /**
   * gets the first attribute of Geoloc Object.
   * 
   * @return the integer of first attribute
   */
  public int getX() {
    return xloc;
  }

  /**
   * gets the second attribute of Geoloc Object.
   * 
   * @return the integer of second attribute
   */
  public int getY() {
    return yloc;
  }

}
