package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

/**
 * this class reads in the json file and do the demo.
 * @author wenmin518
 *
 */
public class DemoDriver {
  private static ParkingPath parkingPath;
  private static ParkingStrJson structJson;
  private static Scanner scan;

  private DemoDriver(){}
  /**
   * The main method of driver.
   */
  public static void doFunctions() {
    scan = new Scanner(System.in);
    System.out.println("Welcome to POLYPARKTRACK, Press Y to continue");
    String permission;
    while (scan.hasNext() && (permission = scan.nextLine()).equalsIgnoreCase("y")) {
      System.out.println("Please Enter your current location as ");
      System.out.println("as x y");
      int xval = scan.nextInt();
      int yval = scan.nextInt();
      while (xval > parkingPath.getRows() || yval > parkingPath.getCols()) {
        System.out.println("your x or y is out of range.");
        System.out.println("Please Enter valid positions");
        xval = scan.nextInt();
        yval = scan.nextInt();
      }
      Geoloc userLoc = new Geoloc(xval, yval);
      System.out.println("You entered (" + xval + ", " + yval + ")");
      System.out.println("Please Enter the type of parking spot that you are looking for:");
      System.out.println("1: Compact. 2: Electric. 3: Handicap. 4: Normal");
      int typeInput = scan.nextInt();
      while (1 > typeInput || typeInput > 4) {
        System.out.println("Your input is out of range");
        System.out.println("Please enter valid type input");
        typeInput = scan.nextInt();
      }
      User user = new User(userLoc, typeInput);
      Calculation cal = new Calculation(parkingPath.getAdj(), parkingPath.getVisited(), user,
          parkingPath.getRows(), parkingPath.getCols(), parkingPath.getNumVer(),
          structJson.getStructArr());
      System.out.println();
      cal.printInfo(userLoc);

    }
    System.out.println("Press Y if you want to continue");
    permission = scan.nextLine();
  }


  /**
   * The main driver class.
   * 
   * @param args Referring to the command line argument.
   * @throws IOException Prevent the file not find.
   * @throws ParseException prevent if file not parse correctly.
   */
  public static void main(String[] args) throws IOException, ParseException {
    parkingPath = new ParkingPath();
    parkingPath.setadjFromFile("test.txt");
    structJson = new ParkingStrJson();
    structJson.setParkLocFromFile("test1.json");
    doFunctions();
  }
}
