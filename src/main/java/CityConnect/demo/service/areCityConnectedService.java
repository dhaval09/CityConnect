package CityConnect.demo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import CityConnect.demo.interfaces.areCityConnectedInterface;



@Service
public class areCityConnectedService implements areCityConnectedInterface {
	private static final Logger Logger = LoggerFactory.getLogger(areCityConnectedService.class);
	public static final String DELIMITER = ",";
	public String areCityConnectedResult = "no";
	String filename = "city.txt";

	public String areCitiesConnectedService(String source, String destination) {
		Logger.info("areCitiesConnectedService Intialized");
		try {
			Map<String, Set<String>> cityToNodeMap = readandParseFile(filename);
			Logger.debug("readandParseFile completed");
			boolean result = isConnected(cityToNodeMap, source, destination);
			Logger.debug("isConnected completed");
			areCityConnectedResult = printResult(result);
			Logger.debug("are Cities connected " + areCityConnectedResult);

		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
		return areCityConnectedResult;
	}

	// The key in the map are the cities that we have available.
	// The value is a set of string, a set of cities that this city is connected to
	// So the strings are the nodes, the name of the cities.
	// If there is a key-value pair (or key to set pair) then those cities are
	// connected
	//
	// The map approach is sparse, so it will reduce the amount of memory used
	// however, for each line, we add two key-value pairs for both directions of
	// possible traversal
	private Map<String, Set<String>> readandParseFile(String filename) throws IOException {
		Map<String, Set<String>> cityToNodeMap = new HashMap<String, Set<String>>();
		BufferedReader bufferedReader = null;
		try {
			Logger.debug("readandParseFile Intialized");
			Reader fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while (line != null && !line.isEmpty()) {
				String[] cities = line.split(DELIMITER);
				String firstCity = cities[0].trim();
				String secondCity = cities[1].trim();

				Set<String> firstCityConnections = getCityConnections(cityToNodeMap, firstCity);
				Set<String> secondCityConnections = getCityConnections(cityToNodeMap, secondCity);
				firstCityConnections.add(secondCity);
				secondCityConnections.add(firstCity);

				line = bufferedReader.readLine();
			}
		} 
		catch(Exception e)
		{
			Logger.error(e.getMessage());
		}
		finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		return cityToNodeMap;
	}

	// helper function to default the connected cities in the map to the empty set
	private static Set<String> getCityConnections(Map<String, Set<String>> map, String city) {
		if (!map.containsKey(city)) {
			map.put(city, new HashSet<String>());
		}
		return map.get(city);
	}

	private static boolean isConnected(Map<String, Set<String>> cityToNodeMap, String city1, String city2) {
		boolean isFound = city1.equals(city2);
		if (cityToNodeMap.containsKey(city1) && cityToNodeMap.containsKey(city2)) {
			// By using a Queue, we are implementing Breadth First Search
			// This will find the shortest path between two cities
			Queue<String> citiesToVisit = new LinkedList<String>();

			// We keep a set of the cities we have already visited. This prevents BFS from
			// looping in
			// cycles and allows the BFS to terminate if no path can be found after
			// exploring all reachable nodes
			Set<String> citiesAlreadyVisited = new HashSet<String>();

			citiesToVisit.add(city1);

			while (!citiesToVisit.isEmpty() && !isFound) {
				String city = citiesToVisit.poll();
				isFound = city.equals(city2);

				Set<String> possibleConnections = cityToNodeMap.get(city);
				for (String possibleCity : possibleConnections) {
					if (!citiesAlreadyVisited.contains(possibleCity)) {
						citiesToVisit.add(possibleCity);
						citiesAlreadyVisited.add(possibleCity);
					}
				}
			}
		}

		return isFound;
	}

	private String printResult(boolean result) {
		if (result) {
			return "yes";
		} else {
			return "no";

		}
	}

}
