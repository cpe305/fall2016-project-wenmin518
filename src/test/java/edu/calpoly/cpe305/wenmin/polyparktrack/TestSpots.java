package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingSpot;


public class TestSpots {

  @Test
  public void parkingSpotTest1() {
    ParkingSpot ps = new ParkingSpot(9, 3, true);
    assertTrue(ps.getNum() == 9);
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), true);
  }

  @Test
  public void parkingSpotTest2() {
    ParkingSpot ps = new ParkingSpot(3, 3, false);
    assertTrue(ps.getNum() == 3);
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), false);
  }

  @Test
  public void parkingSpotTest3() {
    ParkingSpot ps = new ParkingSpot(20, 3, true);
    assertFalse(ps.getNum() == 10);
    assertEquals(ps.getType(), 3);
    assertEquals(ps.isAvailable(), true);
  }
  
  @Test
  public void testSetters() {
    ParkingSpot ps = new ParkingSpot(20, 3, true);
    ps.setAvailability(false);
    ps.setNum(10);
    ps.setType(2);
    assertEquals(ps.isAvailable(), false);
    assertEquals(ps.getNum(), 10);
    assertEquals(ps.getType(), 2);
  }
  
  @Test
  public void testEquals() {
    ParkingSpot ps = new ParkingSpot(20, 3, true);
    assertFalse(ps.equals(null));
    assertFalse(ps.equals(new Geoloc(1, 1)));
    ParkingSpot newPs = new ParkingSpot(5, 2, false);
    assertFalse(ps.equals(newPs));
    newPs.setAvailability(true);
    newPs.setNum(20);
    newPs.setType(3);
    assertTrue(ps.equals(newPs));
  }

}
