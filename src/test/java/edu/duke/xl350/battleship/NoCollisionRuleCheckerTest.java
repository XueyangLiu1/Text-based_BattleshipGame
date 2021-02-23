package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_noCollRule() {
    PlacementRuleChecker rc = new NoCollisionRuleChecker(null);
    Board<Character> b = new BattleShipBoard<Character>(10,26,rc,'X');
    V1ShipFactory f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement(new Coordinate(4,4),'H'));
    Ship<Character> s2 = f.makeSubmarine(new Placement(new Coordinate(4,3),'H'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(4,2),'H'));
    Ship<Character> s4 = f.makeSubmarine(new Placement(new Coordinate(3,4),'V'));
    Ship<Character> s5 = f.makeSubmarine(new Placement(new Coordinate(4,5),'V'));
    Ship<Character> s6 = f.makeBattleship(new Placement(new Coordinate(4,3),'H'));
    Ship<Character> s7 = f.makeBattleship(new Placement(new Coordinate(4,5),'H'));

    assertEquals(rc.checkPlacement(s1,b),null);
    b.tryAddShip(s1);
    assertEquals(rc.checkPlacement(s2,b),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(rc.checkPlacement(s3,b),null);
    assertEquals(rc.checkPlacement(s4,b),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(rc.checkPlacement(s5,b),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(rc.checkPlacement(s6,b),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(rc.checkPlacement(s7,b),"That placement is invalid: the ship overlaps another ship.");
  }
  @Test
  public void test_combined(){
    PlacementRuleChecker rc = new NoCollisionRuleChecker(new InBoundsRuleChecker(null));
    Board<Character> b = new BattleShipBoard<Character>(10,26,rc,'X');
    V1ShipFactory f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement(new Coordinate(4,4),'H'));
    Ship<Character> s2 = f.makeSubmarine(new Placement(new Coordinate(4,3),'H'));
    Ship<Character> s3 = f.makeBattleship(new Placement(new Coordinate(25,3),'V'));

    assertEquals(rc.checkPlacement(s1,b),null);
    b.tryAddShip(s1);
    assertEquals(rc.checkPlacement(s2,b),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(rc.checkPlacement(s3,b),"That placement is invalid: the ship goes off the bottom of the board.");

  }
}
