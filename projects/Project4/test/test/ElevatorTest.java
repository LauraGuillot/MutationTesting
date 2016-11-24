package test;

import com.Elevator;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorTest {

    @Test
    public void testIsFull() {
       Elevator e = new Elevator(5,4);
	   e.addRiders(4);
	   assertEquals(true, e.isFull());
    }
	
	@Test
    public void testIsFull2() {
       Elevator e = new Elevator(5,4);
	   assertEquals(false, e.isFull());
    }
	
	@Test
    public void testAddRiders() {
       Elevator e = new Elevator(5,4);
	   e.addRiders(4);
	   assertEquals(4,e.getNumRiders());
    }
	
	@Test
    public void testAddRiders2() {
       Elevator e = new Elevator(5,4);
	   e.addRiders(1);
	   assertEquals(1,e.getNumRiders());
    }
	
	@Test
    public void testAddRiders3() {
       Elevator e = new Elevator(5,4);
	   e.addRiders(4);
	   e.addRiders(-1);
	   assertEquals(3,e.getNumRiders());
    }
	
	@Test
    public void testAddRiders4() {
       Elevator e = new Elevator(5,4);
	   e.addRiders(4);
	   e.addRiders(4);
	   assertEquals(4,e.getNumRiders());
    }
	
	@Test
    public void testGoUp() {
       Elevator e = new Elevator(5,4);
	   e.goUp();
	   assertEquals(1,e.getCurrentFloor());
    }
	
	@Test
    public void testGoUp2() {
       Elevator e = new Elevator(1,5);
	   e.goUp();e.goUp();
	   assertEquals(1,e.getCurrentFloor());
    }
	
	@Test
    public void testGoDown() {
       Elevator e = new Elevator(1,5);
	   e.goDown();
	   assertEquals(0,e.getCurrentFloor());
    }
	
	@Test
    public void testGoDown2() {
       Elevator e = new Elevator(1,5);
	   e.goUp();
	   e.goDown();
	   assertEquals(0,e.getCurrentFloor());
    }
	
   @Test
    public void testCall() {
       Elevator e = new Elevator(1,5);
	   e.call(1);
	   assertEquals(1,e.getCurrentFloor());
    }
	
	@Test
    public void testCall2() {
       Elevator e = new Elevator(2,5);
	   e.goUp(); e.goUp();
	   e.call(0);
	   assertEquals(0,e.getCurrentFloor());
    }
   
   
   
}
