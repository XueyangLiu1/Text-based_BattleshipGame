package edu.duke.xl350.battleship;

public abstract class PlacementRuleChecker<T> {
    /**
     * A chain of rule checker
     */

    /**
     * the next rule to check
     */
    private final PlacementRuleChecker<T> next;

    /**
     * @param next the next rule to check
     */
    public PlacementRuleChecker(PlacementRuleChecker<T> next) {
        this.next = next;
    }
    /**
     * @param theShip the ship to be placed
     * @param theBoard the board where the ship to be placed
     * @return if error exists, return the error message, else return null 
     * check if the board and ship satisfies this rule
     */
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
    
    /**
     * @param theShip the ship to be placed
     * @param theBoard the board where the ship to be placed
     * @return if error exists, return the error message, else return null 
     * check if the board and ship satisfies this rule and its next
     */
    public String checkPlacement (Ship<T> theShip, Board<T> theBoard) {
        //if we fail our own rule: stop the placement is not legal
        String possibleError = checkMyRule(theShip, theBoard);
        if (possibleError!=null) {
            return possibleError;
        }
        //other wise, ask the rest of the chain.
        if (next != null) {
        return next.checkPlacement(theShip, theBoard); 
        }
        //if there are no more rules, then the placement is legal
        return null;
    }
}
