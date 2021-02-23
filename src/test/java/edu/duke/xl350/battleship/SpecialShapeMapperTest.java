package edu.duke.xl350.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public class SpecialShapeMapperTest {
  @Test
  public void test_BattleshipShapeMapper() throws IOException{
    SpecialShapeMapper ssm = new SpecialShapeMapper();
    Placement p1 = new Placement("a0v");
    Placement p2 = new Placement("a0D");
    Placement p3 = new Placement("a0R");
    Placement p4 = new Placement("a0L");
    Placement p5 = new Placement("a0U");
    
    assertThrows(IllegalArgumentException.class, ()->ssm.BattleshipShapeMapper(p1,new Coordinate(0,0),p3));
    assertThrows(IllegalArgumentException.class, ()->ssm.BattleshipShapeMapper(p2,new Coordinate(3,4),p3));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,0),p3),new Coordinate(2,0));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,0),p4),new Coordinate(0,1));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,0),p5),new Coordinate(1,2));
    
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,1),p3),new Coordinate(1,0));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,1),p4),new Coordinate(1,1));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,1),p5),new Coordinate(1,1));

    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,2),p3),new Coordinate(0,0));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,2),p4),new Coordinate(2,1));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,2),p5),new Coordinate(1,0));

    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(1,1),p3),new Coordinate(1,1));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(1,1),p4),new Coordinate(1,0));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(1,1),p5),new Coordinate(0,1));

    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,0),p2),new Coordinate(0,0));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,1),p2),new Coordinate(0,1));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(0,2),p2),new Coordinate(0,2));
    assertEquals(ssm.BattleshipShapeMapper(p2,new Coordinate(1,1),p2),new Coordinate(1,1));

    assertEquals(ssm.BattleshipShapeMapper(p3,new Coordinate(0,0),p4),new Coordinate(2,1));
    assertEquals(ssm.BattleshipShapeMapper(p4,new Coordinate(0,1),p5),new Coordinate(1,2));
    assertEquals(ssm.BattleshipShapeMapper(p5,new Coordinate(1,0),p3),new Coordinate(0,0));
  }

  @Test
  public void test_CarrierShapeMapper() throws IOException{
    SpecialShapeMapper ssm = new SpecialShapeMapper();
    Placement p1 = new Placement("a0v");
    Placement p2 = new Placement("a0u");
    Placement p3 = new Placement("a0R");
    Placement p4 = new Placement("a0D");
    Placement p5 = new Placement("a0L");
    
    assertThrows(IllegalArgumentException.class, ()->ssm.CarrierShapeMapper(p1,new Coordinate(0,0),p3));
    assertThrows(IllegalArgumentException.class, ()->ssm.CarrierShapeMapper(p2,new Coordinate(3,4),p3));

    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(0,0),p3),new Coordinate(1,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(1,0),p3),new Coordinate(1,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,0),p3),new Coordinate(1,2));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,1),p3),new Coordinate(0,2));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(3,1),p3),new Coordinate(0,3));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(4,1),p3),new Coordinate(0,4));

    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(0,0),p4),new Coordinate(4,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(1,0),p4),new Coordinate(3,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,0),p4),new Coordinate(2,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,1),p4),new Coordinate(2,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(3,1),p4),new Coordinate(1,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(4,1),p4),new Coordinate(0,1));

    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(0,0),p5),new Coordinate(1,4));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(1,0),p5),new Coordinate(1,3));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,0),p5),new Coordinate(1,2));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,1),p5),new Coordinate(0,2));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(3,1),p5),new Coordinate(0,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(4,1),p5),new Coordinate(0,0));

    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(0,0),p2),new Coordinate(0,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(1,0),p2),new Coordinate(1,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,0),p2),new Coordinate(2,0));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(2,1),p2),new Coordinate(2,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(3,1),p2),new Coordinate(3,1));
    assertEquals(ssm.CarrierShapeMapper(p2,new Coordinate(4,1),p2),new Coordinate(4,1));

    assertEquals(ssm.CarrierShapeMapper(p3,new Coordinate(1,0),p4),new Coordinate(4,0));
    assertEquals(ssm.CarrierShapeMapper(p4,new Coordinate(0,1),p5),new Coordinate(0,0));
    assertEquals(ssm.CarrierShapeMapper(p5,new Coordinate(0,1),p3),new Coordinate(0,3));
  }

  @Test
  public void test_DestroyerShapeMapper() throws IOException{
    SpecialShapeMapper ssm = new SpecialShapeMapper();
    Placement p1 = new Placement("a0R");
    Placement p2 = new Placement("a0V");
    Placement p3 = new Placement("a0H");
    
    assertThrows(IllegalArgumentException.class, ()->ssm.DestroyerShapeMapper(p1,new Coordinate(0,0),p3));
    assertThrows(IllegalArgumentException.class, ()->ssm.DestroyerShapeMapper(p2,new Coordinate(3,4),p3));

    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(0,0),p3),new Coordinate(0,0));
    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(1,0),p3),new Coordinate(0,1));
    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(2,0),p3),new Coordinate(0,2));

    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(0,0),p2),new Coordinate(0,0));
    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(1,0),p2),new Coordinate(1,0));
    assertEquals(ssm.DestroyerShapeMapper(p2,new Coordinate(2,0),p2),new Coordinate(2,0));

    assertEquals(ssm.DestroyerShapeMapper(p3,new Coordinate(0,2),p2),new Coordinate(2,0));
  }

  @Test
  public void test_SubmarineShapeMapper() throws IOException{
    SpecialShapeMapper ssm = new SpecialShapeMapper();
    Placement p1 = new Placement("a0R");
    Placement p2 = new Placement("a0V");
    Placement p3 = new Placement("a0H");
    
    assertThrows(IllegalArgumentException.class, ()->ssm.SubmarineShapeMapper(p1,new Coordinate(0,0),p3));
    assertThrows(IllegalArgumentException.class, ()->ssm.SubmarineShapeMapper(p2,new Coordinate(3,4),p3));

    assertEquals(ssm.SubmarineShapeMapper(p2,new Coordinate(0,0),p3),new Coordinate(0,0));
    assertEquals(ssm.SubmarineShapeMapper(p2,new Coordinate(1,0),p3),new Coordinate(0,1));

    assertEquals(ssm.SubmarineShapeMapper(p2,new Coordinate(0,0),p2),new Coordinate(0,0));
    assertEquals(ssm.SubmarineShapeMapper(p2,new Coordinate(1,0),p2),new Coordinate(1,0));

    assertEquals(ssm.SubmarineShapeMapper(p3,new Coordinate(0,1),p2),new Coordinate(1,0));
  }

}
