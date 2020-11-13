package all;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 *Three should be an option of searching for cars by car model
 *
 */
public class Search {
	public HashMap<String, Car> searchByCar(String model, Map<String, Car> carInventory) {
		HashMap<String, Car> result = new HashMap<>();
		Set<Entry<String, Car>>  carsSet = carInventory.entrySet();
		
		for (Entry<String, Car> car : carsSet) {
			if(car.getValue().toString().contains(model)) {
				result.put(car.getKey(), car.getValue());
			}
		}		
		
		return result;
		
	}
}
