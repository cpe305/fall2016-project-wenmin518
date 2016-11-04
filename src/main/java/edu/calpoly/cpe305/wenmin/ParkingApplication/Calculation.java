package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.util.ArrayList;

/**
 * Observer that does caluclation when user changes the location.
 * 
 * @author wenmin518
 *
 */
public class Calculation implements Observer {

  public int[][] adj;
  public boolean[] visited;
  public Geoloc userLoc;
  public int row;
  public int col;
  public int numVertices;
  public ArrayList<ParkingStructure> parkingLoc;

  /**
   * Constructor.
   * 
   * @param adj the 2d array corresponding to the grid
   * @param visited used for whether each grid is been visited
   * @param userLoc user location is used to calculated the nearest partking structure
   * @param row refers to how many rows in the grid
   * @param col number of colums in the grid
   * @param numVertices count for number of verticies
   * @param parkingloc give the location of Parking structures
   */
  public Calculation(int[][] adj, boolean[] visited, Geoloc userLoc, int row, int col,
      int numVertices, ArrayList<ParkingStructure> parkingLoc) {
    this.adj = adj;
    this.visited = visited;
    this.userLoc = userLoc;
    this.row = row;
    this.col = col;
    this.numVertices = numVertices;
    this.parkingLoc = parkingLoc;
  }

  public int locToint(Geoloc loc) {
    return loc.getX() + loc.getY() * col;
  }

  public void updateUserPos(Geoloc pos) {
    // TODO Auto-generated method stub
    userLoc = pos;
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
    double xDis;
    double yDis;
    xDis = user.getX() - spotLoc.getX();
    yDis = user.getY() - spotLoc.getY();
    return Math.sqrt(xDis * xDis + yDis * yDis);
  }

  /**
   * This method compares the distance between
   * 
   * @param user
   * @param parkStrLoc
   * @return
   */
  public int nearbyParkingStr(Geoloc user, ArrayList<ParkingStructure> parkStrLoc) {
    double newDis;
    int value = 0;
    double dis = distance(user, parkStrLoc.get(value).getPosition());
    for (int i = 1; i < parkStrLoc.size(); i++) {
      newDis = distance(user, parkStrLoc.get(i).getPosition());
      if (dis > newDis && parkStrLoc.get(i).getNumavailable() > 0) {
        dis = newDis;
        value = i;
      }
    }
    return value;
  }

  public void printInfo(Geoloc userLoc, ArrayList<ParkingStructure> parkLoc) {
    int parkingStrNum = nearbyParkingStr(userLoc, parkingLoc);
    int start = locToint(userLoc);
    double distance = fewestEdgePath(start, parkingStrNum) * 0.027;
    System.out.println("The nearest ParkingStructre is " + (parkingStrNum + 1));
    System.out.println("Parking Spot #" + (parkingLoc.get(parkingStrNum).getSmallestSpotNum() + 1));
    
    System.out.println("The parking spot is about ) " +distance + "miles away");
    System.out.println("It is gonna take about " + distance / 10 * 60 +" minutes");
  }
}
