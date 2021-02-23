package edu.duke.xl350.battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class BattleShipBoard<T> implements Board<T>{

  private final int width;

  private final int height;

  final ArrayList<Ship<T>> myShips;

  private final PlacementRuleChecker<T> placementChecker;

  HashSet<Coordinate> enemyMisses;
  HashMap<Coordinate,T> enemyHits;

  final T missInfo;

  public BattleShipBoard(int w, int h,T missInfo) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)),missInfo);
  }
  /**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @param p is the rule checker for placing a ship
   * @param missInfo the missInfo for enemy's board
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */
  public BattleShipBoard(int w, int h,PlacementRuleChecker<T> p,T missInfo){
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = p;
    this.enemyMisses = new HashSet<Coordinate>();
    this.missInfo = missInfo;
    this.enemyHits = new HashMap<Coordinate,T>();
  }

  /**
   * @return the int that is the width of the board
   */
  public int getWidth(){
    return this.width;
  }

  /**
   * @return the int that is the height of the board
   */
  public int getHeight() {
    return this.height;
  }
  /** 
   * Check if the ship can be added, then add it to the board or give up
   * @param toAdd is a ship
   * @return if the placement is valid, return null, else return the error message
   */
  public String tryAddShip(Ship<T> toAdd){
    String possibleErr = this.placementChecker.checkPlacement(toAdd,this);
    if(possibleErr==null){
      myShips.add(toAdd);
    }
    return possibleErr;
  }
  /** 
   * Takes a Coordinate and see if any ship occupies that position
   * @param where is the position to check
   * @param isSelf true if the info wanted is for self board
   * false if it's for enemy's board
   * @return the displayInfo at that position or 
   * null if none was found
   */
  protected T whatIsAt(Coordinate where, boolean isSelf){
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where))
      {
        if(!isSelf && !enemyHits.containsKey(where) && s.wasHitAt(where)){
          return null;
        }
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    return null;
  }

  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where,true);
  }

  public T whatIsAtForEnemy(Coordinate where) {
    if(enemyMisses.contains(where)){
      return missInfo;
    }
    if(enemyHits.containsKey(where)){
      return enemyHits.get(where);
    }
    return whatIsAt(where, false);
  }
  /**
   * no matter whether the coordinate has been hit
   * as long as if there's a ship
   * @param where the center of the sonar
   * @return return the char for the ship
   */
  public T whatIsAtForSonar(Coordinate where){
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        if(s.wasHitAt(where)){
          return s.getDisplayInfoAt(where,false);
        }else{
          return s.getDisplayInfoAt(where,true);
        }
      }
    }
    return null;
  }
  /**
   * get the ship at the given coordinate
   * @param where the given coordinate
   * @return the ship there
   */
  public Ship<T> getShip(Coordinate where){
    for(Ship<T> s : myShips){
      if(s.occupiesCoordinates(where)){
        return s;
      }
    }
    return null;
  }

  /**
   * remove the given ship from myShips
   */
  public void removeShip(Ship<T> ship){
    myShips.remove(ship);
  }
  /**
   * fire at the given coordinate on the board
   * return the ship if hit
   * else record the miss and return null
   * @param c the coordinate to be fired at
   * @return the ship hit or null if miss
   */
  public Ship<T> fireAt(Coordinate c){
    for(Ship<T> s : myShips){
      if(s.occupiesCoordinates(c)){
        s.recordHitAt(c);
        enemyHits.put(c,s.getDisplayInfoAt(c,false));
        if(enemyMisses.contains(c)){
          enemyMisses.remove(c);
        }
        return s;
      }
    }
    enemyMisses.add(c);
    enemyHits.remove(c);
    return null;
  }
  /**
   * check if the ships on this board is all sunk
   * @return if all ships are sunk, yes, else no
   */
  public boolean allShipsSunk(){
    for(Ship<T> s : myShips){
      if(!s.isSunk()){
        return false;
      }
    }
    return true;
  }
}
