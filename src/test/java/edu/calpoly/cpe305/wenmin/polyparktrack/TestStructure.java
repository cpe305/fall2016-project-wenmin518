package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingSpot;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingStructure;

public class TestStructure {

  @Test
  public void testGetter() {
    ParkingSpot ps1 = new ParkingSpot(10, 3, true);
    ParkingSpot ps2 = new ParkingSpot(5, 1, false);
    ParkingStructure ps = new ParkingStructure(new Geoloc(4, 4));
    ps.addtoSpotArr(ps1);
    ps.addtoSpotArr(ps2);
    assertEquals(ps.getPosition().getX(), 4);
    assertEquals(ps.getPosition().getY(), 4);
    assertEquals(ps.getNumavailable(), 1);
    assertEquals(ps.getNumoccupied(), 1);
    assertEquals(ps.getspotArr().get(1).getType(), 1);
    assertEquals(ps.getspotArr().get(1).isAvailable(), false);
    assertEquals(ps.getSmallestSpotNum(), 0);
    assertEquals(ps.getSmallestTypeNum(3), 0);
  }

  @Test
  public void moreGetterTests() {
    ParkingSpot ps1 = new ParkingSpot(10, 2, false);
    ParkingStructure ps = new ParkingStructure(new Geoloc(3, 3));
    ps.addtoSpotArr(ps1);
    assertEquals(ps.getNumavailable(), 0);
    assertEquals(ps.getNumoccupied(), 1);
    assertEquals(ps.getspotArr().get(0).getType(), 2);
    assertEquals(ps.getspotArr().get(0).isAvailable(), false);
  }
  
  @Test
  public void setterandOthers() {
    ParkingSpot spot = new ParkingSpot(10, 2, true);
    ParkingStructure structure = new ParkingStructure(new Geoloc(3, 3));
    structure.addtoSpotArr(spot);
    assertFalse(structure.parkingStrIsFull());
    structure.parktoTheNearest();
    assertTrue(structure.parkingStrIsFull());
    structure.setPosition(new Geoloc(3, 2));
    assertEquals(structure.getSmallestSpotNum(), -1);
    structure.setSpotAt(0, new ParkingSpot(1, 2, false));
    structure.setPosition(new Geoloc(22, 2));
    assertTrue(structure.getPosition().equals(new Geoloc(22, 2)));
  }
  
  @Test
  public void testequals() {
    ParkingSpot spot = new ParkingSpot(10, 2, true);
    ParkingSpot spot2 = new ParkingSpot(20, 2, true);
    ParkingSpot spot3 = new ParkingSpot(10, 4, true);
    ParkingSpot spot4 = new ParkingSpot(10, 2, false);
    ParkingStructure structure = new ParkingStructure(new Geoloc(1, 1));
    structure.addtoSpotArr(spot);
    structure.addtoSpotArr(spot2);
    structure.addtoSpotArr(spot3);
    structure.addtoSpotArr(spot4);
    assertEquals(structure.getSmallestTypeNum(4), 2);
    assertEquals(structure.getSmallestSpotNum(), 0);
    structure.setSpotAt(2, spot4);
    assertFalse(structure.equals(spot4));
  }
  
  @Test
  public void testmoreEquals() {
    ParkingStructure ps = new ParkingStructure(new Geoloc(1, 1));
    ParkingStructure ps2 = new ParkingStructure(new Geoloc(2, 1));
    assertFalse(ps.equals(ps2));
    ParkingSpot spot1 = new ParkingSpot(10, 2, true);
    ParkingSpot spot2 = new ParkingSpot(5, 3, false);
    ps.addtoSpotArr(spot1);
    ps.addtoSpotArr(spot2);
    ps2.addtoSpotArr(spot1);
    assertFalse(ps.equals(ps2));
    ps2.addtoSpotArr(spot1);
    assertFalse(ps.equals(ps2));
//    System.out.println(ps.hashCode());
    assertEquals(ps.hashCode(), 1078);
    assertFalse(ps.equals(null));
    assertTrue(ps.equals(ps));
    
  }
}
