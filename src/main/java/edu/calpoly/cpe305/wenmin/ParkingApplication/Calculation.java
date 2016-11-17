package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

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
  
  public void updateUserPos(Geoloc pos) {
    // TODO Auto-generated method stub
    user.setPosition(pos);
  }
  
  public int locToint(Geoloc loc) {
    return loc.getX() + loc.getY() * col;
  }

  public int getAdjAt(int rowNum, int colNum) {
    return adj[rowNum][colNum];
  }
  
  public int[][] getAdj() {
    return adj;
  }
  
  public boolean[] getVisited() {
    return visited;
  }
  
  public boolean getVisitedAt(int idx) {
    return visited[idx];
  }
  
  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getNumVer() {
    return numVertices;
  }
  
  public User getUser() {
    return user;
  }

  public ParkingStructure getStrAt(int idx) {
    return parkingLoc.get(idx);
  }
  
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
    while (queue.isEmpty() == false) {
      child = (Integer) queue.removeLast();
      for (int i = 0; i < numVertices; i++) {
        if (adj[child][i] == 1 && visited[i] == false) {
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
    double xdis;
    double ydis;
    xdis = user.getX() - spotLoc.getX();
    ydis = user.getY() - spotLoc.getY();
    return Math.sqrt(xdis * xdis + ydis * ydis);
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
    double dis = -1;
    for (int i = 0; i < parkStrLoc.size(); i++) {
      newDis = distance(user, parkStrLoc.get(i).getPosition());
      if (parkStrLoc.get(i).getNumavailable() > 0) {
        if (dis > newDis || dis == -1) {
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
  public void printInfo(Geoloc userLoc, ArrayList<ParkingStructure> parkLoc) {
    int parkingStrNum = nearbyParkingStr(userLoc, parkingLoc);
    String car = null;
    if (parkingStrNum != -1) {
      int start = locToint(userLoc);
      double distance = fewestEdgePath(start, parkingStrNum);
      distance *= 0.027;
      System.out.println("The nearest ParkingStructre is " + (parkingStrNum + 1));
      System.out.println("Parking Spot #" + (parkingLoc.get(parkingStrNum).getSmallestSpotNum() + 1)
          + " is the first available parking spot");
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
        System.out.println(
            car + " car that you are looking for is not available in parking " + parkingStrNum);
      } else {
        System.out.println("Parking Spot #"
            + (parkingLoc.get(parkingStrNum).getSmallestTypeNum(user.getCarType()) + 1) + " is "
            + car + " that you are looking for");
      }
      System.out.println("The parking spot is about " + distance + " miles away");
      System.out.println("It is gonna take about " + distance / 10 * 60 + " minutes");
    } else {
      System.out.println("Sorry, every parking spot is taken, try tomorrow, ---");
    }

  }
}
