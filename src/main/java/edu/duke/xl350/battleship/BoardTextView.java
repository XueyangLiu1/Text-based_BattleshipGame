package edu.duke.xl350.battleship;

import java.util.function.Function;
/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the 
 * enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;
  /**
   * Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.
   */
  public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * @return the String that represents self's boardTextView
   */
  public String displayMyOwnBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
  }

  /**
   * @return the String that represents enemy's boardTextView
   */
  public String displayEnemyBoard(){
    return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
  }
  
  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn){
    String header = this.makeHeader();
    StringBuilder contentBuilder = new StringBuilder();
    for(int h = 0; h < this.toDisplay.getHeight();h++){
      contentBuilder.append(this.makeLine(h,getSquareFn));
    }
    String content = contentBuilder.toString();
    return header+content+header;
  }

  /**
   * @param row is the number of the row, start from 0, max is 25
   * @return the String that represents a single line of the body of the boardTextView
   */
  private String makeLine(int row, Function<Coordinate, Character> getSquareFn){
    assert(row>=0 && row<=25);
    StringBuilder ans = new StringBuilder();
    char rowSymbol = (char)('A' + row);
    ans.append(rowSymbol);
    ans.append(" ");
    for(int col = 0; col < this.toDisplay.getWidth();col++){
      Character symbol = getSquareFn.apply(new Coordinate(row,col));
      symbol = symbol==null ? ' ':symbol;
      ans.append(symbol);
      if(col!=this.toDisplay.getWidth()-1){
        ans.append("|");
      }
    }
    ans.append(" ");
    ans.append(rowSymbol);
    ans.append("\n");
    return ans.toString();
  }
  
  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep=""; //start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
}













