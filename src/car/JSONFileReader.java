package car;

import java.util.ArrayList;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A class that will read in and parse a JSON file
 * @author Daniel Madeley
 *
 */
public class JSONFileReader {

	/**
	 * Method to read in a parse a JSON file.
	 * Then sorts the JSON into car objects
	 * @return An array list of car objects
	 */
	public ArrayList<Car> readJSONFile() {
		ArrayList<Car> cars = new ArrayList<Car>();
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("C:\\vehicles.json")); // change location
			
			JSONObject jsonObject = (JSONObject) obj;
			

            JSONObject search = (JSONObject) jsonObject.get("Search");
            JSONArray vehicleList = (JSONArray) search.get("VehicleList");
            
            for(int i = 0; i < vehicleList.size(); i++) {
                JSONObject vehicle = (JSONObject) vehicleList.get(i);
            	
                String sipp = vehicle.get("sipp").toString();
                String name = vehicle.get("name").toString();
                Double price = Double.parseDouble(vehicle.get("price").toString());
                String supplier = vehicle.get("supplier").toString();
                Double rating = Double.parseDouble(vehicle.get("rating").toString());
                
                // ** changed in rest as the constructor wouldn't work.
            	Car car = new Car();
            	car.setName(name);
            	car.setSipp(sipp);
            	car.setPrice(price);
            	car.setSupplier(supplier);
            	car.setRating(rating);

            	cars.add(car); // add the new object to the array
            }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cars;
	}
	
}
