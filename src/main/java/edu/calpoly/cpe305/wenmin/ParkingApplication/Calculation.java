package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Observer that does caluclation when user changes the location.
 * 
 * @author wenmin518
 *
 */
public class Calculation implements Observer {

  private int[][] adj;
  private boolean[] visited;
  private User user;
  private int row;
  private int col;
  private int numVertices;
  private int[] previous;
  private ArrayList<ParkingStructure> parkingLoc;

  /**
   * Constructor.
   * 
   * @param adj the 2d array corresponding to the grid
   * @param visited used for whether each grid is been visited
   * @param user referring to the user object
   * @param row refers to how many rows in the grid
   * @param col number of colums in the grid
   * @param numVertices count for number of verticies
   * @param parkingLoc give the location of Parking structures
   */
  public Calculation(int[][] adj, boolean[] visited, User user, int row, int col, int numVertices,
      ArrayList<ParkingStructure> parkingLoc) {
    this.adj = adj;
    this.visited = visited;
    this.user = user;
    this.row = row;
    this.col = col;
    this.numVertices = numVertices;
    this.parkingLoc = parkingLoc;
    previous = new int[numVertices];
  }


  /**
   * convert Geoloc object to a single point.
   * 
   * @param loc referring to a Geoloc object that contains x and y.
   * @return a integer that is referring to the same location is a grid.
   */
  public int locToint(Geoloc loc) {
    return loc.getX() + loc.getY() * col;
  }

  /**
   * get the number in adj specified at row and col.
   * 
   * @param rowNum referring to the row at the table.
   * @param colNum referring to the col at the table.
   * @return number at the adj
   */
  public int getAdjAt(int rowNum, int colNum) {
    return adj[rowNum][colNum];
  }

  /**
   * gets the adj object.
   * 
   * @return 2d arr.
   */
  public int[][] getAdj() {
    return adj;
  }

  /**
   * gets the visited object.
   * 
   * @return boolean array indicates whether each vertex is being visited.
   */
  public boolean[] getVisited() {
    return visited;
  }

  /**
   * 
   * @param idx referring to the index of the visited arr.
   * @return the boolean at specified index
   */
  public boolean getVisitedAt(int idx) {
    return visited[idx];
  }

  /**
   * get the row.
   * 
   * @return the number of rows in the array.
   */
  public int getRow() {
    return row;
  }

  /**
   * get the colume.
   * 
   * @return the number of colums in the array.
   */
  public int getCol() {
    return col;
  }

  /**
   * number of vertices = number of rows * number of columns.
   * 
   * @return integer specifying number of vertices
   */
  public int getNumVer() {
    return numVertices;
  }

  /**
   * gets the user object.
   * 
   * @return user object with location and other attributes
   */
  public User getUser() {
    return user;
  }

  /**
   * 
   * @param idx referring to the index of the list.
   * @return Parking structure at specified index.
   */
  public ParkingStructure getStrAt(int idx) {
    return parkingLoc.get(idx);
  }

  /**
   * gets the parking array
   * 
   * @return the memory that contains parking structure arraylist.
   */
  public ArrayList<ParkingStructure> getParkStr() {
    return parkingLoc;
  }


  /**
   * adding path between loc1 to loc2.
   * 
   * @param loc1 starting location
   * @param loc2 ending location
   */
  public void addEdge(Geoloc loc1, Geoloc loc2) {
    int loc1Num = locToint(loc1);
    int loc2Num = locToint(loc2);
    adj[loc1Num][loc2Num] = 1;
    adj[loc2Num][loc1Num] = 1;
  }

  /**
   * Depth search through the component.
   * 
   * @param ver any vertex in the component
   */
  public void visit(int ver) {
    visited[ver] = true;
    for (int idx = 0; idx < numVertices; idx++) {
      if (adj[ver][idx] != 0 && visited[idx] == false) {
        visit(idx);
      }
    }
  }


  /**
   * @param start referring to the User location.
   * @param stop Parking location
   * @return number of steps from start to stop
   */
  public int fewestEdgePath(int start, int stop) {
    int[] path = new int[numVertices];

    for (int i = 0; i < numVertices; i++) {
      visited[i] = false;
    }

    LinkedList queue = new LinkedList();
    queue.addLast(start);
    visited[start] = true;
    // Do not assign the unecessary child assignment
    int child;
    while (!queue.isEmpty()) {
      child = (Integer) queue.removeLast();
      for (int i = 0; i < numVertices; i++) {
        if (adj[child][i] == 1 && !(visited[i])) {
          queue.addFirst(i);
          visited[i] = true;
          path[i] = path[child] + 1;
        }
      }
    }
    return path[stop];
  }


