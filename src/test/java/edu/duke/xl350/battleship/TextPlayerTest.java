package edu.duke.xl350.battleship;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  @Test
  void test_read_placement() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
    String splitLine = "--------------------------------------------------------------------------------\n";
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
        Placement p = player.readPlacement(prompt);
        assertEquals(p, expected[i]); //did we get the right Placement back
        assertEquals(splitLine+prompt+"\n"+splitLine, bytes.toString()); //should have printed prompt and newline
        bytes.reset(); //clear out bytes for next time around
    }
    assertThrows(EOFException.class, ()->player.readPlacement(prompt));
  }

  @Test
  void test_do_one_placement() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 4, "a0V\nD0H\n", bytes);
    String splitLine = "--------------------------------------------------------------------------------\n";
    V1ShipFactory shipFactory = new V1ShipFactory();
    HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    String[] expected = new String[2];
    expected[0] = "  0|1|2\n"+"A d| |  A\n"+"B d| |  B\n"+"C d| |  C\n"+"D  | |  D\n"+"  0|1|2\n";
    expected[1] = "  0|1|2\n"+"A d| |  A\n"+"B d| |  B\n"+"C d| |  C\n"+"D d|d|d D\n"+"  0|1|2\n";
        
    for(int i=0;i<expected.length;i++){
      player.doOnePlacement("Destroyer",shipCreationFns.get("Destroyer"));
        assertEquals(splitLine+"Player TestPlayer where do you want to place a Destroyer?\n"+splitLine+
                  splitLine+"Current ocean:\n"+expected[i]+splitLine, bytes.toString());
        bytes.reset();
    }
  }

  // @Test
  // void test_doPlacementPhase() throws IOException{
  //   ByteArrayOutputStream bytes = new ByteArrayOutputStream();
  //   TextPlayer player = createTextPlayer(3, 4, "a0V\nD0H\n", bytes);
  //   String instruction = "--------------------------------------------------------------------------------\n"+
  //     "Player "+player.name+": you are going to place the following ships (which are all\n"+
  //     "rectangular). For each ship, type the coordinate of the upper left\n"+
  //     "side of the ship, followed by either H (for horizontal) or V (for\n"+
  //     "vertical).  For example M4H would place a ship horizontally starting\n"+
  //     "at M4 and going to the right.  You have\n\n"+
  //     "2 \"Submarines\" ships that are 1x2 \n3 \"Destroyers\" that are 1x3\n"+
  //     "3 \"Battleships\" that are 1x4\n2 \"Carriers\" that are 1x6\n"+
  //     "--------------------------------------------------------------------------------\n";
  //   String[] expected = new String[3];
  //   expected[0] = "  0|1|2\n"+"A  | |  A\n"+"B  | |  B\n"+"C  | |  C\n"+"D  | |  D\n"+"  0|1|2\n";
  //   expected[1] = "  0|1|2\n"+"A d| |  A\n"+"B d| |  B\n"+"C d| |  C\n"+"D  | |  D\n"+"  0|1|2\n";
  //   expected[2] = "  0|1|2\n"+"A d| |  A\n"+"B d| |  B\n"+"C d| |  C\n"+"D d|d|d D\n"+"  0|1|2\n";
    
  //   for(int i=1;i<expected.length;i++){
  //       player.doPlacementPhase();
  //       assertEquals(expected[i-1]+instruction+"Player TestPlayer where would you like to put your ship?\n"+expected[i], bytes.toString());
  //       bytes.reset();
  //   }
    
  // }


  private TextPlayer createTextPlayer(int w, int h, String inputData, ByteArrayOutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("TestPlayer", board, input, output, shipFactory);
  }
  @Test
  public void test_displayBothBoards_and_lose() throws IOException{
    String ans = 
    "--------------------------------------------------------------------------------\n"+
    "Player TestPlayer's turn:\n"+
    "     Your ocean                           Player B's ocean\n"+
    "  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9\n"+
    "A  | | | | | | | | |  A                A  | | | | | | | | |  A\n"+
    "B  | | | | | | | | |  B                B  | | | | | | | | |  B\n"+
    "C  | | | | | | | | |  C                C  | | | | | | | | |  C\n"+
    "  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9\n"+
    "--------------------------------------------------------------------------------\n";

    TextPlayer player = createTextPlayer(10,3,"a0v\n",new ByteArrayOutputStream());
    Board<Character> enemy = new BattleShipBoard<Character>(10,3,'X');
    BoardTextView enemyView = new BoardTextView(enemy);
    String display = player.displayMyBoardWithEnemyNextToIt(enemyView,"Your ocean","Player B's ocean");
    assertEquals(display,ans);
    V1ShipFactory shipFactory = new V1ShipFactory();
    HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    player.doOnePlacement("Destroyer",shipCreationFns.get("Destroyer"));
    ans = 
    "--------------------------------------------------------------------------------\n"+
    "Player TestPlayer's turn:\n"+
    "     Your ocean                           Player B's ocean\n"+
    "  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9\n"+
    "A d| | | | | | | | |  A                A  | | | | | | | | |  A\n"+
    "B d| | | | | | | | |  B                B  | | | | | | | | |  B\n"+
    "C d| | | | | | | | |  C                C  | | | | | | | | |  C\n"+
    "  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9\n"+
    "--------------------------------------------------------------------------------\n";
    display = player.displayMyBoardWithEnemyNextToIt(enemyView,"Your ocean","Player B's ocean");
    assertEquals(display,ans);
    assertFalse(player.ifLost());
    player.theBoard.fireAt(new Coordinate(0,0));
    assertFalse(player.ifLost());
    player.theBoard.fireAt(new Coordinate(1,0));
    assertFalse(player.ifLost());
    player.theBoard.fireAt(new Coordinate(2,0));
    assertTrue(player.ifLost());
  }
  @Test
  public void test_readCoordinate() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2\nC8\na4\n\n", bytes);
    String splitLine = "--------------------------------------------------------------------------------\n";
    String prompt = "Please enter a location for a coordinate";
    Coordinate[] expected = new Coordinate[3];
    expected[0] = new Coordinate(1, 2);
    expected[1] = new Coordinate(2, 8);
    expected[2] = new Coordinate(0, 4);

    for (int i = 0; i < expected.length; i++) {
        Coordinate c = player.readCoordinate(prompt);
        assertEquals(c, expected[i]); //did we get the right Coordinate back
        assertEquals(splitLine+prompt+"\n"+splitLine, bytes.toString()); //should have printed prompt and newline
        bytes.reset(); //clear out bytes for next time around
    }
    assertThrows(IllegalArgumentException.class, ()->player.readCoordinate(prompt));
    assertThrows(EOFException.class, ()->player.readCoordinate(prompt));
  }
  @Test
  public void test_fireMsg_printWin(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    String splitLine = "--------------------------------------------------------------------------------\n";
    Ship s1 = new RectangleShip(new Coordinate(1,2),'s','*');
    player.fireMsg(s1);
    assertEquals(splitLine+"You hit a testship!\n"+splitLine, bytes.toString());
    bytes.reset(); 
    player.fireMsg(null);
    assertEquals(splitLine+"You missed!\n"+splitLine, bytes.toString());
    bytes.reset();
    player.printWin();
    assertEquals(splitLine+"Player TestPlayer won!\n"+splitLine, bytes.toString());
    bytes.reset();
  }
}
