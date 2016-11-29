package main.java.edu.calpoly.cpe305.wenmin.parkingapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Referring to the object that sets up the required path and 2d array for parking structure
 * application from a preset text file.
 * 
 * @author wenmin518
 *
 */
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
        if ((i - cols > -1) && (ver[i - cols] == 0)) {
          addEdge(i, i - cols);
        }

        if (((i + 1) % cols != 0) && (ver[i + 1] == 0)) {
          addEdge(i, i + 1);
        }
      }
    }
  }

  /**
   * set the value of adjacency matrix at specified row and col.
   * 
   * @param rowNum referring to the row of adj matrix.
   * @param colNum referring to the col of adj matrix.
   * @param value referring to the value to be changed.
   */
  public void setAdjAt(int rowNum, int colNum, int value) {
    adj[rowNum][colNum] = value;
  }

  /**
   * gets the integer value of adj matrix at specified row and col.
   * 
   * @param rowNum referring to the row of adj matrix.
   * @param colNum referring to the col of adj matrix.
   * @return the integer at adj matrix.
   */
  public int getAdjAt(int rowNum, int colNum) {
    return adj[rowNum][colNum];
  }

  /**
   * gets the adj matrix.
   * 
   * @return the memory that contains adj matrix.
   */
  public int[][] getAdj() {
    return adj;
  }

  /**
   * gets number of rows in adj matrix.
   * 
   * @return integer indicates the number of rows.
   */
  public int getRows() {
    return rows;
  }

  /**
   * gets number of cols in adj matrix.
   * 
   * @return integer indicates the number of cols.
   */
  public int getCols() {
    return cols;
  }

  /**
   * gets number of vertices adj matrix.
   * 
   * @return integer indicates the number of vertices.
   */
  public int getNumVer() {
    return numVertices;
  }

  /**
   * 
   * @param vertex referring to specified vertex in boolean array.
   * @return the boolean at specified index.
   */
  public boolean getVisitedAt(int vertex) {
    return visited[vertex];
  }


  /**
   * gets boolean array.
   * 
   * @return memory of visited array.
   */
  public boolean[] getVisited() {
    return visited;
  }

  /**
   * gets vertices array.
   * 
   * @return memory of vertices array.
   */
  public int[] getVertices() {
    return vertices;
  }

  /**
   * 
   * @param from referring to the starting index.
   * @param end referring to the ending index.
   */
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
