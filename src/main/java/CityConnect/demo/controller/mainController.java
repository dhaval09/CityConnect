package CityConnect.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import CityConnect.demo.interfaces.areCityConnectedInterface;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/connected")
@CrossOrigin
public class mainController {

	/** Constant Logger*/
	private static final Logger Logger = LoggerFactory.getLogger(mainController.class);
	
	@Autowired
	areCityConnectedInterface connectInterface;
	
	
	/**
	Rest Api AreCities Connected 
	Takes in two parameter i.e Two cities 
	Returns Yes or No 
	
	*/
	@RequestMapping(method = RequestMethod.GET)
	@ApiResponses(value= {@ApiResponse(code=200,message="Success"),
	@ApiResponse(code=401,message="Unauthorized"),
	@ApiResponse(code=403,message="Forbidden"),
	@ApiResponse(code=404,message="NotFound"),
	@ApiResponse(code=500,message="Failure")})
    public String callAreCitiesConnected(@RequestParam(value="city1") String city1,@RequestParam(value="city2") String city2) {
		Logger.info("areCitiesConnected intialized");
		String success = "no";
        try {
          success = connectInterface.areCitiesConnectedService(city1,city2);
        }
        catch(Exception e) {
        Logger.debug(e.getMessage());
    }
        Logger.info("areCitiesConnected completed with Success");
        return success;
	}
 
}
