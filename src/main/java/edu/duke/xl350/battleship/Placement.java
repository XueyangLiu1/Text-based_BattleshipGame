package edu.duke.xl350.battleship;

import java.io.EOFException;
import java.io.IOException;

public class Placement {
  /**
   * The placement of ships
   */
  private final Coordinate coordinate;
  private final char orientation;
  
  /**
   * Initialize the placement obj
   * @param coordinate is the position marker
   * @param orientation is how the ship been placed
   */
  public Placement(Coordinate coordinate, char orientation){
    this.coordinate = new Coordinate(coordinate.getRow(), coordinate.getCol());
    orientation = Character.toUpperCase(orientation);
    if(orientation!='H' && orientation!='V' && orientation!='U' && orientation!='R' && orientation!='D' && orientation!='L'){
      throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
    }
    this.orientation = orientation;
  }

  /**
   * Initialize the placement obj
   * @param descr is the text description for placement such as "A0V"
   * @throws IllegalArgumentException if the syntax for placement descr is wrong 
   */
  public Placement(String descr) throws IOException{
    if(descr==null){
      throw new EOFException();
    }
    descr = descr.toUpperCase();
    if(descr.length()!=3){
      throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
    }
    this.coordinate = new Coordinate(descr.substring(0,2));
    char orientation = descr.charAt(2);
    if(orientation!='H' && orientation!='V' && orientation!='U' && orientation!='R' && orientation!='D' && orientation!='L'){
      throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
    }
    this.orientation = orientation;
  }

  /**
   * @return the Coordinate that is the coordinate of the placement
   */
  public Coordinate getCoordinate(){
    return this.coordinate;
  }

  /**
   * @return the char that is the orientation of the placement
   */
  public char getOrientation(){
    return this.orientation;
  }

  @Override
  public boolean equals(Object o){
    if(o.getClass().equals(this.getClass())){
      Placement p = (Placement) o;
      return p.getCoordinate().getRow()==this.coordinate.getRow() 
      && p.getCoordinate().getCol()==this.coordinate.getCol() && 
      p.getOrientation() == this.orientation;
    }
    return false;
  }

  @Override
  public String toString() {
    return "("+this.coordinate.getRow()+", " + this.coordinate.getCol()+", "+this.orientation+")";
  
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}












