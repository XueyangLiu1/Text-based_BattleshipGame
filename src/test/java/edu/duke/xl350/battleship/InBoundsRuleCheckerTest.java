package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_InBoundRule() {
    PlacementRuleChecker rc = new InBoundsRuleChecker(null);
    Board<Character> b = new BattleShipBoard<Character>(10,26,rc,'X');
    V1ShipFactory f = new V1ShipFactory();

    Ship<Character> s1 = f.makeBattleship(new Placement(new Coordinate(4,6),'H'));
    Ship<Character> s2 = f.makeBattleship(new Placement(new Coordinate(4,7),'H'));
    Ship<Character> s3 = f.makeBattleship(new Placement(new Coordinate(25,3),'V'));
    Ship<Character> s4 = f.makeBattleship(new Placement(new Coordinate(-1,4),'V'));
    Ship<Character> s5 = f.makeBattleship(new Placement(new Coordinate(4,-1),'H'));

    assertEquals(rc.checkPlacement(s1,b),null);
    assertEquals(rc.checkPlacement(s2,b),"That placement is invalid: the ship goes off the right of the board.");
    assertEquals(rc.checkPlacement(s3,b),"That placement is invalid: the ship goes off the bottom of the board.");
    assertEquals(rc.checkPlacement(s4,b),"That placement is invalid: the ship goes off the top of the board.");
    assertEquals(rc.checkPlacement(s5,b),"That placement is invalid: the ship goes off the left of the board.");
  }

}
