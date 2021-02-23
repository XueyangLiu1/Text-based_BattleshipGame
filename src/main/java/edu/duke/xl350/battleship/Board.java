package edu.duke.xl350.battleship;

public interface Board<T> {
  
  /**
   * @return the int that is the width of the board
   */
  public int getWidth();

  /**
   * @return the int that is the height of the board
   */
  public int getHeight();

  /** 
   * Check if the ship can be added, then add it to the board or give up
   * @param toAdd is a ship
   * @return if the placement is valid, return null, else return the error message
   */
  public String tryAddShip(Ship<T> toAdd);

  /** 
   * Takes a Coordinate and see if any ship occupies that position
   * @param where is the position to check
   * @return the displayInfo for self board at that position or 
   * null if none was found
   */
  public T whatIsAtForSelf(Coordinate where);

  /** 
   * Takes a Coordinate and see if any ship occupies that position
   * @param where is the position to check
   * @return the displayInfo for enemy's board at that position or 
   * null if none was found
   */
  public T whatIsAtForEnemy(Coordinate where);

  /**
   * no matter whether the coordinate has been hit
   * as long as if there's a ship
   * @param where the center of the sonar
   * @return return the char for the ship
   */
  public T whatIsAtForSonar(Coordinate where);
  
  /**
   * fire at the given coordinate on the board
   * return the ship if hit
   * else record the miss and return null
   * @param c the coordinate to be fired at
   * @return the ship hit or null if miss
   */
  public Ship<T> fireAt(Coordinate c);

  /**
   * check if the ships on this board is all sunk
   * @return if all ships are sunk, yes, else no
   */
  public boolean allShipsSunk();

  /**
   * get the ship at the given coordinate
   * @param where the given coordinate
   * @return the ship there
   */
  public Ship<T> getShip(Coordinate where);

  /**
   * remove the given ship from myShips
   */
  public void removeShip(Ship<T> ship);
}


