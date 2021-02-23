package edu.duke.xl350.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T>{
    /**
     * this class is to check if the ship to be placed is in the bound of the board
     */
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
    /**
     * @param theShip the ship to be placed
     * @param theBoard the board where the ship to be placed
     * @return if error exists, return the error message, else return null 
     * check if the board and ship satisfies this rule
     */
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        for(Coordinate c: theShip.getCoordinates()){
            int ShipRow = c.getRow(),shipCol = c.getCol(),
                height = theBoard.getHeight(),width = theBoard.getWidth();
            if( ShipRow < 0){
                return "That placement is invalid: the ship goes off the top of the board.";
            }else if(ShipRow>=height){
                return "That placement is invalid: the ship goes off the bottom of the board.";
            }else if(shipCol<0){
                return "That placement is invalid: the ship goes off the left of the board.";
            }else if(shipCol>=width){
                return "That placement is invalid: the ship goes off the right of the board.";
            }
        }
        return null;
    }
}
