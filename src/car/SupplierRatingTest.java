package car;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SupplierRatingTest {

	Car car = new Car();
	String result = car.sortBySupplierRating();
	String[] parts;

	@Before
	public void setUp() throws Exception {
		parts = result.split(",");
	}

	@Test
	public void firstCarTest() {
		assertEquals("[{Kia Picanto} - {Mini} - {Hertz} - {8.9}", parts[0].trim());
	}
	
	@Test
	public void secondCarTest() {
		assertEquals("{Ford Galaxy} - {Full Size} - {Hertz} - {8.9}", parts[5].trim());
	}
}
