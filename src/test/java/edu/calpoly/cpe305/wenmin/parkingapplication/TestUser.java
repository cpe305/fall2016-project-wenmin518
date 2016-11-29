package test.java.edu.calpoly.cpe305.wenmin.parkingapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import main.java.edu.calpoly.cpe305.wenmin.parkingapplication.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.parkingapplication.User;

import org.junit.Test;



public class TestUser {

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

}
