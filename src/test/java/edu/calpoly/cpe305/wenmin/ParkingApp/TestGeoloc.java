package test.java.edu.calpoly.cpe305.wenmin.ParkingApp;
import main.java.edu.calpoly.cpe305.wenmin.ParkingApp.*;

import junit.framework.TestCase;

import org.junit.Test;

public class TestGeoloc extends TestCase{

  @Test
  public void GeolocTest1() {
      Geoloc loc = new Geoloc(10, 10);
      assertTrue(loc.equals(new Geoloc(10, 10)));
  }
  
  @Test
  public void GeolocTest2() {
      Geoloc loc = new Geoloc(0, -1);
      assertEquals(loc.getX(), 0);
      assertEquals(loc.getY(), -1);
  }
  
  @Test 
  public void GeolocTest3() {
      Geoloc loc = new Geoloc(5, 5);
      assertFalse(loc.equals(new Geoloc(4, 4)));
  }

}
