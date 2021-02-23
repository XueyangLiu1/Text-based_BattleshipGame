package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class SpecialShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate[] cs = new Coordinate[2];
    cs[0] = new Coordinate(4,9);
    cs[1] = new Coordinate(2,3);
    int[] ws = new int[2], hs = new int[2];
    ws[0] = 4;
    ws[1] = 7;
    hs[0] = 9;
    hs[1] = 10;
    test_makeCoord_helper(cs,ws,hs);
  }

  private void test_makeCoord_helper(Coordinate[] cs,int[] ws,int[] hs){
    HashSet<Coordinate> hset = SpecialShip.makeCoords(cs,ws,hs);
    for(int i = 0; i < cs.length;i++){
      for(int row = cs[i].getRow();row<hs[i]+cs[i].getRow();row++){
        for(int col = cs[i].getCol();col<ws[i]+cs[i].getCol();col++){
          Coordinate curr = new Coordinate(row,col);
          assertEquals(hset.contains(curr),true);
        }
      }
    }
  }

  @Test
  public void test_constructor_fourmethods(){
    Coordinate[] cs = new Coordinate[2];
    cs[0] = new Coordinate(9,3);
    cs[1] = new Coordinate(10,4);
    int[] ws = new int[2], hs = new int[2];
    ws[0] = 3;
    ws[1] = 1;
    hs[0] = 1;
    hs[1] = 1;
    //ship shape: sss
    //             s
    SpecialShip<Character> rs1 = new SpecialShip<Character>("Submarine",cs,ws,hs, 's', '*',new Placement(cs[0],'R'));
    assertEquals(rs1.getName(),"Submarine");
    test_occupy(rs1,cs,ws,hs);
    test_fourMethods_helper(rs1,cs,ws,hs);
    int squareNum = 0;
    for(Coordinate c: rs1.getCoordinates()){
      assertEquals(rs1.occupiesCoordinates(c),true);
      squareNum++;
    }
    assertEquals(squareNum,4);
  }

  private void test_occupy(SpecialShip rs,Coordinate[] cs, int[] ws, int[] hs){
    for(int i = 0; i < cs.length;i++){
      for(int row = cs[i].getRow();row<hs[i]+cs[i].getRow();row++){
        for(int col = cs[i].getCol();col<ws[i]+cs[i].getCol();col++){
          Coordinate curr = new Coordinate(row,col);
          assertEquals(rs.occupiesCoordinates(curr),true);
        }
      }
    }
  }

  private void test_fourMethods_helper(SpecialShip rs,Coordinate[] cs, int[] ws, int[] hs){
    int r = cs[0].getRow(), c = cs[0].getCol();
    assertEquals(rs.getPlacement(),new Placement(cs[0],'R'));
    assertThrows(IllegalArgumentException.class, () -> rs.wasHitAt(new Coordinate(r-1,c)));
    assertThrows(IllegalArgumentException.class, () -> rs.recordHitAt(new Coordinate(r+1,c)));
    assertThrows(IllegalArgumentException.class, () -> rs.getDisplayInfoAt(new Coordinate(r+1,c),true));
    assertEquals(rs.isSunk(),false);
    for(int i = 0; i < cs.length;i++){
      for(int row = cs[i].getRow();row<hs[i]+cs[i].getRow();row++){
        for(int col = cs[i].getCol();col<ws[i]+cs[i].getCol();col++){
          Coordinate curr = new Coordinate(row,col);
          assertEquals(rs.wasHitAt(curr),false);
          assertEquals(rs.getDisplayInfoAt(curr,true),'s');
          assertEquals(rs.getDisplayInfoAt(curr,false),null);
          rs.recordHitAt(curr);
          assertEquals(rs.wasHitAt(curr),true);
          assertEquals(rs.getDisplayInfoAt(curr,true),'*');
          assertEquals(rs.getDisplayInfoAt(curr,false),'s');
        }
      }
    }
    assertEquals(rs.isSunk(),true);
  }
}
