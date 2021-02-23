package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10,20,'X');
    assertEquals(10,b1.getWidth());
    assertEquals(20,b1.getHeight());
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20,'X'));
  }
  @Test
  public void test_whatIsAt() {
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10,20,'X');
    Character[][] cBoard = new Character[20][10];
    checkWhatIsAtBoard(b1,cBoard);
    addBasicShip(1,3,b1);
    addBasicShip(2,6,b1);
    addBasicShip(19,9,b1);
    addBasicShip(15,7,b1);
    cBoard[1][3] = 's';
    cBoard[2][6] = 's';
    cBoard[19][9] = 's';
    cBoard[15][7] = 's';
    checkWhatIsAtBoard(b1,cBoard);
  }
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    assert(expected.length>=1 && expected[0].length>=1);
    for(int i = 0; i < expected.length; i++){
      for(int j = 0; j<expected[0].length; j++){
        Coordinate c1 = new Coordinate(i,j);
        assertEquals(b.whatIsAtForSelf(c1),expected[i][j]);
      }
    }
  }
  private void addBasicShip(int row, int col, BattleShipBoard<Character> b){
    Coordinate c1 = new Coordinate(row,col);
    RectangleShip<Character> bs1 = new RectangleShip<Character>(c1, 's', '*');
    assertEquals(b.tryAddShip(bs1),null);
  }
  //@Disabled
  @Test
  public void test_fireAt(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10,20,'X');
    test_fireAt_helper(1,3,b1);
    test_fireAt_helper(0,0,b1);
    test_fireAt_helper(19,9,b1);
    test_fireAt_helper(10,5,b1);
    Coordinate c1 = new Coordinate(2,4);
    assertEquals(b1.whatIsAtForSelf(c1),null);
    assertEquals(b1.whatIsAtForEnemy(c1),null);
    assertEquals(b1.whatIsAtForSonar(c1),null);
    assertEquals(b1.fireAt(c1),null);
    assertEquals(b1.whatIsAtForSelf(c1),null);
    assertEquals(b1.whatIsAtForEnemy(c1),'X');
    assertEquals(b1.whatIsAtForSonar(c1),null);
    assertSame(b1.getShip(c1),null);
  }
  private void test_fireAt_helper(int row, int col, BattleShipBoard<Character> b){
    Coordinate c1 = new Coordinate(row,col);
    RectangleShip<Character> bs1 = new RectangleShip<Character>(c1, 's', '*');
    assertEquals(b.tryAddShip(bs1),null);
    assertEquals(bs1.wasHitAt(c1),false);
    assertEquals(b.whatIsAtForSelf(c1),'s');
    assertEquals(b.whatIsAtForEnemy(c1),null);
    assertEquals(b.whatIsAtForSonar(c1),'s');
    assertSame(b.fireAt(c1),bs1);
    assertSame(b.getShip(c1),bs1);
    assertEquals(bs1.wasHitAt(c1),true);
    assertEquals(b.whatIsAtForSelf(c1),'*');
    assertEquals(b.whatIsAtForEnemy(c1),'s');
    assertEquals(b.whatIsAtForSonar(c1),'s');
    b.removeShip(bs1);
    assertEquals(b.whatIsAtForSelf(c1),null);
    assertEquals(b.whatIsAtForEnemy(c1),'s');
    assertEquals(b.whatIsAtForSonar(c1),null);
    assertEquals(b.fireAt(c1),null);
    assertEquals(b.whatIsAtForSelf(c1),null);
    assertEquals(b.whatIsAtForEnemy(c1),'X');
    assertEquals(b.whatIsAtForSonar(c1),null);
    assertSame(b.getShip(c1),null);
  }

  @Test
  public void test_placementChecker(){
    PlacementRuleChecker rc = new NoCollisionRuleChecker(new InBoundsRuleChecker(null));
    Board<Character> b = new BattleShipBoard<Character>(10,26,rc,'X');
    V1ShipFactory f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement(new Coordinate(4,4),'H'));
    Ship<Character> s2 = f.makeSubmarine(new Placement(new Coordinate(4,3),'H'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(4,2),'H'));
    Ship<Character> s4 = f.makeBattleship(new Placement(new Coordinate(25,3),'V'));
    Ship<Character> s5 = f.makeBattleship(new Placement(new Coordinate(21,3),'V'));

    assertEquals(b.tryAddShip(s1),null);
    assertEquals(b.tryAddShip(s1),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(b.tryAddShip(s2),"That placement is invalid: the ship overlaps another ship.");
    assertEquals(b.tryAddShip(s3),null);
    assertEquals(b.tryAddShip(s4),"That placement is invalid: the ship goes off the bottom of the board.");
    assertEquals(b.tryAddShip(s5),null);
  }

}










