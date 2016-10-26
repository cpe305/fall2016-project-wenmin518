package test.java.edu.calpoly.cpe305.wenmin.ParkingApp;

import main.java.edu.calpoly.cpe305.wenmin.ParkingApp.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUser extends TestCase {

  @Test
  public void userTest1() {
    User user = new User(new Geoloc(10, 10), 3);
    assertTrue(user.getPosition().equals(new Geoloc(10, 10)));
    assertEquals(user.getCarType(), 3);
  }

  @Test
  public void userTest2() {
    User user = new User(new Geoloc(0, 1), 3);
    assertEquals(user.getCarType(), 3);
    assertEquals(user.getPosition().getX(), 0);
    assertEquals(user.getPosition().getY(), 1);

  }

}
