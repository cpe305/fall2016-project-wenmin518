package test.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.edu.calpoly.cpe305.wenmin.ParkingApplication.LinkedList;



public class TestLinkedList {

  @Test
  public void linekdListTest1() {
    LinkedList list = new LinkedList();
    LinkedList listtwo = new LinkedList();
    list.addLast(3);
    list.addFirst(3);
    listtwo = (LinkedList) list.clone();
    assertEquals(list.length(), 2);
    assertFalse(list.isEmpty());
    assertFalse(list.equals(2));
    assertTrue(list.contains(3));
    assertEquals(listtwo, list);
    listtwo.remove(3);
    assertFalse(list.contains(3));
  }

  @Test
  public void linekdListTest2() {
    LinkedList list = new LinkedList();
    LinkedList listtwo = new LinkedList();
    listtwo.addFirst(100);
    list.addLast(2);
    list.addFirst(3);
    list.addFirst(10);
    assertEquals(10, list.removeFirst());
    assertEquals(2, list.removeLast());
    list.clear();
    listtwo.clear();
    assertEquals(listtwo, list);
  }  

  @Test
  public void linekdListTest3() {
    LinkedList list = new LinkedList();
    LinkedList listtwo = new LinkedList();
    list.addFirst(100);
    list.addLast(300);
    assertEquals(list.length(), 2);
    list.remove(100);
    assertEquals(list.length(), 1);
  }  
}
