package edu.duke.xl350.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * @param w width of the ship
	 * @param h height of the ship
	 * @param letter the display char
	 * @param name the name of the ship
	 * create a rectangleship, only for V1
	 */
	protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
		char orientation = where.getOrientation();
		assert(orientation=='V' || orientation=='H');
		if(orientation=='V'){
			return new RectangleShip<Character>(name,where.getCoordinate(),w,h,letter,'*',where);
		}else{
			return new RectangleShip<Character>(name,where.getCoordinate(),h,w,letter,'*',where);
		}
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a submarine
	 */
	@Override
	public Ship<Character> makeSubmarine(Placement where) {
		return createShip(where,1,2,'s',"Submarine");
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a battleship
	 */
	@Override
	public Ship<Character> makeBattleship(Placement where) {
		return createShip(where,1,4,'b',"Battleship");
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a carrier
	 */
	@Override
	public Ship<Character> makeCarrier(Placement where) {
		return createShip(where,1,6,'c',"Carrier");
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a destroyer
	 */
	@Override
	public Ship<Character> makeDestroyer(Placement where) {
		return createShip(where,1,3,'d',"Destroyer");
	}

}
