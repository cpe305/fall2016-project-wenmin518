package test.java.edu.calpoly.cpe305.wenmin.polyparktrack;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import main.java.edu.calpoly.cpe305.wenmin.polyparktrack.Fxdriver;

public class TestDriver {

  @Test
  public void testIntegerMethods() {
    assertTrue(Fxdriver.isInteger("100"));
    assertFalse(Fxdriver.isInteger("somthing"));
    assertFalse(Fxdriver.isInteger("-1"));
  }
  
  @Test
  public void testgetcartype() {
    assertEquals(Fxdriver.getCarType("Normal"), 4);
    assertEquals(Fxdriver.getCarType("Handicap"), 3);
    assertEquals(Fxdriver.getCarType("Electric"), 2);
    assertEquals(Fxdriver.getCarType("Compact"), 1);
    assertEquals(Fxdriver.getCarType("1"), 1);
    assertEquals(Fxdriver.getCarType("2"), 2);
    assertEquals(Fxdriver.getCarType("3"), 3);
    assertEquals(Fxdriver.getCarType("4"), 4);
  }
  
  @Test
  public void inboundTest() {
    assertTrue(Fxdriver.inboundblick(300, 300, 200, 600, 600));
    assertFalse(Fxdriver.inboundblick(100, 300, 200, 600, 600));
    assertFalse(Fxdriver.inboundblick(300, 300, 200, 10000, 100));
  }
  
  @Test
  public void testhandler() {
    Group group = new Group();
    Circle circle = new Circle();
    circle.prefHeight(100);
    circle.prefWidth(100);
    Fxdriver.addEventHandler(group, circle);
    assertEquals(1, 1);
    
  }

}
