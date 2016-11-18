package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
  public void setParkLocFromFile(String fileName) throws IOException, ParseException {
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
        /**
         * There is one problem here if I were to change to String it will work for json, but not for java
         */
        
//        boolean availability = (boolean) parkingSpot.get("availability");
        String availability = (String) parkingSpot,get("availability");

        //        System.out.println(availability);
        ParkingSpot parkSpot =
//            new ParkingSpot(num, type, availability);
             new ParkingSpot(num, type, Boolean.parseBoolean(availability));
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
   * @return the memory containing the parking strucuture arraylist.
   */
  public ArrayList<ParkingStructure> getStructArr() {
    return structArr;
  }
}
