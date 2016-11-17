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
  
  public ParkingStrJson() {
    structArr = new ArrayList<ParkingStructure>();
  }
  
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
        String availability = (String) parkingSpot.get("availability");

        ParkingSpot parkSpot =
            new ParkingSpot(num, type, Boolean.parseBoolean(availability));

        ps.addtoSpotArr(parkSpot);
      }
      structArr.add(ps);
    }
  }
  
  public ParkingStructure getStrcutre(int idx) {
    return structArr.get(idx);
  }
  
  public ArrayList<ParkingStructure> getStructArr() {
    return structArr;
  }
}
