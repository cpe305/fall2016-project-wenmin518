package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Calculation;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingStructure;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.User;

import org.junit.Test;

import java.util.ArrayList;



public class TestUser {

  public int vert = 4;
  public int[][] adj = new int[vert][vert];
  public boolean[] visited = new boolean[vert];
  public ArrayList<ParkingStructure> parkLoc = new ArrayList<ParkingStructure>();

  @Test
  public void userTest1() {
    User user = new User(new Geoloc(10, 10), 3);
    assertTrue(user.getPosition().getX() == 10);
    assertTrue(user.getPosition().getY() == 10);
    assertEquals(user.getCarType(), 3);
    user.setcarType(1);
    assertEquals(user.getCarType(), 1);
  }

  @Test
  public void userTest2() {
    User user = new User(new Geoloc(0, 1), 3);
    assertEquals(user.getCarType(), 3);
    assertEquals(user.getPosition().getX(), 0);
    assertEquals(user.getPosition().getY(), 1);
  }

  @Test
  public void testUserEquals() {
    User user = new User(new Geoloc(0, 1), 3);
    Calculation cal = new Calculation(adj, visited, user, vert, vert, vert * 2, parkLoc);
    user.registerObs(cal);
    user.notifyObsType(1);
    user.notifyObsLoc(new Geoloc(1, 1));
    user.unregisterObs();
    user = new User(new Geoloc(0, 1), 3);
    assertFalse(user.equals(new User(new Geoloc(1, 1), 2)));
    assertFalse(user.equals(null));
    assertFalse(user.equals(new Geoloc(1, 1)));
    assertEquals(user.hashCode(), 26);
  }



}
