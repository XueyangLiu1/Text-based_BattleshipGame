package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  @Test
  public void test_ships() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    Placement h3_4 = new Placement(new Coordinate(3,4),'H');
    Ship<Character> dst = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(h3_4);
    Ship<Character> submarine2 = f.makeSubmarine(h3_4);
    Ship<Character> submarine = f.makeSubmarine(v1_2);
    checkShip(dst, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
    checkShip(submarine, "Submarine", 's', new Coordinate(1, 2), new Coordinate(2, 2));
    checkShip(dst2, "Destroyer", 'd', new Coordinate(3, 4), new Coordinate(3, 5), new Coordinate(3, 6));
    checkShip(submarine2, "Submarine", 's', new Coordinate(3, 4), new Coordinate(3, 5));
    Placement r1_2 = new Placement(new Coordinate(1, 2), 'R');
    Placement d4_5 = new Placement(new Coordinate(4, 5), 'D');
    Placement l9_2 = new Placement(new Coordinate(9, 2), 'L');
    Placement u3_4 = new Placement(new Coordinate(3,4),'U');
    Ship<Character> carrier = f.makeCarrier(r1_2);
    Ship<Character> battleship = f.makeBattleship(r1_2);
    Ship<Character> carrier2 = f.makeCarrier(d4_5);
    Ship<Character> battleship2 = f.makeBattleship(d4_5);
    Ship<Character> carrier3 = f.makeCarrier(l9_2);
    Ship<Character> battleship3 = f.makeBattleship(l9_2);
    Ship<Character> carrier4 = f.makeCarrier(u3_4);
    Ship<Character> battleship4 = f.makeBattleship(u3_4);
    checkShip(battleship, "Battleship", 'b', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2),new Coordinate(2, 3));
    checkShip(battleship2, "Battleship", 'b', new Coordinate(4, 5), new Coordinate(4, 6), new Coordinate(4, 7), new Coordinate(5, 6));  
    checkShip(battleship3, "Battleship", 'b', new Coordinate(10, 2), new Coordinate(9, 3), new Coordinate(10, 3),new Coordinate(11, 3));
    checkShip(battleship4, "Battleship", 'b', new Coordinate(3, 5), new Coordinate(4, 4), new Coordinate(4, 5), new Coordinate(4, 6));
    
    checkShip(carrier, "Carrier", 'c', new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4),
                                      new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(1, 6));
    checkShip(carrier2, "Carrier", 'c', new Coordinate(4, 6), new Coordinate(5, 6), new Coordinate(6, 6),
                                      new Coordinate(6, 5), new Coordinate(7, 5), new Coordinate(8, 5));
    checkShip(carrier3, "Carrier", 'c', new Coordinate(9, 2), new Coordinate(9, 3), new Coordinate(9, 4),
                                      new Coordinate(10, 4), new Coordinate(10, 5), new Coordinate(10, 6));
    checkShip(carrier4, "Carrier", 'c', new Coordinate(3, 4), new Coordinate(4, 4), new Coordinate(5, 4),
                                      new Coordinate(5, 5), new Coordinate(6, 5), new Coordinate(7, 5));

    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(r1_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(d4_5));
    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(l9_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(u3_4));
    assertThrows(IllegalArgumentException.class, ()->f.makeSubmarine(r1_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeSubmarine(d4_5));
    assertThrows(IllegalArgumentException.class, ()->f.makeSubmarine(l9_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeSubmarine(u3_4));
    assertThrows(IllegalArgumentException.class, ()->f.makeCarrier(v1_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeBattleship(h3_4));
    assertThrows(IllegalArgumentException.class, ()->f.makeCarrier(v1_2));
    assertThrows(IllegalArgumentException.class, ()->f.makeBattleship(h3_4));

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

