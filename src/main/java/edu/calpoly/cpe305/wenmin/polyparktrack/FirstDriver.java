package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The first driver class that reads in the regular text file.
 * 
 * @author wenmin518
 *
 */
public class FirstDriver {

  private static int[][] adj;
  private static int rows;
  private static int cols;
  private static int numVertices;
  private static boolean[] visited;
  private static int[] vertices;
  private static ArrayList<ParkingStructure> structArr;
  private static Scanner scan;

  private FirstDriver() {}

  /**
   * add the connection between two index.
   * 
   * @param from refer to the starting index
   * @param end refers to the ending index
   */

  public static void addEdge(int from, int end) {
    adj[from][end] = 1;
    adj[end][from] = 1;
  }

  /**
   * Convert a single integer point to a geoloc object.
   * 
   * @param num referring to the number to be converted.
   * @param col referring to how many columns are in the adjacency matrix.
   * @return the geoloc object.
   */
  public static Geoloc locToPoint(int num, int col) {
    return new Geoloc(num % col, num / col);
  }

  /**
   * setup up the adjacency matrix.
   * 
   * @param ver referring to the vertex array
   * @param cols referring to the number of cols
   */

  public static void setAdj(int[] ver, int cols) {
    for (int i = 0; i < numVertices; i++) {
      if (ver[i] == 0 && i - cols > 1 && (ver[i - cols] == 0)) {
        addEdge(i, i - cols);
      }

      if (((i + 1) % cols != 0) && (ver[i + 1] == 0)) {
        addEdge(i, i + 1);
      }
    }

  }


  /**
   * Read in the file and set up the 2d array.
   * 
   * @param fileName refer to the text file that contains parking info
   * @throws FileNotFoundException throw if the file is not found
   * 
   */


  public static void setadjFromFile(String fileName) throws FileNotFoundException {
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

  /**
   * Print the adjacency matrix.
   * 
   */
  public static void printAdj() {
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < numVertices; j++) {
        System.out.print(adj[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * sets up the objects according to file.
   * 
   * @param fileName referring to the file to be read in.
   * @throws FileNotFoundException throws if the file is not exit in the project.
   */
  public static void setParkLocFromFile(String fileName) throws FileNotFoundException {
    structArr = new ArrayList<ParkingStructure>();
    File file = new File(fileName);
    Scanner scan = new Scanner(file);
    Scanner lineScan;
    String lineContent;
    int parkingStrNum = -1;
    while (scan.hasNextLine()) {
      lineContent = scan.nextLine();
      lineScan = new Scanner(lineContent);
      String ele = lineScan.next();
      if (ele.equals("ParkingStructure")) {
        parkingStrNum++;
        ParkingStructure ps =
            new ParkingStructure(new Geoloc(lineScan.nextInt(), lineScan.nextInt()));
        structArr.add(ps);
      }
      if (ele.equals("ParkingSpot")) {
        ParkingSpot ps =
            new ParkingSpot(lineScan.nextInt(), lineScan.nextInt(), lineScan.nextBoolean());

        structArr.get(parkingStrNum).addtoSpotArr(ps);
      }
    }
    scan.close();

  }

  /**
   * Do the driver function.
   */
  public static void doFunctions() {
    scan = new Scanner(System.in);
    System.out.println("Welcome to POLYPARKTRACK, Press Y to continue");
    while (scan.hasNext() && (scan.nextLine()).equalsIgnoreCase("y")) {
      System.out.println("Please Enter your current location as ");
      System.out.println("as x y");
      int xval = scan.nextInt();
      int yval = scan.nextInt();
      if (xval > rows || yval > cols) {
        System.out.println("your x or y is out of range");
      } else {
        Geoloc userLoc = new Geoloc(xval, yval);
        System.out.println("You entered (" + xval + ", " + yval + ")");
        System.out.print("x: " + userLoc.getX() + " y: " + userLoc.getY());
        System.out.println("Please Enter the type of parking spot that you are looking for:");
        System.out.println("1: Compact. 2: Electric. 3: Handicap. 4: Normal");
        int typeInput = scan.nextInt();
        if (1 > typeInput && typeInput > 4) {
          System.out.println("Your input is out of range");
        } else {
          User user = new User(userLoc, typeInput);
          Calculation cal = new Calculation(adj, visited, user, rows, cols, rows * cols, structArr);
          System.out.println();
          cal.printInfo(userLoc);
        }
      }
      System.out.println("Press Y if you want to continue");

    }
  }

  /**
   * main method.
   * 
   * @param args command line arguments
   * @throws FileNotFoundException prevent file not found
   */
  public static void main(String[] args) throws FileNotFoundException {
    setadjFromFile("test.txt");
    setParkLocFromFile("Structure1full.txt");

    doFunctions();
  }
}
