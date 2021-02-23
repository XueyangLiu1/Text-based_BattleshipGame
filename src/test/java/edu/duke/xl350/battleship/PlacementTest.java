package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.EOFException;
import java.io.IOException;

public class PlacementTest {
  @Test
  public void test_equals_hash() throws IOException{
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Placement p1 = new Placement(c1, 'v');
    Placement p2 = new Placement(c2, 'V');
    Placement p3 = new Placement(c1, 'V');
    Placement p4 = new Placement(c1, 'H');
    assertEquals(p1, p2);
    assertNotEquals(p2,p4);
    assertNotEquals(p1,c1);
    assertEquals(p1.hashCode(),p2.hashCode());
    assertNotEquals(p3.hashCode(),p4.hashCode());
    assertEquals(p1.hashCode(),p3.hashCode());
  }

  @Test
  public void test_constructors_and_getters() throws IOException{
    test_an_instance(1,2,'H');
    test_an_instance(1,2,'V');
    test_an_instance(1,2,'h');
    test_an_instance(1,2,'v');
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'a'));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'*'));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'0'));
    test_an_instance("B2H");
    test_an_instance("b2V");
    test_an_instance("B2h");
    test_an_instance("b2v");
    test_an_instance(1,2,'U');
    test_an_instance(1,2,'L');
    test_an_instance(1,2,'d');
    test_an_instance(1,2,'r');
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'a'));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'*'));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(1,2,'0'));
    test_an_instance("B2R");
    test_an_instance("b2D");
    test_an_instance("B2l");
    test_an_instance("b2u");
    String nullStr = null;
    assertThrows(EOFException.class, () -> test_an_instance(nullStr));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance(""));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance("b2a"));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance("B2*"));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance("B20"));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance("B20fds"));
    assertThrows(IllegalArgumentException.class, () -> test_an_instance("B2"));
  }

  private void test_an_instance(int row, int col, char orientation) throws IOException{
    Coordinate c1 = new Coordinate(row, col);
    Placement p1 = new Placement(c1,orientation);
    assertEquals(p1.getCoordinate(),c1);
    assertEquals(p1.toString(),"("+row+", " +col+", "+Character.toUpperCase(orientation)+")");
  }

  private void test_an_instance(String descr) throws IOException{
    Placement p1 = new Placement(descr);
    Coordinate c1 = new Coordinate(descr.substring(0,2));
    assertEquals(p1.getCoordinate(),c1);
    assertEquals(p1.toString(),"("+c1.getRow()+", " +c1.getCol()+", "+Character.toUpperCase(descr.charAt(2))+")");
  }
}
