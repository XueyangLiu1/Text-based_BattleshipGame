package edu.duke.xl350.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T>{
  /**
   * inherit from basic ship
   */
  private final String name;
  private final Placement p;

  /**
   * @param name the name of the ship
   * @param upperLeft the upperleft coordinate for a rectangle ship
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @param myDisplayInfo the display info for self board display
   * @param enemyDisplayInfo the display info for enemy board display
   * init the rectangleship with the info
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height,
                ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo,Placement p) {
    super(makeCoords(upperLeft, width, height),myDisplayInfo,enemyDisplayInfo,p);
    this.name = name;
    this.p = p;
  }

  /**
   * @param name the name of the ship
   * @param upperLeft the upperleft coordinate for a rectangle ship
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @param data the display strategy of normal state
   * @param onHit the display strategg of hit
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit,Placement p) {
    this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),new SimpleShipDisplayInfo<T>(null, data),p);
  }

  /**
   * @param upperLeft the upperleft coordinate for a rectangle ship
   * @param data the display strategy of normal state
   * @param onHit the display strategg of hit
   * init a 1x1 rectangle ship with given info and the name "testship""
   */
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit,new Placement(upperLeft,'H'));
  }

  /**
   * Get the name of this Ship, such as "submarine".
   * @return the name of this ship
   */
  public String getName(){
    return this.name;
  }

  /**
   * @param upperLeft the upperleft coordinate for a rectangle ship
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @return a set of coordinates of the rectangle area
   */
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
    HashSet<Coordinate> set = new HashSet<Coordinate>();
    for(int w = 0;w<width;w++){
      for(int h = 0;h<height;h++){
        int row = upperLeft.getRow()+h;
        int col = upperLeft.getCol()+w;
        Coordinate curr = new Coordinate(row,col);
        set.add(curr);
      }
    }
    return set;
  }
  /**
   * @return the placement info of the ship
   */
  public Placement getPlacement(){
    return this.p;
  }
}
