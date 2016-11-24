package test;

import org.Triangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {

    /**
     * Test of getB method, of class Triangle.
     */
    @Test
    public void testGetB() {
        Triangle instance = new Triangle(1, 2, 1);
        System.out.println("getB");
        assertEquals(2, instance.getB());
    }

    /**
     * Test of getC method, of class Triangle.
     */
    @Test
    public void testGetC() {
        Triangle instance = new Triangle(1, 1, 2);
        System.out.println("getC");
        assertEquals(2, instance.getC());
    }

    /**
     * Test of equi method, of class Triangle.
     */
    @Test
    public void testEqui() {
        Triangle instance = new Triangle(1, 1, -1);
        System.out.println("equi false");
        boolean expResult = false;
        boolean result = instance.equi();
        assertEquals(expResult, result);

        Triangle instance1 = new Triangle(4, 4, 4);
        System.out.println("equi true");
        boolean expResult1 = true;
        boolean result1 = instance1.equi();
        assertEquals(expResult1, result1);
    }
 @Test(timeout=3000)
public void yourTest() {
    Triangle t = new Triangle(1,1,2);
  assertEquals(t.equi(),false);
} 
}
