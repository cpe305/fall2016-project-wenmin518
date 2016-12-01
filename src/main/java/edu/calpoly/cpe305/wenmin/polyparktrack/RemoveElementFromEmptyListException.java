package main.java.edu.calpoly.cpe305.wenmin.polyparktrack;


@SuppressWarnings("serial")
public class RemoveElementFromEmptyListException extends Exception {
  public RemoveElementFromEmptyListException() {
    super("Can not remove an element from an empty list");
  }
}
