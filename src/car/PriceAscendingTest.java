package car;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PriceAscendingTest {
	
	Car car = new Car();
	String result = car.sortPriceAcending();
	String[] parts;

	@Before
	public void setUp() throws Exception {
		parts = result.split(",");
	}
	
	@Test
	public void lowestPriceTest() {
		assertEquals("[{ChevroletSpark} - {120.16}", parts[0].trim());
	}
	
	@Test
	public void highestPriceTest() {
		// had the test the second from biggest because the string was over two lines.
		assertEquals("{VW Sharan} - {753.75}", parts[29].trim());
	}
	
	@Test
	public void middleTest() {
		assertEquals("{Audi A1} - {234.76}", parts[12].trim());
	}
}
	
	


