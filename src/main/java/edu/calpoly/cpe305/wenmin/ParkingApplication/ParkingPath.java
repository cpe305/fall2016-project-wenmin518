package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingPath {
  private int[][] adj;
  private int rows;
  private int cols;
  private int numVertices;
  private boolean[] visited;
  private int[] vertices;
  
  /**
   * Set up the adj matrix table.
   * 
   * @param ver referring to the vertex array.
   * @param cols referring to number of columns in the give input file.
   */
  public void setAdj(int[] ver, int cols) {
    for (int i = 0; i < numVertices; i++) {
      if (ver[i] == 0) {
        if (i - cols > -1) {
          if (ver[i - cols] == 0) {
            addEdge(i, i - cols);
          }
        }

        if ((i + 1) % cols != 0) {
          if (ver[i + 1] == 0) {
            addEdge(i, i + 1);
          }
        }
      }
    }
  }
  
  public void setAdj(int rowNum, int colNum, int value) {
    adj[rowNum][colNum] = value;
  }

  public int getAdjAt(int rowNum, int colNum) {
    return adj[rowNum][colNum];
  }

  public int[][] getAdj() {
    return adj;
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public int getNumVer() {
    return numVertices;
  }

  public boolean getVisitedAt(int vertex) {
    return visited[vertex];
  }

  public boolean[] getVisited() {
    return visited;
  }

  public int[] getVertices() {
    return vertices;
  }

  public void addEdge(int from, int end) {
    adj[from][end] = 1;
    adj[end][from] = 1;
  }

  /**
   * Set up the matrix from a file.
   * 
   * @param fileName referring to the file that contains numbers
   * @throws FileNotFoundException is thrown if file is not find.
   */
  public void setadjFromFile(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scan = new Scanner(file);
    rows = scan.nextInt();
    cols = scan.nextInt();
    numVertices = rows * cols;
    adj = new int[numVertices][numVertices];
    visited = new boolean[numVertices];
    vertices = new int[numVertices];
    for (int i = 0; i < numVertices; i++) {
      vertices[i] = scan.nextInt();
    }
    setAdj(vertices, cols);
    scan.close();
  }

}
