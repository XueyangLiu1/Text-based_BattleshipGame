package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    String expectedBody="A  |  A\n"+"B  |  B\n";
    String expectedHeader= "  0|1\n";
    BoardHelper(2, 2, expectedHeader, expectedBody);
  }
  
  @Test
  public void test_invalid_dimensions() {
    Board<Character> b1 = new BattleShipBoard<Character>(11,26,'X');
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(b1));
    Board<Character> b2 = new BattleShipBoard<Character>(10,27,'X');
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(b2));
  }
  @Test
  public void test_display_empty_3by2() {
    String expectedBody="A  | |  A\n"+"B  | |  B\n";
    String expectedHeader= "  0|1|2\n";
    BoardHelper(3, 2, expectedHeader, expectedBody);
  }

  @Test
  public void test_display_empty_2by3() {
    String expectedBody = "A  |  A\n" + "B  |  B\n" + "C  |  C\n";
    String expectedHeader= "  0|1\n";
    BoardHelper(2, 3, expectedHeader, expectedBody);
  }
  @Test
  public void test_3by4(){
    Board<Character> b1 = new BattleShipBoard<Character>(3,4,'X');
    String expectedHeader = "  0|1|2\n";
    Coordinate c1 = new Coordinate(0,0);
    RectangleShip<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    assertEquals(b1.tryAddShip(s1),null);
    String expectedBody1 = "A s| |  A\n"+"B  | |  B\n"+"C  | |  C\n"+"D  | |  D\n";
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader+expectedBody1+expectedHeader,view.displayMyOwnBoard());
    Coordinate c2 = new Coordinate(2,1);
    RectangleShip<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
    assertEquals(b1.tryAddShip(s2),null);
    String expectedBody2 = "A s| |  A\n"+"B  | |  B\n"+"C  |s|  C\n"+"D  | |  D\n";
    assertEquals(expectedHeader+expectedBody2+expectedHeader,view.displayMyOwnBoard());
    Coordinate c3 = new Coordinate(2,0);
    RectangleShip<Character> s3 = new RectangleShip<Character>(c3, 's', '*');
    assertEquals(b1.tryAddShip(s3),null);
    String expectedBody3 = "A s| |  A\n"+"B  | |  B\n"+"C s|s|  C\n"+"D  | |  D\n";
    assertEquals(expectedHeader+expectedBody3+expectedHeader,view.displayMyOwnBoard());
  }

  private void BoardHelper(int w, int h, String expectedHeader, String expectedBody){
    Board<Character> b1 = new BattleShipBoard<Character>(w, h,'X');
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_enemyBoard(){
    Board<Character> b = new BattleShipBoard<Character>(4,3,'X');
    BoardTextView view = new BoardTextView(b);
    String myView_empty =
      "  0|1|2|3\n" +
      "A  | | |  A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(myView_empty,view.displayMyOwnBoard());
    assertEquals(myView_empty,view.displayEnemyBoard());
    V1ShipFactory factory = new V1ShipFactory();
    Ship s1 = factory.makeSubmarine(new Placement(new Coordinate(0,0),'V'));
    assertEquals(b.tryAddShip(s1),null);
    String myView =
      "  0|1|2|3\n" +
      "A s| | |  A\n" +
      "B s| | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(myView,view.displayMyOwnBoard());
    assertEquals(myView_empty,view.displayEnemyBoard());
    b.fireAt(new Coordinate(0,0));
    String myViewHit =
      "  0|1|2|3\n" +
      "A *| | |  A\n" +
      "B s| | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    String enemyViewHit1 =
      "  0|1|2|3\n" +
      "A s| | |  A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(myViewHit,view.displayMyOwnBoard());
    assertEquals(enemyViewHit1,view.displayEnemyBoard());
    b.fireAt(new Coordinate(0,1));
    String enemyViewHit2 =
      "  0|1|2|3\n" +
      "A s|X| |  A\n" +
      "B  | | |  B\n" +
      "C  | | |  C\n" +
      "  0|1|2|3\n";
    assertEquals(myViewHit,view.displayMyOwnBoard());
    assertEquals(enemyViewHit2,view.displayEnemyBoard());
  }

}












