package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JasonFileDriver {
  public static int[][] adj;
  public static int rows;
  public static int cols;
  public static int numVertices;
  public static boolean[] visited;
  public static int[] vertices;
  public static ArrayList<ParkingStructure> structArr;

  /**
   * add the connection between two vertices.
   * 
   * @param from refer to the starting vertex
   * @param end refers to the ending vertex
   */
  public static void addEdge(int from, int end) {
    adj[from][end] = 1;
    adj[end][from] = 1;
  }

  public static Geoloc locToPoint(int num, int col) {
    return new Geoloc(num % col, num / col);
  }

  /**
   * setup the adjacency matrix.
   * 
   * @param ver refering to the vertex array
   * @param cols number of cols
   */
  public static void setAdj(int[] ver, int cols) {
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

  /**
   * Read in the file and set up the 2d array.
   * 
   * @param fileName refer to the text file that contains parking info
   * @throws FileNotFoundException throw if the file is not found
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
   * Set up the parking structure and parking spot arrays from the input file.
   * @param fileName referring to the json file that contains information about parking structures.
   * @throws IOException prevent file from not found.
   * @throws ParseException exception is thrown if the parse format is not correct.
   */
  public static void setParkLocFromFile(String fileName) throws IOException, ParseException {
    structArr = new ArrayList<ParkingStructure>();
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(fileName));

    JSONObject jsonObject = (JSONObject) obj;
    JSONArray structureList = (JSONArray) jsonObject.get("Structure List");
    
    for (Object newObj : structureList) {
      JSONObject structObj = (JSONObject) newObj;
      int strNum = Integer.valueOf((String) structObj.get("Structure Number"));
      int xloc = Integer.valueOf((String) structObj.get("x"));
      int yloc = Integer.valueOf((String) structObj.get("y"));


      ParkingStructure ps =
          new ParkingStructure(new Geoloc(xloc, yloc));
      
      JSONArray parkingList = (JSONArray) structObj.get("Parking Spot List");
      for (Object o : parkingList) {
        JSONObject parkingSpot = (JSONObject) o;
        int num = (int) Integer.valueOf((String) parkingSpot.get("Parking Number"));
        int type = (int) Integer.valueOf((String) parkingSpot.get("Type"));
        boolean availability = (boolean) parkingSpot.get("availability");

        ParkingSpot parkSpot =
            new ParkingSpot(num, type, availability);

        ps.addtoSpotArr(parkSpot);
      }
      structArr.add(ps);
    }

  }

  public static void doFunctions() {
    Scanner scan = new Scanner(System.in);
    String permission;
    System.out.println("Welcome to POLYPARKTRACK, Press Y to continue");
    while (scan.hasNext() && (permission = scan.nextLine()).equalsIgnoreCase("y")) {
      System.out.println("Please Enter your current location as ");
      System.out.println("as x y");
      int xval = scan.nextInt();
      int yval = scan.nextInt();
      if (xval > rows || yval > cols) {
        System.out.println("your x or y is out of range");
      } else {
        Geoloc userLoc = new Geoloc(xval, yval);
        System.out.println("You entered (" + xval + ", " + yval + ")");
        System.out.println("Please Enter the type of parking spot that you are looking for:");
        System.out.println("1: Compact. 2: Electric. 3: Handicap. 4: Normal");
        int typeInput = scan.nextInt();
        if (1 > typeInput && typeInput > 4) {
          System.out.println("Your input is out of range");
        }
        else {
          User user = new User(userLoc, typeInput);
          Calculation cal =
              new Calculation(adj, visited, user, rows, cols, rows * cols, structArr);
          System.out.println();
          cal.printInfo(userLoc, structArr);
        }
      }
      System.out.println("Press Y if you want to continue");
      permission = scan.nextLine();

    }
  }

  /**
   * Main driver
   * @param args takes in the json and required test files
   * @throws IOException 
   * @throws ParseException
   */
  public static void main(String[] args) throws IOException, ParseException {
    setadjFromFile("test.txt");
    setParkLocFromFile("test1.json");

    // printAdj();
    doFunctions();
  }
}
