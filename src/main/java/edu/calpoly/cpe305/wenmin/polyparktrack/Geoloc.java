package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;


/**
 * Own location class that contains xloc and yloc field
 * 
 * @author wenmin518
 *
 */
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

  /**
   * setter for xloc.
   * 
   * @param newX referring to the new xloc to change to
   */
  public void setX(int newX) {
    xloc = newX;
  }

  /**
   * setter for yloc.
   * 
   * @param newY referring to the new yloc to change to
   */
  public void setY(int newY) {
    yloc = newY;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!(obj instanceof Geoloc)) {
      return false;
    }

    Geoloc other = (Geoloc) obj;
    if (this.xloc != other.getX() || this.yloc != other.getY()) {
      return false;
    }

    return true;
  }

}
