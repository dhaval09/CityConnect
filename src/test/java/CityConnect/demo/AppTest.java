package CityConnect.demo;

import org.springframework.beans.factory.annotation.Autowired;

import CityConnect.demo.interfaces.areCityConnectedInterface;
import CityConnect.demo.service.areCityConnectedService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	@Autowired
	areCityConnectedService testareCityConnectedService;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
   
    public void areCitiesConnectedServiceTest()
    {   
     String validate	= testareCityConnectedService.areCitiesConnectedService("Boston", "New York");
        assertEquals("yes", validate);
    }
}
