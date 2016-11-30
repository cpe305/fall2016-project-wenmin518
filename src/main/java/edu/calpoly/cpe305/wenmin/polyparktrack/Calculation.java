package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import java.util.ArrayList;

/**
 * Observer that does caluclation when user changes the location.
 * 
 * @author wenmin518
 *
 */
public class Calculation implements Observer {
  private double ratio = 0.027;
  private int[][] adj;
  private boolean[] visited;
  private User user;
  private int row;
  private int col;
  private int numVertices;
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

    return (double) Math
        .sqrt(Math.multiplyExact(user.getX() - spotLoc.getX(), user.getX() - spotLoc.getX())
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
   * returns the life distance.
   * 
   * @param start referring to the starting location or user location
   * @param end referring to the structure locations
   * @return the distance between user and the nearest structure location
   */
  public double getCalpolyDistance(int start, int end) {
    double distance = fewestEdgePath(start, end);
    distance *= ratio;
    return distance;
  }

  /**
   * Print the distance with 1 decimal format.
   * 
   * @param distance referring to the distance between user and nearest parking structure
   * @return the string referring to distance with 1 decimal format;
   */
  public String distanceString(double distance) {
    String result = String.format("%.1f", distance);
    return "The parking spot is about " + result + " miles away\n";
  }

  /**
   * get the info tells time requires to travel.
   * 
   * @param distance referring to the distance between user and nearest parking structure.
   * @return string tells about time required with 1 decimal place.
   */
  public String timeString(double distance) {
    String result = String.format("%.1f", distance / 15 * 60.0);
    return "It is gonna take about " + result + " minutes";
  }

  /**
   * String tells the nearest parking Structure number.
   * 
   * @param num referring to he nearest parking structure number
   * @return the string that displays parking structure number info.
   */
  public String printParkingNum(int num) {
    String result = "";
    result += "The nearest ParkingStructure is P" + (num + 1) + "\n";
    return result;
  }


  /**
   * Print the string saying which parking spot is available.
   * 
   * @param parkingStrNum referring to the nearest parking structure
   * @return the string tells user about the nearest parking spot that he/she is looking for
   */
  public String printCarType(int parkingStrNum) {
    String car = null;
    String str = null;

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
      str +=
          car + " car that you are looking for is not available in parking " + parkingStrNum + "\n";
    } else {
      str += "Parking Spot #"
          + (parkingLoc.get(parkingStrNum).getSmallestTypeNum(user.getCarType()) + 1) + " is " + car
          + " Parking Spot you are looking for\n";
    }
    return str;
  }

  /**
   * Print necessary information to find the nearest parking structure and parking spot.
   * 
   * @param userLoc referring to the position of user.
   * @return the string referring to the information that user might want to know.
   */
  public String printInfo(Geoloc userLoc) {
    String str = "\n";
    int parkingStrNum = nearbyParkingStr(userLoc, parkingLoc);
    if (parkingStrNum != -1) {

      int start = locToint(userLoc);
      int end = locToint(parkingLoc.get(parkingStrNum).getPosition());
      str += printParkingNum(parkingStrNum);
      str += printCarType(parkingStrNum);
      double distance = getCalpolyDistance(start, end);
      str += distanceString(distance);
      str += timeString(distance);
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
