package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.ParkingStrJson;

public class TestParkingStrJson {
  ParkingStrJson structJson = new ParkingStrJson();


  @Test
  public void testAllMethods() throws ParseException, IOException {
    structJson.setParkLocFromFile(
        "jsonfiles/JsonTest2.json");
    assertEquals(structJson.getStrcutre(0).getNumavailable(), 51);
    assertEquals(structJson.getStructArr().get(1).getNumavailable(), 46);
    structJson.setStrArrAt(0, structJson.getStrcutre(1));
    assertEquals(structJson.getStructArr().get(0).getNumavailable(), 46);
  }

}
