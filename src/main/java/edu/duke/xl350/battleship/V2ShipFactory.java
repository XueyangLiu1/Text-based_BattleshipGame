package edu.duke.xl350.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {

	/**
     * @param name the name of the ship
     * @param upperLefts the upperleft coordinate for a rectangle ship
     * @param widths the width of the rectangle
     * @param heights the height of the rectangle
	 * @param letter the display char
	 * create a SpecialShip, only for V2
	 */
	protected Ship<Character> createShip(String name, Coordinate[] upperLefts, 
                int[] widths, int[] heights, char letter, Placement p){
		return new SpecialShip<Character>(name,upperLefts,widths,heights,letter,'*',p);
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a special battleship
	 */
	@Override
	public Ship<Character> makeBattleship(Placement where) {
		char orientation = where.getOrientation();
        if(orientation!='U' && orientation!='R' && orientation!='D' && orientation!='L'){
            throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
        }
        int row = where.getCoordinate().getRow(), col = where.getCoordinate().getCol();
        Coordinate[] upperLefts = new Coordinate[2];
        int[] widths = new int[2], heights = new int[2];
        switch(orientation){
            case 'U':
                upperLefts[0] = new Coordinate(row,col+1);
                widths[0] = 1;
                heights[0] = 1;
                upperLefts[1] = new Coordinate(row+1,col);
                widths[1] = 3;
                heights[1] = 1;
                break;
            case 'R':
                upperLefts[0] = new Coordinate(row,col);
                widths[0] = 1;
                heights[0] = 3;
                upperLefts[1] = new Coordinate(row+1,col+1);
                widths[1] = 1;
                heights[1] = 1;
                break;
            case 'D':
                upperLefts[0] = new Coordinate(row,col);
                widths[0] = 3;
                heights[0] = 1;
                upperLefts[1] = new Coordinate(row+1,col+1);
                widths[1] = 1;
                heights[1] = 1;
                break;
            case 'L':
                upperLefts[0] = new Coordinate(row,col+1);
                widths[0] = 1;
                heights[0] = 3;
                upperLefts[1] = new Coordinate(row+1,col);
                widths[1] = 1;
                heights[1] = 1;
                break;
        }
        return createShip("Battleship",upperLefts,widths,heights,'b',where);
	}

	/**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a special carrier
	 */
	@Override
	public Ship<Character> makeCarrier(Placement where) {
        char orientation = where.getOrientation();
        if(orientation!='U' && orientation!='R' && orientation!='D' && orientation!='L'){
            throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
        }
        int row = where.getCoordinate().getRow(), col = where.getCoordinate().getCol();
        Coordinate[] upperLefts = new Coordinate[2];
        int[] widths = new int[2], heights = new int[2];
        switch(orientation){
            case 'U':
                upperLefts[0] = new Coordinate(row,col);
                widths[0] = 1;
                heights[0] = 3;
                upperLefts[1] = new Coordinate(row+2,col+1);
                widths[1] = 1;
                heights[1] = 3;
                break;
            case 'R':
                upperLefts[0] = new Coordinate(row+1,col);
                widths[0] = 3;
                heights[0] = 1;
                upperLefts[1] = new Coordinate(row,col+2);
                widths[1] = 3;
                heights[1] = 1;
                break;
            case 'D':
                upperLefts[0] = new Coordinate(row+2,col);
                widths[0] = 1;
                heights[0] = 3;
                upperLefts[1] = new Coordinate(row,col+1);
                widths[1] = 1;
                heights[1] = 3;
                break;
            case 'L':
                upperLefts[0] = new Coordinate(row,col);
                widths[0] = 3;
                heights[0] = 1;
                upperLefts[1] = new Coordinate(row+1,col+2);
                widths[1] = 3;
                heights[1] = 1;
                break;
        }
        return createShip("Carrier",upperLefts,widths,heights,'c',where);
	}

    /**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a submarine
	 */
	@Override
	public Ship<Character> makeSubmarine(Placement where) {
        char orientation = where.getOrientation();
        if(orientation!='H' && orientation!='V'){
            throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
        }
		V1ShipFactory factory = new V1ShipFactory();
        return factory.createShip(where,1,2,'s',"Submarine");
	}

    /**
	 * @param where the placement info, containing the coordinate and orientation
	 * create a destroyer
	 */
	@Override
	public Ship<Character> makeDestroyer(Placement where) {
        char orientation = where.getOrientation();
        if(orientation!='H' && orientation!='V'){
            throw new IllegalArgumentException("That placement is invalid: it does not have the correct format.");
        }
        V1ShipFactory factory = new V1ShipFactory();
		return factory.createShip(where,1,3,'d',"Destroyer");
	}
}