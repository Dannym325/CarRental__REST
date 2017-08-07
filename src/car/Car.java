package car;

import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * A class that represents a car object.
 * Will include getters/setters
 * Will also include a range of different methods to sort out data.
 * @author Daniel Madeley
 *
 */

@Path("/car")
public class Car implements Comparable<Car> {
	
	public String name;
	public String sipp;
	public String supplier;
	public Double price;
	public Double rating;
	JSONFileReader fileReader = new JSONFileReader();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSipp() {
		return sipp;
	}

	public void setSipp(String sipp) {
		this.sipp = sipp;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	/**
	 * Method that uses natural ordering to print out cars in price ascending order
	 * @param cars
	 */
	public String sortPriceAcending() {
		ArrayList<Car> cars = new ArrayList<Car>();
		cars = fileReader.readJSONFile();
		ArrayList<String> outputArr = new ArrayList<String>();
		Collections.sort(cars);
		for (Car car : cars) {
			String result = "{" + car.getName() + "} - {" + car.getPrice() + "} \n";
			outputArr.add(result);
		}
		return outputArr.toString();
	}
	
	/**
	 * Finds the highest rated car per type and finds the supplier
	 * @param cars
	 */
	public String sortBySupplierRating() {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		ArrayList<String> outputArr = new ArrayList<String>();
		cars = fileReader.readJSONFile();
		
		String[] carTypes = {"M", "E", "C", "I", "S", "F", "P", "L", "X"};
		// loops around all the car types
		for(int i = 0; i <= carTypes.length - 1; i++) {
			Double highestRated = 0.0;
			int highestRatedIndex = 0;
			String sipp = null;
			// loops around all the cars
			for(int j = 0; j < cars.size(); j++) {
				sipp = cars.get(j).getSipp();
				String[] sippParts = sipp.split("");
				// checking if the car type matches the car in the first loop
				if(sippParts[0].equalsIgnoreCase(carTypes[i])) {
					// checks if the current car has the highest rating
					if(cars.get(j).getRating() > highestRated) {
						highestRated = cars.get(j).getRating();
						highestRatedIndex = j;
					}
				}	
			}
			String result = "{" + cars.get(highestRatedIndex).getName() + "} - {" + getCarType(carTypes[i]) + "} - {" + 
						cars.get(highestRatedIndex).getSupplier() + "} - {" + cars.get(highestRatedIndex).getRating() + "} \n";
			outputArr.add(result);
		}
		return outputArr.toString();
		
	}

	
	/**
	 * Method to display each car and break down its SIPP into its meanings.
	 * @param cars
	 */
	public String printOutCarSpecs() {
		ArrayList<Car> cars = new ArrayList<Car>();
		ArrayList<String> outputArr = new ArrayList<String>();
		cars = fileReader.readJSONFile();
		
		String carType, doors, transmission, fuelAirCon; 
		
		for (Car car : cars) {
			String sipp = car.getSipp();
	        String[] parts = sipp.split("");
	        
	        // find the type of car by the first letter of the SIPP.
	        carType = getCarType(parts[0]);
	        
	        // finds the car type and number of doors by the second letter of the SIPP
	        doors = getCarDoorType(parts[1]);
	        
	        // finds the car transmission type by the third letter of the SIPP
	        transmission = getCarTranmission(parts[2]);
	        
	        // find the fuel and air con type by the forth letter of the SIPP
	        fuelAirCon = getCarFuelAirCon(parts[3]);
	        
	        String[] arrFuelAir = fuelAirCon.split("/");
	        String fuelType = arrFuelAir[0];
	        String airCon = arrFuelAir[1];
	        
	        String result = "{" + car.getName() + "} - {" + car.getSipp() + "} - {" 
	        + carType + "} - {" + doors + "} - {" + transmission + "} - {" + fuelType + "} - {" + airCon + "} \n";
	        outputArr.add(result);
	        
		}
		return outputArr.toString();
	}
	
	
	/**
	 * Finds vehicle specs and adds up a score, and adds it with the supplier rating.
	 * @param cars
	 */
	public String getVehicleScore() {
		ArrayList<Car> cars = new ArrayList<Car>();
		ArrayList<String> outputArr = new ArrayList<String>();
		cars = fileReader.readJSONFile();
		
		
		int manual = 1, automatic = 5, airCon = 2;
		
		for(int i = 0; i < cars.size(); i++) {
			int totalScore = 0;
			String sipp = cars.get(i).getSipp();
			String[] parts = sipp.split("");
			if(parts[2].equalsIgnoreCase("M")) { // manual
				totalScore = totalScore + manual;
			} else if (parts[2].equalsIgnoreCase("A")) { // automatic
				totalScore = totalScore + automatic;
			}
			if(parts[3].equalsIgnoreCase("R")) {
				totalScore = totalScore + airCon;
			}
			Double supplierRating = cars.get(i).getRating();
			Double vehicleScore = supplierRating + totalScore;
			String name = cars.get(i).getName();
			String output = "{" + name + "} - {" + totalScore + "} - {" + supplierRating + "} - {" + vehicleScore + "} \n";
			outputArr.add(output);
		}
		return outputArr.toString();
		
	}
	
	
	@Override
	public int compareTo(Car otherCar) {
		if(otherCar == null) {
			throw new NullPointerException("Attempted to compare " + this + " to null");
		} else if (!this.getClass().equals(otherCar.getClass())) {
            throw new ClassCastException("Class Cast Exception");
        } else if (this.price < otherCar.price) {
            return -1;
        } else if (this.price > otherCar.price) {
            return 1;
        }
		return 0;
	}
	
	public String getCarType(String type) {
		String carType;
        switch(type) {
	        case "M" : carType = "Mini"; break;
	        case "E" : carType = "Economy"; break;
	        case "C" : carType = "Compact"; break;
	        case "I" : carType = "Intermediate"; break;
	        case "S" : carType = "Standard"; break;
	        case "F" : carType = "Full Size"; break;
	        case "P" : carType = "Premium"; break;
	        case "L" : carType = "Luxury"; break;
	        case "X" : carType = "Special"; break;
	        default : carType = "Unknown";
	    }
        return carType;
	}
	
	public String getCarDoorType(String doors) {
        switch(doors) {
	        case "B" : doors = "2 Doors"; break;
	        case "C" : doors = "4 Doors"; break;
	        case "D" : doors = "5 Doors"; break;
	        case "W" : doors = "Estate"; break;
	        case "T" : doors = "Convertible"; break;
	        case "F" : doors = "SUV"; break;
	        case "P" : doors = "Pick up"; break;
	        case "V" : doors = "Passenger Van"; break;
	        default : doors = "Unknown";
        }
        return doors;
	}
	
	public String getCarTranmission(String transmission) {
        switch(transmission) {
	        case "M" : transmission = "Manual"; break;
	        case "A" : transmission = "Automatic"; break;
	        default : transmission = "Unknown";
	    }
		return transmission;
	}
	
	public String getCarFuelAirCon(String fuelAirCon) {
		switch(fuelAirCon) {
	        case "N" : fuelAirCon = "Petrol/no AC"; break;
	        case "R" : fuelAirCon = "Petrol/AC"; break;
	        default : fuelAirCon = "Unknown/Unknown";
	    }
		return fuelAirCon;
	}
	
	/**
	 * Method to print out all the resutls from each method as the progam kept crashing
	 * when running multiple methods.
	 * @return - Returns an arraylist in the form of a string
	 */
	@GET
	@Produces("text/plain")
	public String printResultsToBrowser() {
		ArrayList<String> allResultsArr = new ArrayList<String>();
		
		allResultsArr.add("All cars sorted in price ascending order:");
		String ascendingOrder = sortPriceAcending();
		allResultsArr.add(ascendingOrder);
		
		allResultsArr.add("\n Prints out the highest rated supplier per car type:");
		String supplierRating = sortBySupplierRating();
		allResultsArr.add(supplierRating);
		
		allResultsArr.add("\n Prints out all the vehicles specs:");
		String carSpecs = printOutCarSpecs();
		allResultsArr.add(carSpecs);
		
		allResultsArr.add("\n Gives each vehicle a scores and prints it out:");
		String vehicleScore = getVehicleScore();
		allResultsArr.add(vehicleScore);
		
		return allResultsArr.toString();
	}
}
