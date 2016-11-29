package main.java.edu.calpoly.cpe305.wenmin.parkingapplication;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Referring to the parking structure array objects read from json file.
 * 
 * @author wenmin518
 *
 */
public class ParkingStrJson {
  private ArrayList<ParkingStructure> structArr;

  /**
   * Constructor.
   */
  public ParkingStrJson() {
    structArr = new ArrayList<ParkingStructure>();
  }

  /**
   * 
   * @param fileName referring to the file name to read in.
   * @throws IOException prevent from file not found.
   * @throws ParseException gets thrown when json file is not read correctly.
   */
  public void setParkLocFromFile(String fileName) throws ParseException, IOException {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(fileName));
    JSONObject jsonObject = (JSONObject) obj;
    JSONArray structureList = (JSONArray) jsonObject.get("Structure List");

    for (Object newObj : structureList) {
      JSONObject structObj = (JSONObject) newObj;
      int xloc = Integer.valueOf((String) structObj.get("x"));
      int yloc = Integer.valueOf((String) structObj.get("y"));

      ParkingStructure ps = new ParkingStructure(new Geoloc(xloc, yloc));

      JSONArray parkingList = (JSONArray) structObj.get("Parking Spot List");
      for (Object o : parkingList) {
        JSONObject parkingSpot = (JSONObject) o;
        int num = (int) Integer.valueOf((String) parkingSpot.get("Parking Number"));
        int type = (int) Integer.valueOf((String) parkingSpot.get("Type"));
        int avail = (int) Integer.valueOf((String) parkingSpot.get("availability"));
        boolean availability = false;;
        if (avail == 1) {
          availability = true;
        }
        ParkingSpot parkSpot = new ParkingSpot(num, type, availability);
        ps.addtoSpotArr(parkSpot);
      }
      structArr.add(ps);
    }
  }

  /**
   * 
   * @param idx referring to the index of the structure array
   * @return the parking structure at specified index.
   */
  public ParkingStructure getStrcutre(int idx) {
    return structArr.get(idx);
  }

  /**
   * gets the parking structure arraylist.
   * 
   * @return the memory containing the parking strucuture arraylist.
   */
  public ArrayList<ParkingStructure> getStructArr() {
    return structArr;
  }
}
