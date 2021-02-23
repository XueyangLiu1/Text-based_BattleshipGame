package edu.duke.xl350.battleship;

import java.io.EOFException;
import java.io.IOException;

public class Coordinate {

  private final int row;
  private final int col;

  /**
   * Constructs a Coordinate with the given description
   * @param row is the row number of the coordinate
   * @param col is the column number of the coordinate
   */
  public Coordinate(int row,int col){
    this.row = row;
    this.col = col;
  }

  /**
   * Constructs a Coordinate with the given description
   * @param descr is the description of the coordinate
   * which is a String with length 2
   * The first char in the String should be a letter in A-Z and the second should be a number in 0-9
   * @throws IllegalArgumentException if the length of the String is not 2
   * or the char in it does not satisfy the format requirements
   */
  public Coordinate(String descr) throws IOException{
    if(descr==null){
      throw new EOFException();
    }
    if (descr.length() != 2) {
      throw new IllegalArgumentException("Wrong syntax for coordinate!");
    }
    descr = descr.toUpperCase();
    char rowLetter = descr.charAt(0), colLetter = descr.charAt(1);
    if (rowLetter < 'A' || rowLetter > 'Z' || colLetter < '0' || colLetter > '9') {
      throw new IllegalArgumentException("Wrong syntax for coordinate!");
    }
    this.row = rowLetter - 'A';
    this.col = colLetter - '0';
  }
  /**
   * @return the int that is the row number of this coordinate
   */
  public int getRow(){
    return this.row;
  }

  /**
   * @return the int that is the column number of this coordinate
   */
  public int getCol() {
    return this.col;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && col == c.col;
    }
    return false;
  }

  @Override
  public String toString() {
    return "("+row+", " + col+")";
  
  }
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}













