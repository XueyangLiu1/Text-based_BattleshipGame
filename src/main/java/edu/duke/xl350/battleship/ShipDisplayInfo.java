package edu.duke.xl350.battleship;

public interface ShipDisplayInfo<T> {
  /**
   * The display strategy under different situation: onHit, normal
   */
  public T getInfo(Coordinate where, boolean hit);
}
