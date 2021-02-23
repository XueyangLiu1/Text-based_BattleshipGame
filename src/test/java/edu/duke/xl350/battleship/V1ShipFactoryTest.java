package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {
  @Test
  public void test_ships() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> dst = f.makeDestroyer(v1_2);
    Ship<Character> carrier = f.makeCarrier(v1_2);
    Ship<Character> battleship = f.makeBattleship(v1_2);
    Ship<Character> submarine = f.makeSubmarine(v1_2);
    checkShip(dst, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
    checkShip(carrier, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),
                                      new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(6, 2));
    checkShip(battleship, "Battleship", 'b', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(4, 2));
    checkShip(submarine, "Submarine", 's', new Coordinate(1, 2), new Coordinate(2, 2));

    
    Placement h3_4 = new Placement(new Coordinate(3,4),'H');
    Ship<Character> dst2 = f.makeDestroyer(h3_4);
    Ship<Character> carrier2 = f.makeCarrier(h3_4);
    Ship<Character> battleship2 = f.makeBattleship(h3_4);
    Ship<Character> submarine2 = f.makeSubmarine(h3_4);
    checkShip(dst2, "Destroyer", 'd', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6));
    checkShip(carrier2, "Carrier", 'c', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6),
                                      new Coordinate(3, 7), new Coordinate(3, 8), new Coordinate(3, 9));
    checkShip(battleship2, "Battleship", 'b', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6), new Coordinate(3, 7));
    checkShip(submarine2, "Submarine", 's', new Coordinate(3, 4), new Coordinate(3, 5));  
  }
  private void checkShip(Ship<Character> testShip, String expectedName,
                          char expectedLetter, Coordinate... expectedLocs){
    assertEquals(testShip.getName(),expectedName);
    assertEquals(testShip.isSunk(),false);
    for(Coordinate curr : expectedLocs){
      assertEquals(testShip.wasHitAt(curr),false);
      assertEquals(testShip.getDisplayInfoAt(curr,true),expectedLetter);
      assertEquals(testShip.getDisplayInfoAt(curr,false),null);
      testShip.recordHitAt(curr);
      assertEquals(testShip.wasHitAt(curr),true);
      assertEquals(testShip.getDisplayInfoAt(curr,true),'*');
      assertEquals(testShip.getDisplayInfoAt(curr,false),expectedLetter);
    }
    assertEquals(testShip.isSunk(),true);
  }
}
