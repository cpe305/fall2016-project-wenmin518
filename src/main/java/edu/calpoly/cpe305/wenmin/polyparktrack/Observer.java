package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

public interface Observer {
  public void updateUserLoc(Geoloc loc);

  public void updateCartype(int type);
}