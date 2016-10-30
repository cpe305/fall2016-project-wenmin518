package edu.calpoly.cpe305.wenmin.ParkingApplication;


import java.util.Iterator;

public class LinkedList implements Cloneable {

  private class Node {
    private Object item;
    private Node next;
    private Node prev;

    private Node(Object theObj) {
      item = theObj;
      next = null;
      prev = null;
    }
  }

  private Node first;
  private Node last;
  private int count;

  /**
   * Constructor.
   */
  public LinkedList() {
    first = null;
    last = null;
    count = 0;
  }

  /**
   * .
   * 
   * @param item the object to add to the first position in LinkedList
   */
  public void addFirst(Object item) {
    // Supply details as in the assignment description]
    count++;
    Node node = new Node(item);
    if (last == null) {
      last = node;
    }

    if (first == null) {
      first = node;
    } else {
      first.prev = node;
      node.next = first;
      first = node;
    }
  }

  /**
   * Insert the given object at the end of the list.
   * 
   * @param item is the object to add
   */
  public void addLast(Object item) {
    count++;
    Node node = new Node(item);
    if (first == null) {
      first = node;
    }

    if (last == null) {
      last = node;
    } else {
      last.next = node;
      node.prev = last;
      last = node;
    }
  }

  /**
   * Return the number of items in the list.
   * @return integer indicating number of elements
   */
  public int length() {
    return count;
  }

  /**
   * Determine if the list contains no items.
   * 
   * @return boolean
   */
  public boolean isEmpty() {
    // Supply details as in the assignment description
    Node previous = first;
    if (previous == null) {
      return true;
    }

    for (int i = 0; i < count; i++) {
      if (previous.item != null) {
        return false;
      }
      previous = previous.next;
    }
    return true;
  }

  /**
   * Clears the object in the linkedlist.
   */
  public void clear() {
    first = null;
    last = null;
    count = 0;
  }

  /**
   * Determine if the list contains the given item.
   * 
   * @param item is the object to add
   * @return if the item was presented
   */
  public boolean contains(Object item) {
    Node previous = first;
    if (previous == null) {
      return false;
    }
    for (int i = 0; i < count; i++) {
      if (item.equals(previous.item)) {
        return true;
      }
      previous = previous.next;
    }
    return false;
  }

  /**
   * Remove first item on the list and return it.
   * 
   * @return the last item removed from the linkedlist
   */
  public Object removeFirst() {
    // Supply details as in the assignment description
    if (this.isEmpty()) {
      throw new Error("The LinkedList is empty");
    }
    Object obj = first.item;
    if (first == last) {
      first = null;
      last = null;
      count = 0;
    } else {
      count--;
      first = first.next;
      first.prev = null;
    }
    return obj;
  }

  /**
   * Remove last item on the list and return it.
   * 
   * @return the item removed
   */
  public Object removeLast() {
    // Supply details as in the assignment description
    if (this.isEmpty()) {
      throw new Error("The LinkedList is empty");
    }

    Object obj = last.item;

    if (first == last) {
      last = null;
      first = null;
      count = 0;
    } else {
      count--;
      last = last.prev;
      last.next = null;
    }

    return obj;
  }

  /**
   * Determine if the obj is the same as the object in LinkedLists.
   */
  public boolean equals(Object obj) {
    // Supply details as in the assignment description

    if (obj == null) {
      return false;
    }
    if (!(obj instanceof LinkedList)) {
      return false;
    }

    LinkedList list = (LinkedList) obj;

    if (this.count != list.count) {
      return false;
    }

    Node current = this.first;
    Node newList = list.first;

    while (current != null && newList != null) {
      if (!(current.item.equals(newList.item))) {
        return false;
      }
      if (current.item == null) {
        return false;
      }
      current = current.next;
      newList = newList.next;
    }

    return true;
  }

  /**
   * remove object in the linkedlist.
   * 
   * @param obj refering the object to be removed
   * @return if the object is removeble
   */
  public boolean remove(Object obj) {
    Node previous = first;
    for (int i = 0; i < this.length(); i++) {
      if (obj.equals(previous.item)) {
        if (previous == first) {
          this.removeFirst();
        } else if (previous == last) {
          this.removeLast();
        } else {
          previous.prev.next = previous.next;
          previous.next.prev = previous.prev;
        }
        return true;
      }

      previous = previous.next;
    }
    return false;
  }

  /**
   * copy the linkedList.
   */
  public Object clone() {
    LinkedList theClone = new LinkedList();
    Node previous = this.first;

    while (previous != null) {
      theClone.addLast(previous.item);
      previous = previous.next;
    }

    return theClone;
  }

  public Iterator iterator() {
    return new itr();
  }

  /**
   * Iterator default methods and class.
   * @author wenmin518
   */
  public class itr implements Iterator {
    private Node current;

    public itr() {
      current = first;
    }

    /**
     * return whether the linkedlist has next element.
     */
    public boolean hasNext() {
      if (current == null) {
        return false;
      }
      return true;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    /**
     * next return the next object in the linkedlist.
     */
    public Object next() {
      if (current == null) {
        throw new Error(" No items in the list.");
      }
      Object temp = current.item;
      current = current.next;
      return temp;
    }
  }
}
