package edu.duke.xl350.battleship;

/**
 * This class is used to map coordinates after re-placement
 */
public class SpecialShapeMapper {
    
    public Coordinate BattleshipShapeMapper(Placement p,Coordinate c, Placement newP){
        Coordinate[] U = new Coordinate[4],D = new Coordinate[4],L = new Coordinate[4],R = new Coordinate[4];
        U[0] = new Coordinate(0,1);
        U[1] = new Coordinate(1,0);
        U[2] = new Coordinate(1,1);
        U[3] = new Coordinate(1,2);

        D[0] = new Coordinate(1,1);
        D[1] = new Coordinate(0,2);
        D[2] = new Coordinate(0,1);
        D[3] = new Coordinate(0,0);

        R[0] = new Coordinate(1,1);
        R[1] = new Coordinate(0,0);
        R[2] = new Coordinate(1,0);
        R[3] = new Coordinate(2,0);

        L[0] = new Coordinate(1,0);
        L[1] = new Coordinate(2,1);
        L[2] = new Coordinate(1,1);
        L[3] = new Coordinate(0,1);

        Coordinate oldUpperLeft = p.getCoordinate();
        Coordinate relative = new Coordinate(c.getRow()-oldUpperLeft.getRow(),c.getCol()-oldUpperLeft.getCol());
        int index = 0;
        switch(p.getOrientation()){
            case 'R':
                index = SearchForIndex(R,relative);
                break;
            case 'L':
                index = SearchForIndex(L,relative);
                break;
            case 'D':
                index = SearchForIndex(D,relative);
                break;
            case 'U':
                index = SearchForIndex(U,relative);
                break;
            default:
                index = -1;
                break;
        }
        if(index==-1){
            throw new IllegalArgumentException();
        }
        Coordinate newC = newP.getCoordinate();
        Coordinate newRlative = null;
        switch(newP.getOrientation()){
            case 'R':
                newRlative = R[index];
                break;
            case 'L':
                newRlative = L[index];
                break;
            case 'D':
                newRlative = D[index];
                break;
            case 'U':
                newRlative = U[index];
                break;
        }
        return new Coordinate(newC.getRow()+newRlative.getRow(),newC.getCol()+newRlative.getCol());
    }


    public Coordinate CarrierShapeMapper(Placement p,Coordinate c, Placement newP){
        Coordinate[] U = new Coordinate[6],D = new Coordinate[6],L = new Coordinate[6],R = new Coordinate[6];
        U[0] = new Coordinate(0,0);
        U[1] = new Coordinate(1,0);
        U[2] = new Coordinate(2,0);
        U[3] = new Coordinate(2,1);
        U[4] = new Coordinate(3,1);
        U[5] = new Coordinate(4,1);

        D[0] = new Coordinate(4,0);
        D[1] = new Coordinate(3,0);
        D[2] = new Coordinate(2,0);
        D[3] = new Coordinate(2,1);
        D[4] = new Coordinate(1,1);
        D[5] = new Coordinate(0,1);

        R[0] = new Coordinate(1,0);
        R[1] = new Coordinate(1,1);
        R[2] = new Coordinate(1,2);
        R[3] = new Coordinate(0,2);
        R[4] = new Coordinate(0,3);
        R[5] = new Coordinate(0,4);

        L[0] = new Coordinate(1,4);
        L[1] = new Coordinate(1,3);
        L[2] = new Coordinate(1,2);
        L[3] = new Coordinate(0,2);
        L[4] = new Coordinate(0,1);
        L[5] = new Coordinate(0,0);

        Coordinate oldUpperLeft = p.getCoordinate();
        Coordinate relative = new Coordinate(c.getRow()-oldUpperLeft.getRow(),c.getCol()-oldUpperLeft.getCol());
        int index = 0;
        switch(p.getOrientation()){
            case 'R':
                index = SearchForIndex(R,relative);
                break;
            case 'L':
                index = SearchForIndex(L,relative);
                break;
            case 'D':
                index = SearchForIndex(D,relative);
                break;
            case 'U':
                index = SearchForIndex(U,relative);
                break;
            default:
                index = -1;
                break;
        }
        if(index==-1){
            throw new IllegalArgumentException();
        }
        Coordinate newC = newP.getCoordinate();
        Coordinate newRlative = null;
        switch(newP.getOrientation()){
            case 'R':
                newRlative = R[index];
                break;
            case 'L':
                newRlative = L[index];
                break;
            case 'D':
                newRlative = D[index];
                break;
            case 'U':
                newRlative = U[index];
                break;
        }
        return new Coordinate(newC.getRow()+newRlative.getRow(),newC.getCol()+newRlative.getCol());
    }

    public Coordinate DestroyerShapeMapper(Placement p,Coordinate c, Placement newP){
        Coordinate[] V = new Coordinate[3],H = new Coordinate[3];
        V[0] = new Coordinate(0,0);
        V[1] = new Coordinate(1,0);
        V[2] = new Coordinate(2,0);

        H[0] = new Coordinate(0,0);
        H[1] = new Coordinate(0,1);
        H[2] = new Coordinate(0,2);

        Coordinate oldUpperLeft = p.getCoordinate();
        Coordinate relative = new Coordinate(c.getRow()-oldUpperLeft.getRow(),c.getCol()-oldUpperLeft.getCol());
        int index = 0;
        switch(p.getOrientation()){
            case 'V':
                index = SearchForIndex(V,relative);
                break;
            case 'H':
                index = SearchForIndex(H,relative);
                break;
            default:
                index = -1;
                break;
        }
        if(index==-1){
            throw new IllegalArgumentException();
        }
        Coordinate newC = newP.getCoordinate();
        Coordinate newRlative = null;
        switch(newP.getOrientation()){
            case 'V':
                newRlative = V[index];
                break;
            case 'H':
                newRlative = H[index];
                break;
        }
        return new Coordinate(newC.getRow()+newRlative.getRow(),newC.getCol()+newRlative.getCol());
    }
    
    public Coordinate SubmarineShapeMapper(Placement p,Coordinate c, Placement newP){
        Coordinate[] V = new Coordinate[2],H = new Coordinate[2];
        V[0] = new Coordinate(0,0);
        V[1] = new Coordinate(1,0);

        H[0] = new Coordinate(0,0);
        H[1] = new Coordinate(0,1);

        Coordinate oldUpperLeft = p.getCoordinate();
        Coordinate relative = new Coordinate(c.getRow()-oldUpperLeft.getRow(),c.getCol()-oldUpperLeft.getCol());
        int index = 0;
        switch(p.getOrientation()){
            case 'V':
                index = SearchForIndex(V,relative);
                break;
            case 'H':
                index = SearchForIndex(H,relative);
                break;
            default:
                index = -1;
                break;
        }
        if(index==-1){
            throw new IllegalArgumentException();
        }
        Coordinate newC = newP.getCoordinate();
        Coordinate newRlative = null;
        switch(newP.getOrientation()){
            case 'V':
                newRlative = V[index];
                break;
            case 'H':
                newRlative = H[index];
                break;
        }
        return new Coordinate(newC.getRow()+newRlative.getRow(),newC.getCol()+newRlative.getCol());
    }

    public int SearchForIndex(Coordinate[] cs, Coordinate c){
        for(int i=0;i<cs.length;i++){
            if(cs[i].equals(c)){
                return i;
            }
        }
        return -1;
    }
}