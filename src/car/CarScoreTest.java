package car;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarScoreTest {
	
	Car car = new Car();
	String result = car.getVehicleScore();
	String[] parts;

	@Before
	public void setUp() throws Exception {
		parts = result.split(",");
	}

	@Test
	public void firstCarTest() {
		assertEquals("{VW Jetta} - {7} - {8.9} - {15.9}", parts[4].trim());
	}
	
	@Test
	public void secondCarTest() {
		assertEquals("{Vauxhall Zafira} - {3} - {8.0} - {11.0}", parts[21].trim());
	}

}
