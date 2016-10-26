package wenmin;

public class Geoloc {
  private int xloc;
  private int yloc;

  public Geoloc(int xloc, int yloc) {
    this.xloc = xloc;
    this.yloc = yloc;
  }

  public int getxloc() {
    return xloc;
  }

  public int getyloc() {
    return yloc;
  }

  /**
   * @param loc refer to the location that contains x and y.
   * @return whether one loc 1 is the same as loc 2.
   */
  public boolean equals(Geoloc loc) {
    if (loc.getxloc() == xloc && loc.getyloc() == yloc) {
      return true;
    }
    return false;

  }
}

