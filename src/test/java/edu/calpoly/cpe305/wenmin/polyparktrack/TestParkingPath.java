package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingPath;

import java.io.FileNotFoundException;



public class TestParkingPath {

  ParkingPath pp = new ParkingPath();

  @Test
  public void testRead() throws FileNotFoundException {
    pp.setadjFromFile("/Users/wenmin518/git/fall2016-project-wenmin518/textfiles/pathTest.txt");
    assertEquals(pp.getNumVer(), 4);
  }

  @Test
  public void testArr() throws FileNotFoundException {
    pp.setadjFromFile("/Users/wenmin518/git/fall2016-project-wenmin518/textfiles/pathTest.txt");
    assertEquals(pp.getAdjAt(0, 0), 0);
    assertEquals(pp.getAdj()[1][0], 1);
    assertEquals(pp.getAdj()[1][1], 0);
  }

  @Test
  public void testSetmethods() throws FileNotFoundException {
    pp.setadjFromFile("textfiles/pathTest.txt");
    pp.setAdjAt(1, 0, 10);
    assertEquals(pp.getAdj().length, 4);
    pp.addEdge(0, 1);
    pp.addEdge(0, 2);
    pp.addEdge(1, 3);
    pp.addEdge(2, 3);
    assertEquals(pp.getAdj()[1][0], 1);
    int[] ver = new int[4];
    for (int i = 0; i < 4; i++) {
      ver[i] = 0;
    }

    pp.setAdj(ver, 2);
    assertEquals(pp.getAdj()[1][0], 1);
  }

  @Test
  public void testGetters() throws FileNotFoundException {
    pp.setadjFromFile("textfiles/pathTest.txt");
    assertEquals(pp.getRows(), 2);
    assertEquals(pp.getCols(), 2);
    assertEquals(pp.getNumVer(), 4);
    assertEquals(pp.getVisited().length, 4);
    assertEquals(pp.getVertices().length, 4);
  }


}
