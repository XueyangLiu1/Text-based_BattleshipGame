package edu.duke.xl350.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {
	
	protected final HashMap<Coordinate, Boolean>  myPieces;
	// null means not part of the ship
	// false means not been hit
	// true means been hit
	protected ShipDisplayInfo<T> myDisplayInfo;
	protected ShipDisplayInfo<T> enemyDisplayInfo;
	protected Placement p;
	/**
	 * Constructs a BasicShip with the given Coordinates
	 * @param where contains the coordinates
	 * @param myDisplayInfo the display info for self board display
	 * @param enemyDisplayInfo the display info for enemy board display
	 */
	public BasicShip(Iterable<Coordinate> where,ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo, Placement p){
		this.myPieces = new HashMap<Coordinate, Boolean>();
		for(Coordinate c : where){
			this.myPieces.put(c, false);
		}
		this.myDisplayInfo = myDisplayInfo;
		this.enemyDisplayInfo = enemyDisplayInfo;
		this.p = p;
	}

	/**
	 * @param c check if the given coordinate is part of the ship
	 * @throws IllegalArgumentException if c is not part of the ship
	 * if true do nothing, else throw an exception
	 */
	protected void checkCoordinateInThisShip(Coordinate c){
		if(!occupiesCoordinates(c)){
			throw new IllegalArgumentException("Coordinate not a part of the ship!\n");
		}
	}

	/**
	 * @param where a coordinate
	 * check if the given coordinate is occupied
	 */
	@Override
	public boolean occupiesCoordinates(Coordinate where) {
		return this.myPieces.get(where)!=null;
	}


	/**
	 * check if the ship is sunk
	 */
	@Override
	public boolean isSunk() {
		for(Coordinate c: myPieces.keySet()){
			if(myPieces.get(c)==null || !myPieces.get(c)){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param where a coordinate
	 * mark the given coordinate as hit
	 */
	@Override
	public void recordHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		myPieces.put(where,true);
	}

	/**
	 * @param where a coordinate
	 * check if the given coordinate is hit
	 */
	@Override
	public boolean wasHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		return myPieces.get(where)==true;
	}

	/**
	 * @param where a coordinate
	 * @param myShip if the info needed is for myself or enemy
	 * get the display info of the given coordinate
	 */
	@Override
	public T getDisplayInfoAt(Coordinate where, boolean myShip) {
		checkCoordinateInThisShip(where);
		if(myShip){
			return myDisplayInfo.getInfo(where, wasHitAt(where));
		}else{
			return enemyDisplayInfo.getInfo(where, wasHitAt(where));
		}
	}
  
    /**
	 * Get all of the Coordinates that this Ship occupies.
	 * @return An Iterable with the coordinates that this Ship occupies
	 */
	public Iterable<Coordinate> getCoordinates(){
		return myPieces.keySet();
	}

}













