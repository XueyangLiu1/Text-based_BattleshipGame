package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.io.IOException;

public class CoordinateTest {
  @Test
  public void test_constructor() {
    Coordinate c1 = new Coordinate(1, 2);
    assertEquals(c1.getRow(), 1);
    assertEquals(c1.getCol(), 2);
  }

  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(3, 2);
    assertEquals(c1, c1);   //equals should be reflexsive
    assertEquals(c1, c2);   //different objects bu same contents
    assertNotEquals(c1, c3);  //different contents
    assertNotEquals(c1, c4);
    assertNotEquals(c3, c4);
    assertNotEquals(c1, "(1, 2)"); //different types
  }

  @Test
  public void test_string() {
    Coordinate c1 = new Coordinate(1,2);
    assertEquals(c1.toString(),"(1, 2)");
  }

  @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);
    Coordinate c4 = new Coordinate(2, 1);
    assertEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c1.hashCode(), c3.hashCode());
    assertNotEquals(c1.hashCode(), c4.hashCode());
  }

  @Test
  void test_string_constructor_valid_cases() throws IOException{
    Coordinate c1 = new Coordinate("B3");
    assertEquals(1, c1.getRow());
    assertEquals(3, c1.getCol());
    Coordinate c2 = new Coordinate("d5");
    assertEquals(3, c2.getRow());
    assertEquals(5, c2.getCol());
    Coordinate c3 = new Coordinate("A9");
    assertEquals(0, c3.getRow());
    assertEquals(9, c3.getCol());
    Coordinate c4 = new Coordinate("z0");
    assertEquals(25, c4.getRow());
    assertEquals(0, c4.getCol());

  }
  @Test
  public void test_string_constructor_error_cases() throws IOException{
    assertThrows(EOFException.class, () -> new Coordinate(null));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate(""));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("00"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("AA"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("@0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("[0"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A/"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A:"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A"));
    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A12"));
  }
}












