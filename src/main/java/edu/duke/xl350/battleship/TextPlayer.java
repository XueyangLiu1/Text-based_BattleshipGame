package edu.duke.xl350.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.EOFException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
    
    final Board<Character> theBoard;
    final BoardTextView view;
    final BufferedReader inputReader;
    final PrintStream out;
    final AbstractShipFactory<Character> shipFactory;
    final String name;

    final ArrayList<String> shipsToPlace;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
    private int moveRemains;
    private int sonarRemains;
    // public TextPlayer(){
    //     this.theBoard = new BattleShipBoard<Character>(10,20);
    //     this.view = new BoardTextView(this.theBoard);
    //     this.shipFactory = new V1ShipFactory();
    //     this.name = "Default name";
    //     this.out = System.out;
    //     this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    // }
    
    /**
     * @param name the player's name
     * @param theBoard initialize the board and board text view with this board
     * @param inputSource inputReader
     * @param out initialize printstream with out
     * @param factory the ship factory
     */
    public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out, AbstractShipFactory<Character> factory) {
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = inputSource;
        this.shipFactory = factory;
        this.out = out;
        this.name = name;
        this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
        this.shipsToPlace = new ArrayList<String>();
        setupShipCreationMap();
        setupShipCreationList();
        this.moveRemains = 3;
        this.sonarRemains = 3;
    }
    /**
     * set up the ship creation hashmap
     */
    protected void setupShipCreationMap(){
        shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
        shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
        shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
        shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    }
    /**
     * set up the list of ships to be created at the beginning of the game
     */
    protected void setupShipCreationList(){
        shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
        shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
        shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
        shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
    }
    /**
     * print a message to user, get an input, construct a placement object from the input
     * and return it
     * @param prompt is the message for user
     */
    public Placement readPlacement(String prompt) throws IOException {
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine);
        this.out.println(prompt);
        this.out.print(splitLine);
        String s = this.inputReader.readLine();
        if(s==null){
            throw new EOFException();
        }
        try{
            Placement p = new Placement(s);
            return p;
        }catch(IllegalArgumentException e){
            throw e;
        }
    }
    /**
     * print a message to user, get an input, try to add a ship to the board
     * and print the board text view after the attempt
     * @param shipName the name of the ship to be placed
     * @param createFn the function to be called over a Placement, 
     * which basically create different kinds of ships
     */
    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException{
        String splitLine = "--------------------------------------------------------------------------------\n";
        Placement p = null;
        Ship<Character> s  = null;
        while(p==null || s==null){
            try{
                p = readPlacement("Player "+this.name+" where do you want to place a "+shipName+"?");
                s = createFn.apply(p);
            }catch(IllegalArgumentException e){
                this.out.println("That placement is invalid: it does not have the correct format.");
            }
        }
        String errMessage = this.theBoard.tryAddShip(s);
        if(errMessage==null){
            this.out.print(splitLine+"Current ocean:\n"+this.view.displayMyOwnBoard()+splitLine);
            return;
        }else{
            this.out.println(errMessage);
            doOnePlacement(shipName,createFn);
        }
    }

    /**
     * display the starting (empty) board
     * print the instructions message
     * iterate throw the creation ship list
     * call doOnePlacement to place ships one by one
     */
    public void doPlacementPhase() throws IOException {
        this.out.print(this.view.displayMyOwnBoard());
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine+
        "Player "+this.name+": you are going to place the following ships (which are all\n"+
        "rectangular). For each ship, type the coordinate of the upper left\n"+
        "side of the ship, followed by either H (for horizontal) or V (for\n"+
        "vertical).  For example M4H would place a ship horizontally starting\n"+
        "at M4 and going to the right.  You have\n\n"+
        "2 \"Submarines\" ships that are 1x2 \n3 \"Destroyers\" that are 1x3\n"+
        "3 \"Battleships\" that are 1x4\n2 \"Carriers\" that are 1x6\n"+splitLine);
        for(String shipName: this.shipsToPlace){
            doOnePlacement(shipName,shipCreationFns.get(shipName));
        }
    }

    /**
     * get the text display for two boards together
     * @param enemyView enemy's textboardview
     * @param myHeader the header over my board
     * @param enemyHeader the header over enemy's board
     * @return the string of text display
     */
    public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader){
        String myBoard = this.view.displayMyOwnBoard();
        int myBoardW = this.theBoard.getWidth();
        String enemyBoard = enemyView.displayEnemyBoard();
        String[] myLines = myBoard.split("\n");
        String[] enemyLines = enemyBoard.split("\n");
        String splitLine = "--------------------------------------------------------------------------------\n";
        String headerLine = "     "+myHeader;
        while(headerLine.length()<2*myBoardW+22){
            headerLine+=" ";
        }
        headerLine=headerLine+enemyHeader+"\n";
        assert(myLines.length==enemyLines.length);
        StringBuilder sb = new StringBuilder();
        sb.append(splitLine);
        sb.append("Player "+this.name+"'s turn:\n");
        sb.append(headerLine);
        for(int i = 0;i<myLines.length;i++){
            String currLine = myLines[i];
            while(currLine.length()<2*myBoardW+19){
                currLine+=" ";
            }
            currLine+=enemyLines[i]+"\n";
            sb.append(currLine);
        }
        sb.append(splitLine);
        return sb.toString();
    }
    /**
     * check if this player loses by checking all his ships
     * if all ships are sunk, yes, else, no
     * @return a boolean respresenting if the player loses or not
     */
    public boolean ifLost(){
        return this.theBoard.allShipsSunk();
    }
    /**
     * print that this player has won the game
     */
    public void printWin(){
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine+"Player "+this.name+" won!\n"+splitLine);
    }
    /**
     * print a message to user, get an input, construct a coordinate object from the input
     * and return it
     * @param prompt is the message for user
     */
    protected Coordinate readCoordinate(String prompt) throws IOException{
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine);
        this.out.println(prompt);
        this.out.print(splitLine);
        String s = this.inputReader.readLine();
        if(s==null){
            throw new EOFException();
        }
        try{
            Coordinate c = new Coordinate(s);
            return c;
        }catch(IllegalArgumentException e){
            throw e;
        }
    }
    /**
     * print the message after attack
     * @param enemyShip is the enemy's ship been hit
     */
    protected void fireMsg(Ship enemyShip){
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine);
        if(enemyShip==null){
            this.out.println("You missed!");
        }else{
            this.out.println("You hit a "+enemyShip.getName()+"!");
        }
        this.out.print(splitLine);
    }

    /**
     * read the move from player
     */
    protected String readAction() throws IOException{
        String splitLine = "--------------------------------------------------------------------------------\n";
        this.out.print(splitLine+"Possible actions for Player "+this.name+":\n\n"+" F Fire at a square\n");
        if(this.moveRemains>0){
            this.out.print(" M Move a ship to another square ("+this.moveRemains+" remaining)\n");
        }
        if(this.sonarRemains>0){
            this.out.print(" S Sonar scan ("+this.sonarRemains+" remaining)\n");
        }
        this.out.print("Player "+this.name+", what would you like to do?\n"+splitLine);
        String s = this.inputReader.readLine();
        if(s==null){
            throw new EOFException();
        }
        s = s.toUpperCase();
        if(s.length()==1 &&(s.charAt(0)=='F'||(s.charAt(0)=='M' && this.moveRemains>0)||(s.charAt(0)=='S' && this.sonarRemains>0))){
            return s;
        }else{
            throw new IllegalArgumentException();
        }
    } 

    /**
     * let the player attack for one turn and print the msg
     * @param enemyBoard the enemy's board
     * @param enemyView the enemy's boardTextView
     * @param enemyName the enemy player's name
     */
    public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyView, String enemyName)  throws IOException{
        this.out.print(this.displayMyBoardWithEnemyNextToIt(enemyView,"Your ocean","Player "+enemyName+"'s ocean"));
        boolean valid = false;
        while(!valid){
            String move = null;
            if(this.moveRemains!=0 || this.sonarRemains!=0){
                while(move==null){
                    try{
                        move = readAction();
                    }catch(IllegalArgumentException e){
                        this.out.println("The input is invalid. Please try again.");
                    }
                }
            }else{
                move = "F";
            }

            assert(move.length()==1 && (move.charAt(0)=='F'||move.charAt(0)=='M'||move.charAt(0)=='S'));
            String prompt = null;
            switch(move.charAt(0)){
                case 'F':
                    prompt = "Where do you want to fire at?\n";
                    break;
                case 'M':
                    prompt = "Which ship do you want to move? Input any coordinate on that ship!\n";
                    break;
                case 'S':
                    prompt = "Where do you want to use the sonar?\n";
                break;
            }

            Coordinate c = null;
            try{
                c = readCoordinate(prompt);
            }catch(IllegalArgumentException e){
                this.out.println("That coordinate is invalid: it does not have the correct format. Please try again.");
                continue;
            }
            if(c.getRow()+1>enemyBoard.getHeight()||c.getCol()+1>enemyBoard.getWidth()){
                this.out.println("That coordinate is invalid: it is out of the board. Please try again.");
                continue;
            }

            switch(move.charAt(0)){
                case 'F':
                    Ship<Character> enemyShip = enemyBoard.fireAt(c);
                    this.fireMsg(enemyShip);
                    valid = true;
                    break;
                case 'M':
                    try{
                        moveShip(c);
                        valid = true;
                        this.moveRemains-=1;
                    }catch(IllegalArgumentException e){
                        this.out.println("Your input is invalid. Please try again.");
                    }
                    break;
                case 'S':
                    useSonar(enemyBoard,c);
                    this.sonarRemains-=1;
                    valid = true;
                    break;
            }
        }
        
    }
    
    protected void moveShip(Coordinate c)throws IOException{
        Ship<Character> s = theBoard.getShip(c);
        if(s==null){
            throw new IllegalArgumentException();
        }else{
            Placement p = null;
            Ship<Character> newShip = null;
            try{
                p = readPlacement("Where do you want to move the "+s.getName()+" to?");
                newShip = shipCreationFns.get(s.getName()).apply(p);
            }catch(IllegalArgumentException e){
                throw e;
            }
            theBoard.removeShip(s);
            String errMessage = this.theBoard.tryAddShip(newShip);
            if(errMessage!=null){
                this.theBoard.tryAddShip(s);
                throw new IllegalArgumentException();
            }else{
                SpecialShapeMapper ssm = new SpecialShapeMapper();
                for(Coordinate oldC : s.getCoordinates()){
                    if(s.wasHitAt(oldC)){
                        Placement oldP = s.getPlacement(),newP = newShip.getPlacement();
                        Coordinate newC = null;
                        String shipName = s.getName();
                        switch(shipName.toUpperCase().charAt(0)){
                            case 'S':
                                newC = ssm.SubmarineShapeMapper(oldP,oldC,newP);
                                break;
                            case 'D':
                                newC = ssm.DestroyerShapeMapper(oldP,oldC,newP);
                                break;
                            case 'B':
                                newC = ssm.BattleshipShapeMapper(oldP,oldC,newP);
                                break;
                            case 'C':
                                newC = ssm.CarrierShapeMapper(oldP,oldC,newP);
                                break;
                        }
                        newShip.recordHitAt(newC);
                    }
                }
                this.out.print("Your "+newShip.getName()+" has been moved.\n");
            }
        }
    }

    protected void useSonar(Board<Character> enemyBoard, Coordinate where){
        HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
        for(int row = where.getRow()-3;row<where.getRow()+4;row++){
            int colStart = Math.abs(row-where.getRow())-3;
            for(int col = where.getCol()+colStart; col < where.getCol()-colStart+1; col++){
                Character c = enemyBoard.whatIsAtForSonar(new Coordinate(row,col));
                if(c!=null){
                    freq.put(c,freq.getOrDefault(c,0)+1);
                }
            }
        }
        String splitLine = "--------------------------------------------------------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(splitLine);
        int freqS = freq.getOrDefault('s',0), freqD = freq.getOrDefault('d',0), freqB = freq.getOrDefault('b',0), freqC=freq.getOrDefault('c',0);
        sb.append("Submarines occupy "+freqS+" square");
        if(freqS>0){
            sb.append("s\n");
        }else{
            sb.append("\n");
        }
        sb.append("Destroyers occupy "+freqD+" square");
        if(freqD>0){
            sb.append("s\n");
        }else{
            sb.append("\n");
        }
        sb.append("Battleships occupy "+freqB+" square");
        if(freqB>0){
            sb.append("s\n");
        }else{
            sb.append("\n");
        }
        sb.append("Carriers occupy "+freqC+" square");
        if(freqC>0){
            sb.append("s\n");
        }else{
            sb.append("\n");
        }
        sb.append(splitLine);
        this.out.print(sb.toString());
    }

}
