package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_constructor_getInfo() {
    SimpleShipDisplayInfo<Integer> ssdi = new SimpleShipDisplayInfo<Integer>(2,3);
    Coordinate c = new Coordinate(1,1); //just a coordinate placeholder to pass in, no real meaning
    assertEquals(ssdi.getInfo(c,true),3);
    assertEquals(ssdi.getInfo(c,false),2);
  }

}
