package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Geoloc;




public class TestGeoloc {

  @Test
  public void geolocTest1() {
    Geoloc loc = new Geoloc(10, 10);
    assertTrue(loc.equals(new Geoloc(10, 10)));

  }

  @Test
  public void geolocTest2() {
    Geoloc loc = new Geoloc(0, -1);
    assertEquals(loc.getX(), 0);
    assertEquals(loc.getY(), -1);
  }

  @Test
  public void geolocTest3() {
    Geoloc loc = new Geoloc(5, 5);
    assertFalse(loc == new Geoloc(3, 2));
  }
}
