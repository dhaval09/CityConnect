package CityConnect.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import CityConnect.demo.service.areCityConnectedService;

/**
 * Unit test for simple App.
 */
public class AppTest {

	areCityConnectedService testareCityConnectedService;

	/**
	 * Testing the core method only as rest all are private methods not exposed 
	 *
	 * 
	 */
	@Before
	public void setUp() {
		testareCityConnectedService = new areCityConnectedService();
	}

	@Test
	public void areCitiesConnectedServiceTest() {

		String validate = testareCityConnectedService.areCitiesConnectedService("Boston", "New York");
		assertEquals("yes", validate);
		validate = testareCityConnectedService.areCitiesConnectedService("boston", "new York");
		assertEquals("no", validate);
		validate = testareCityConnectedService.areCitiesConnectedService("", "");
		assertEquals("no", validate);
	}
	
	
}
