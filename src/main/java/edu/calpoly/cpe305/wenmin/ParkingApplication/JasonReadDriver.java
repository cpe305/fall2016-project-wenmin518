package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JasonReadDriver {
  private int[][] adj;
  private int rows;
  private int cols;
  private boolean[] visited;
  private int[] vertices;
  private ArrayList<ParkingStructure> structArr;
  private ParkingJsonObj parkingJson;
  
  public void main(String[] args) throws FileNotFoundException {
    parkingJson = new ParkingJsonObj(adj, rows, cols, visited, vertices, structArr);
    parkingJson.setadjFromFile("test.txt");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(parkingJson.getAdj(i, j)+ " ");
      }
      System.out.println();
    }
  }
  
}
