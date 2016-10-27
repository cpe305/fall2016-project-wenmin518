package test.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.ParkingSpot;

public class TestSpots {

  @Test
  public void parkingSpotTest1() {
    ParkingSpot ps = new ParkingSpot(new Geoloc(9, 9), 3, true);
    assertTrue(ps.getPosition().equals(new Geoloc(9, 9)));
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), true);
  }

  @Test
  public void parkingSpotTest2() {
    ParkingSpot ps = new ParkingSpot(new Geoloc(1, 1), 3, false);
    assertTrue(ps.getPosition().equals(new Geoloc(1, 1)));
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), false);
  }

  @Test
  public void parkingSpotTest3() {
    ParkingSpot ps = new ParkingSpot(new Geoloc(10, 10), 3, true);
    assertFalse(ps.getPosition().equals(new Geoloc(5, 10)));
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), true);
  }

}
