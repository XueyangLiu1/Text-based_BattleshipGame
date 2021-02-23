package edu.duke.xl350.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
    /**
     * this class is to check if the ship to be placed is in the bound of the board
     */
    public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
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
            if(theBoard.whatIsAtForSelf(c)!=null){
                return "That placement is invalid: the ship overlaps another ship.";
            }
        }
        return null;
    }
}
