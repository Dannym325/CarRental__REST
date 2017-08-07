package car;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DetailedCarSpecTest {
	
	Car car = new Car();
	String result = car.printOutCarSpecs();
	String[] parts;


	@Before
	public void setUp() throws Exception {
		parts = result.split(",");
	}

	@Test
	public void firstCarTest() {
		assertEquals("{Ford Galaxy} - {FVAR} - {Full Size} - {Passenger Van} - {Automatic} - {Petrol} - {AC}", parts[1].trim());
	}
	
	@Test
	public void secondCarTest() {
		assertEquals("{VW Golf} - {CXMR} - {Compact} - {Unknown} - {Manual} - {Petrol} - {AC}", parts[27].trim());
	}

}
