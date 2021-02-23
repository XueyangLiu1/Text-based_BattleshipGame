package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate c1 = new Coordinate(1,3);
    Coordinate c2 = new Coordinate(4,9);
    test_makeCoord_helper(c1,3,10);
    test_makeCoord_helper(c2,99,12);
  }

  private void test_makeCoord_helper(Coordinate c,int w,int h){
    HashSet<Coordinate> hs = RectangleShip.makeCoords(c,w,h);
    for(int row = c.getRow();row<h+c.getRow();row++){
      for(int col = c.getCol();col<w+c.getCol();col++){
        Coordinate curr = new Coordinate(row,col);
        assertEquals(hs.contains(curr),true);
      }
    }
  }

  @Test
  public void test_constructor(){
    Coordinate c1 = new Coordinate(1,2);
    Coordinate c2 = new Coordinate(3,9);
    Placement p1 = new Placement(c1,'H');
    Placement p2 = new Placement(c2,'H');
    RectangleShip<Character> rs1 = new RectangleShip<Character>("submarine",c1,8,10, 's', '*',p1);
    RectangleShip<Character> rs2 = new RectangleShip<Character>("carrier",c2,65,12, 's','*',p2);
    assertEquals(rs1.getName(),"submarine");
    assertEquals(rs2.getName(),"carrier");
    assertEquals(rs1.getPlacement(),p1);
    assertEquals(rs2.getPlacement(),p2);
    test_occupy(rs1,c1,8,10);
    test_occupy(rs2,c2,65,12);
  }

  private void test_occupy(RectangleShip rs,Coordinate c, int w, int h){
    for(int row = c.getRow();row<h+c.getRow();row++){
      for(int col = c.getCol();col<w+c.getCol();col++){
        Coordinate curr = new Coordinate(row,col);
        assertEquals(rs.occupiesCoordinates(curr),true);
      }
    }
  }
  @Test
  public void test_fourMethods(){
    Coordinate c1 = new Coordinate(2,3);
    Coordinate c2 = new Coordinate(3,4);
    test_fourMethods_helper(c1,2,3,new Placement(c1,'H'));
    test_fourMethods_helper(c2,7,8, new Placement(c2,'V'));
  }

  private void test_fourMethods_helper(Coordinate c,int width,int height,Placement p){
    RectangleShip<Character> rs1 = new RectangleShip<Character>("submarine",c,width,height, 's', '*',p);
    int row = c.getRow(),col = c.getCol();
    assertEquals(rs1.getPlacement(),p);
    assertThrows(IllegalArgumentException.class, () -> rs1.wasHitAt(new Coordinate(row-1,col)));
    assertThrows(IllegalArgumentException.class, () -> rs1.recordHitAt(new Coordinate(row,col+width)));
    assertThrows(IllegalArgumentException.class, () -> rs1.getDisplayInfoAt(new Coordinate(row,col+width),true));
    for(int i = row;i<row+height;i++){
      for(int j = col;j<col+width;j++){
        Coordinate curr = new Coordinate(i,j);
        assertEquals(rs1.wasHitAt(curr),false);
        assertEquals(rs1.getDisplayInfoAt(curr,true),'s');
        assertEquals(rs1.getDisplayInfoAt(curr,false),null);
        rs1.recordHitAt(curr);
        assertEquals(rs1.wasHitAt(curr),true);
        assertEquals(rs1.getDisplayInfoAt(curr,true),'*');
        assertEquals(rs1.getDisplayInfoAt(curr,false),'s');
        if(i==row+height-1 && j==col+width-1){
          assertEquals(rs1.isSunk(),true);
        }else{
          assertEquals(rs1.isSunk(),false);
        }
      }
    }
  }

  @Test
  public void test_getCoordinates(){
    Coordinate c1 = new Coordinate(3,9);
    Ship<Character> carrier = new RectangleShip<Character>("carrier",c1,65,12, 's','*',new Placement(c1,'H'));
    int squareNum = 0;
    for(Coordinate c: carrier.getCoordinates()){
      assertEquals(carrier.occupiesCoordinates(c),true);
      squareNum++;
    }
    assertEquals(squareNum,780);
  }

}






