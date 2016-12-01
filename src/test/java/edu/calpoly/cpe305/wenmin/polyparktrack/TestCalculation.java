package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Calculation;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingSpot;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingStructure;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.User;

import java.util.ArrayList;

public class TestCalculation {

  public int vert = 4;
  public int[][] adj = new int[vert][vert];
  public boolean[] visited = new boolean[vert];
  public ArrayList<ParkingStructure> parkLoc = new ArrayList<ParkingStructure>();
  public Geoloc userLoc = new Geoloc(0, 0);

  /**
   * initalize the adj and visited array.
   */
  public void initAdjVis() {
    int rowIdx;
    int colIdx;
    for (colIdx = 0; colIdx < vert; colIdx++) {
      for (rowIdx = 0; rowIdx < vert; rowIdx++) {
        adj[colIdx][rowIdx] = 0;
      }
      visited[colIdx] = false;
    }
  }

  @Test
  public void testPath1() {
    parkLoc.add(new ParkingStructure(new Geoloc(1, 1)));

    initAdjVis();
    User user = new User(userLoc, 1);
    Calculation cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    cal.addEdge(new Geoloc(0, 0), new Geoloc(1, 0));
    cal.addEdge(new Geoloc(0, 0), new Geoloc(0, 1));
    cal.addEdge(new Geoloc(1, 0), new Geoloc(1, 1));
    cal.addEdge(new Geoloc(0, 1), new Geoloc(1, 1));
    int start = cal.locToint(user.getPosition());
    int stop = cal.locToint(parkLoc.get(0).getPosition());
    assertEquals(cal.fewestEdgePath(start, stop), 2);
    cal.updateUserLoc(new Geoloc(1, 0));
    start = cal.locToint(cal.getUser().getPosition());
    assertEquals(cal.fewestEdgePath(start, stop), 1);
  }

  @Test
  public void testPath2() {
    User user = new User(userLoc, 2);
    Calculation cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    cal.addEdge(new Geoloc(0, 0), new Geoloc(1, 0));
    cal.addEdge(new Geoloc(0, 0), new Geoloc(0, 1));
    cal.addEdge(new Geoloc(1, 0), new Geoloc(1, 1));
    cal.addEdge(new Geoloc(0, 1), new Geoloc(1, 1));

    int start = cal.locToint(new Geoloc(0, 0));
    int stop = cal.locToint(new Geoloc(0, 1));
    assertEquals(cal.fewestEdgePath(start, stop), 1);
  }

  @Test
  public void testDistance() {
    User user = new User(userLoc, 1);
    Calculation cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    assertTrue(cal.distancebetween(new Geoloc(0, 0), new Geoloc(3, 4)) == 5);
    assertFalse(cal.equals(
        new Calculation(adj, visited, new User(userLoc, 2), vert, vert, 2 * vert, parkLoc)));
  }

  @Test
  public void testGetter() {
    User user = new User(userLoc, 1);
    Calculation cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    assertEquals(adj[1][1], cal.getAdj()[1][2]);
    assertEquals(adj[1][2], cal.getAdjAt(1, 2));
    int start = cal.locToint(new Geoloc(0, 0));
    int end = cal.locToint(new Geoloc(0, 1));
    double distance = (double) cal.fewestEdgePath(start, end) * 0.27;
    assertTrue(distance == cal.getCalpolyDistance(start, end));
    assertEquals(vert / 2, cal.getCol());
    assertEquals(vert / 2, cal.getRow());
    assertEquals(vert, cal.getNumVer());
    parkLoc.clear();
    ParkingStructure ps = new ParkingStructure(new Geoloc(1, 1));

    parkLoc.add(new ParkingStructure(new Geoloc(1, 1)));
    assertEquals(cal.getParkStr().get(0).getPosition(), new Geoloc(1, 1));
    assertTrue(ps.getPosition().equals(cal.getStrAt(0).getPosition()));
    assertEquals(cal.getUser(), user);
    assertEquals(cal.getVisited()[1], cal.getVisitedAt(1));
    assertEquals(cal.nearbyParkingStr(user.getPosition(), parkLoc), -1);

  }

  @Test
  public void testprint() {

    User user = new User(userLoc, 1);
    Calculation cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    assertEquals(cal.distanceString(2.70001), "The parking spot is about " + 2.7 + " miles away\n");
    assertEquals(cal.printUserCarType(), "Compact");
    assertEquals(cal.printParkingNum(-1), "The nearest ParkingStructure is P0\n");
    parkLoc.add(new ParkingStructure(userLoc));
    assertEquals(cal.printStrNumwithCarType("Compact", 0),
        "Compact car that you are looking for is not available in parking 0\n");
    assertEquals(cal.timeString(1.009), "It is gonna take about 4.0 minutes");
    cal.updateCartype(2);
    assertEquals(cal.getUser().getCarType(), 2);
    assertEquals(cal.printUserCarType(), "Electric");
    cal.updateCartype(3);
    assertEquals(cal.printUserCarType(), "Handicap");
    cal.updateCartype(4);
    assertEquals(cal.printUserCarType(), "Normal");
    cal.updateUserLoc(new Geoloc(2, 0));
    assertTrue(cal.getUser().getPosition().equals(new Geoloc(2, 0)));
    assertEquals(cal.printInfo(userLoc), "Sorry, every parking spot is taken, try tomorrow, ---\n");
    parkLoc.clear();
    parkLoc.add(new ParkingStructure(new Geoloc(0, 0)));

    for (int i = 0; i < 4; i++) {
      ParkingSpot spot = new ParkingSpot(i, i + 1, true);
      parkLoc.get(0).addtoSpotArr(spot);
    }
    cal = new Calculation(adj, visited, user, vert / 2, vert / 2, vert, parkLoc);
    assertEquals(cal.printStrNumwithCarType("Electric", 0),
        "Parking Spot #4 is Electric Parking Spot you are looking for\n");
    assertEquals(cal.printInfo(userLoc),
        "The nearest ParkingStructure is P1\nParking Spot #4 is Normal Parking Spot you are looking for\nThe parking spot is about 0.0 miles away\nIt is gonna take about 0.0 minutes");
  }
  
  @Test
  public void testequalsMethod() {
    User user = new User(userLoc, 1);
    Calculation cal = new Calculation(adj, visited, user, vert , vert , vert * 2, parkLoc);
    assertFalse(cal.equals(new Geoloc(1, 1)));
    assertFalse(cal.equals(null));
    Calculation newCal = cal;
    newCal.getAdj()[0][0] = 5;
    assertFalse(cal.equals(newCal));
    
    
  }

}
