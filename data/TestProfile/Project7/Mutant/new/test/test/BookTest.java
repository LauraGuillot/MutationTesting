package test;

import com.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
	
	 @Test
    public void testConstructor() {
      Book b = new Book(100);
	  assertEquals(-1, b.getCurPage());
    }
	

    @Test
    public void testOpen() {
      Book b = new Book(100);
	  b.open();
	  assertEquals(1, b.getCurPage());
    }
	
	@Test
    public void testClose() {
      Book b = new Book(100);
	  b.open();b.close();
	  assertEquals(-1, b.getCurPage());
    }
	
	@Test
    public void testIsOpened() {
      Book b = new Book(100);
	  b.open();
	  assertEquals(true, b.isOpened());
    }
	
	@Test
    public void testIsOpened2() {
      Book b = new Book(100);
	  b.open();b.close();
	  assertEquals(false, b.isOpened());
    }
	
	@Test
    public void testIsOpened3() {
      Book b = new Book(100);
	  assertEquals(false, b.isOpened());
    }
	
	@Test
    public void testRead() {
      Book b = new Book(100);
	  b.open(); b.read();
	  assertEquals(2,  b.getCurPage());
    }
	
	 @Test
    public void testRead2() {
      Book b = new Book(100);
		b.read();
	  assertEquals(-1,  b.getCurPage());
    }
	
	@Test
    public void testRead3() {
      Book b = new Book(1);
	  b.open(); b.read();
	  assertEquals(-1,  b.getCurPage());
    }
	   
   
}
