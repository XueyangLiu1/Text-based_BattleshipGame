package edu.duke.xl350.battleship;

import java.util.HashSet;

public class SpecialShip<T> extends BasicShip<T>{
  /**
   * inherit from basic ship
   */
  private final String name;
  private final Placement upperLeft;

  /**
   * @param name the name of the ship
   * any special ship can be broken down to several rectangle ship
   * the upperleft, width, height with the same index represents a rectangle
   * @param upperLefts the upperleft coordinate for a rectangle ship
   * @param widths the width of the rectangle
   * @param heights the height of the rectangle
   * @param myDisplayInfo the display info for self board display
   * @param enemyDisplayInfo the display info for enemy board display
   * init the SpecialShip with the info
   */
  public SpecialShip(String name, Coordinate[] upperLefts, int[] widths, int[] heights,
                ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo, Placement p) {
    super(makeCoords(upperLefts, widths, heights),myDisplayInfo,enemyDisplayInfo,p);
    this.name = name;
    this.upperLeft = p;
  }

  /**
   * @param name the name of the ship
   * any special ship can be broken down to several rectangle ship
   * the upperleft, width, height with the same index represents a rectangle
   * @param upperLefts the upperleft coordinate for a rectangle ship
   * @param widths the width of the rectangle
   * @param heights the height of the rectangle
   * @param data the display strategy of normal state
   * @param onHit the display strategg of hit
   */
  public SpecialShip(String name, Coordinate[] upperLefts, int[] widths, int[] heights, T data, T onHit, Placement p) {
    this(name,upperLefts, widths, heights, new SimpleShipDisplayInfo<T>(data, onHit),new SimpleShipDisplayInfo<T>(null, data),p);
  }

  /**
   * Get the name of this Ship, such as "Battleship".
   * @return the name of this ship
   */
  public String getName(){
    return this.name;
  }

  /**
   * @param upperLefts the upperlefts coordinate for a rectangle
   * @param widths the widths of a rectangle
   * @param heights the heights of a rectangle
   * @return a set of coordinates of the area combined by a series of rectangles
   */
  static HashSet<Coordinate> makeCoords(Coordinate[] upperLefts, int[] widths, int[] heights){
    assert(upperLefts.length==widths.length && widths.length==heights.length);
    HashSet<Coordinate> set = new HashSet<Coordinate>();
    for(int i=0;i<upperLefts.length;i++){
        for(int w = 0;w<widths[i];w++){
            for(int h = 0;h<heights[i];h++){
                int row = upperLefts[i].getRow()+h;
                int col = upperLefts[i].getCol()+w;
                Coordinate curr = new Coordinate(row,col);
                set.add(curr);
            }
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
