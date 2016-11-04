package test.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.Calculation;
import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.Geoloc;
import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.ParkingStructure;
import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.User;

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
  public void calculationTest1() {
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
    cal.updateUserPos(new Geoloc(1, 0));
    start = cal.locToint(cal.user.getPosition());
    assertEquals(cal.fewestEdgePath(start, stop), 1);
  }

  @Test
  public void calculationTest2() {
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
    Calculation cal= new Calculation(adj, visited, user, vert/2, vert/2, vert, parkLoc);
    assertTrue(cal.distance(new Geoloc(0, 0), new Geoloc(3, 4)) == 5);
  }

}
