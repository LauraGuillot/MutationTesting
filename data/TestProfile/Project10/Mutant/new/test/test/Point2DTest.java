package test;

import com.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class Point2DTest {

    @Test
    public void testGetX() {
       Point2D p = new Point2D(1,2);
	   assertEquals(1,p.getX());
    }
	
	@Test
    public void testGetY() {
       Point2D p = new Point2D(1,2);
	   assertEquals(2,p.getY());
    }
	
	@Test
    public void testTranslate() {
       Point2D p = new Point2D(1,1);
	   p.translate(1,2);
	   assertEquals(2,p.getX());
	   assertEquals(3,p.getY());
    }
	
	@Test
    public void testEqual1() {
       Point2D p = new Point2D(1,1);
	   Point2D p1 = new Point2D(1,1);
	   assertEquals(p.equals(p1),true);
    }
	
	@Test
    public void testEqual2() {
       Point2D p = new Point2D(1,1);
	   Point2D p1 = new Point2D(1,2);
	   assertEquals(p.equals(p1),false);
	}
	
	@Test
    public void testDistance() {
       Point2D p = new Point2D(1,1);
	   Point2D p1 = new Point2D(1,1);
	   assertEquals(p.distance(p1),0,0.1);
	}
	
	@Test
    public void testDistance2() {
       Point2D p = new Point2D(1,1);
	   Point2D p1 = new Point2D(5,4);
	   assertEquals(p.distance(p1),5,0.1);
	}
	
}
	