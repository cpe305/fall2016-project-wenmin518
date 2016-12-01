package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
      String xstr = (String) structObj.get("x");
      int xloc = Integer.parseInt(xstr);
      String ystr = (String) structObj.get("y");
      int yloc = Integer.parseInt(ystr);

      Geoloc strLoc = new Geoloc(xloc, yloc);
      ParkingStructure ps = new ParkingStructure(strLoc);

      JSONArray parkingList = (JSONArray) structObj.get("Parking Spot List");
      for (Object o : parkingList) {
        JSONObject parkingSpot = (JSONObject) o;
        String numStr = (String) parkingSpot.get("Parking Number");
        int num = (int) Integer.valueOf(numStr);
        String typeStr = (String) parkingSpot.get("Type");
        int type = (int) Integer.valueOf(typeStr);
        String availStr = (String) parkingSpot.get("availability");
        int avail = (int) Integer.valueOf(availStr);
        boolean availability = false;
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
  public List<ParkingStructure> getStructArr() {
    return structArr;
  }

  /**
   * Setter method for ParkingStructure array.
   * 
   * @param idx referring to the index to be changed
   * @param ps referring to the parking structure that you want it to change to
   */
  public void setStrArrAt(int idx, ParkingStructure ps) {
    structArr.set(idx, ps);
  }
}
