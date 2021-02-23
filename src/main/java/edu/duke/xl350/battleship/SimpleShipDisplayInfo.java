package edu.duke.xl350.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
	/**
	 * A simple display strategy under different situation: onHit, normal
	 */
	private final T myData;
	private final T onHit;

	/**
	 * @param data the display strategy of normal state
   	 * @param onHit the display strategg of hit
	 * init the object with given info
	 */
	public SimpleShipDisplayInfo(T data, T hit){
		this.myData = data;
		this.onHit = hit;
	}

	/**
	 * @param where the coordinate of the ship
   	 * @param hit whether the coordinate is hit or not
	 * @return return onHit if hit, return data if not
	 */
	@Override
	public T getInfo(Coordinate where, boolean hit) {
		// TODO Auto-generated method stub
		return hit ? this.onHit : this.myData;
	}

}