  /**
   * This method calculate the distance between two points.
   * 
   * @param user referring to the user Location
   * @param spotLoc referring to the parking structure entrance location
   * @return the distance between two points
   */
  public double distance(Geoloc user, Geoloc spotLoc) {

    return Math.sqrt(Math.multiplyExact(user.getX() - spotLoc.getX(), user.getX() - spotLoc.getX())
        + Math.multiplyExact(user.getY() - spotLoc.getY(), user.getY() - spotLoc.getY()));
  }

  /**
   * This method return the parking structure number that is closest to user.
   * 
   * @param user referring to the user's location in the map
   * @param parkStrLoc referring to the parking structure locations
   * @return the number of blocks to parking the closest parking structure
   */
  public int nearbyParkingStr(Geoloc user, ArrayList<ParkingStructure> parkStrLoc) {
    double newDis;
    int value = -1;
    double dis = -1.0;
    for (int i = 0; i < parkStrLoc.size(); i++) {
      newDis = distance(user, parkStrLoc.get(i).getPosition());
      if (parkStrLoc.get(i).getNumavailable() > 0) {
        if (dis > newDis) {
          dis = newDis;
          value = i;
        } else if (dis == -1.0) {
          dis = newDis;
          value = i;
        }
      }
    }
    return value;
  }

  /**
   * Print necessary information to find the nearest parking structure and parking spot.
   * 
   * @param userLoc referring to the position of user.
   * @param parkLoc referring to the parking structure locations.
   */
  public String printInfo(Geoloc userLoc) {
    String str = "\n";
    int parkingStrNum = nearbyParkingStr(userLoc, parkingLoc);
    String car = null;
    if (parkingStrNum != -1) {
      int start = locToint(userLoc);
//      System.out.println("start : " + start + " ");
//      System.out.println("paring str num " + parkingStrNum);
      double distance =
          fewestEdgePath(start, locToint(parkingLoc.get(parkingStrNum).getPosition()));
      distance *= 0.027;
      str += "The nearest ParkingStructre is P" + (parkingStrNum + 1) + "\n";
      if (user.getCarType() == 1) {
        car = "Compact";
      }
      if (user.getCarType() == 2) {
        car = "Electric";
      }
      if (user.getCarType() == 3) {
        car = "Handicap";
      }
      if (user.getCarType() == 4) {
        car = "Normal";
      }
      if (parkingLoc.get(parkingStrNum).getSmallestTypeNum(user.getCarType()) == -1) {
        str += car + " car that you are looking for is not available in parking " + parkingStrNum
            + "\n";
      } else {
        str += "Parking Spot #"
            + (parkingLoc.get(parkingStrNum).getSmallestTypeNum(user.getCarType()) + 1) + " is "
            + car + " Parking Spot you are looking for\n";
      }

      String dis = String.format("%.1f", distance);
      str += "The parking spot is about " + dis + " miles away\n";
      String time = String.format("%.1f", distance / 15 * 60);
      str += "It is gonna take about " + time + " minutes";
    } else {
      str += "Sorry, every parking spot is taken, try tomorrow, ---\n";
    }
    return str;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Calculation)) {
      return false;
    }

    Calculation cal = (Calculation) obj;
    for (int j = 0; j < row; j++) {
      for (int k = 0; k < col; k++) {
        if (adj[j][k] != cal.getAdjAt(j, k)) {
          return false;
        }
      }
    }

    if (col != cal.getCol()) {
      return false;
    }
    if (numVertices != cal.getNumVer()) {
      return false;
    }
    if (parkingLoc.size() != cal.getParkStr().size()) {
      return false;
    }
    for (int index = 0; index < parkingLoc.size(); index++) {
      if (parkingLoc.get(index).equals(cal.getParkStr().get(index))) {
        return false;
      }
    }
    if (row != cal.getRow()) {
      return false;
    }
    if (!(user.equals(cal.getUser()))) {
      return false;
    }
    for (int idx = 0; idx < visited.length; idx++) {
      if (visited[idx] != cal.getVisitedAt(idx)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void updateUserLoc(Geoloc loc) {
    user.setPosition(loc);

  }

  @Override
  public void updateCartype(int type) {
    user.setcarType(type);
  }

}
