package test;

import com.Point2D;
import com.Circle;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void testGetR() {
       Circle c = new Circle(0,0,1);
	   assertEquals(1,c.getR());
    }
	
	@Test
    public void testGetArea() {
       Circle c = new Circle(0,0,2);
	   assertEquals(c.getArea(),Math.PI * c.getR() * c.getR(),0.1);
    }
	
	@Test
    public void testGetPerimeter() {
       Circle c = new Circle(0,0,7);
	   assertEquals(c.getPerimeter(),2 * Math.PI * c.getR(),0.1);
    }
	
	@Test
    public void testAreConcentric() {
       Circle c = new Circle(1,4,7);
	   Circle c1 = new Circle(1,8,7);
	   assertEquals(c.areConcentric(c1),false);
    }
	
	@Test
    public void testAreConcentric2() {
       Circle c = new Circle(1,1,7);
	   Circle c1 = new Circle(1,1,14);
	   assertEquals(c.areConcentric(c1),true);
    }
	
	@Test
    public void testExtend() {
       Circle c = new Circle(1,1,7);
		c.extend(-1);
	   assertEquals(6,c.getR());
    }
	
	@Test
    public void testInclude() {
       Circle c = new Circle(1,1,7);
	   Point2D p = new Point2D(2,3);
	   assertEquals(c.include(p),true);
    }
	
}