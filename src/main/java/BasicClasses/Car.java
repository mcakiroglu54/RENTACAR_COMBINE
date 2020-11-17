package BasicClasses;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {

	private String plate ;
	private String type ;
	private String  carClass;
	private int numofSeat;
	private int model;
	private String make;
	private int dailyPrice;
	private int carID;
	private static int ID=1000;
	private String currentCity;

	private static final String filepath="src/main/java/Cars.txt";

	//Constructor of Car Class
	public Car(){

	}

	public Car(String plate, String type, String carClass, int numofSeat, int model, String make, int dailyPrice, String currentCity) {
		this.plate = plate;
		this.type = type;
		this.carClass = carClass;
		this.numofSeat = numofSeat;
		this.model = model;
		this.make = make;
		this.carID = ++ID;
		this.dailyPrice = dailyPrice;
		this.currentCity = currentCity;
	}

	//Getter and Setter Methods
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID =carID;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCarClass() {
		return carClass;
	}
	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}
	public int getNumofSeat() {
		return numofSeat;
	}
	public void setNumofSeat(int numofSeat) {
		this.numofSeat = numofSeat;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(int String) {
		this.make = make;
	}
	public int getDailyPrice() { return dailyPrice; }
	public void setDailyPrice(int dailyPrice) { this.dailyPrice = dailyPrice;  }
	public String getCurrentCity() { return currentCity; }
	public void setCurrentCity(String currentCity) { this.currentCity = currentCity; }

	// Other methods
	public String toString() {
		return carID+" "+plate+" "+make+" "+numofSeat+" "+carClass+" "+dailyPrice+" "+currentCity;
	}
	public static void printAllCar(){
		List<Car> carList = Car.takeCarList();
		for (Car w : takeCarList()) {
			System.out.println(w.toString());
		}
	}
	public static void printCarListed(List <Car> carList,long rentDay){
		int i=1;
		for (Car w : carList) {
			System.out.println("************************ CAR-"+i+" *************************");
			System.out.println("Make:"+w.getMake()+" Model:"+w.getModel()+
								" Class:"+w.getCarClass()+" Seat:"+ w.getNumofSeat());
			System.out.println("TOTAL PRICE:"+w.getDailyPrice()*rentDay);
			i++;
		}
	}

	public void addCar() {
		List<Car> carList = Car.takeCarList();
		for (Car w : takeCarList()) {
			if (w.getPlate().equals(this.getPlate())) {
				return;
			}
		}
		carList.add(this);
		File file = new File(filepath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			for(Car w: carList) {
				outputStream.writeObject(w);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public boolean removeCarByPlate(String plate) {
		List<Car> carList = Car.takeCarList();
		boolean exist=false;
		for (Car w : carList) {
			if (w.getPlate().equals(plate)) {
				carList.remove(w);
				System.out.println("Car: " + plate + " was removed:");
				exist=true;
				break;
			}
		}
		if(!exist) {
			System.out.println("the car doesn't exist");
			return false;
		}

		// After removing an object we update the file by rewriting the list into that file.
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filepath));
			for (Car w : carList) {
				outputStream.writeObject(w);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return true;
	}

	public Car getCarByPlate(String plate) {
		List<Car> carList = Car.takeCarList();
		for (Car w : carList) {
			if (w.getPlate().equalsIgnoreCase(plate))
				return w;
		}
		return null;
	}

	public Car searchByClass(String carClass) {
		List<Car> carList = Car.takeCarList();
		for (Car w : carList) {
			if (w.getCarClass().equalsIgnoreCase(carClass))
				return w;
		}
		return null;
	}

	public static List<Car> searchByCurrentCity(String city) {
		List<Car> carList = Car.takeCarList();
		List<Car> searchedByCity = new ArrayList<>();
		for (Car w : carList) {
			if (w.getCurrentCity().equalsIgnoreCase(city)) {
				searchedByCity.add(w);
			}
		}
		return searchedByCity;
	}
	public List<Car> SearchBySeat(int numofSeat) {
		List<Car> carList = Car.takeCarList();
		List<Car> searchedBySeat = new ArrayList<>();
		for (Car w : carList) {
			if (w.getNumofSeat() == numofSeat) {
				searchedBySeat.add(w);
			}
		}
		return searchedBySeat;
	}

	//We get Car list from the file (Cars.txt) and store them into a List (carsList)
	public static List<Car> takeCarList() {
		List<Car> carsList = new ArrayList<>();
		ObjectInputStream inputStream = null;
		try {
			// open the file for reading
			inputStream = new ObjectInputStream(new FileInputStream(filepath));
			boolean EOF = false;
			// Keep reading file until file ends
			while (!EOF) {
				try {
					Car car = (Car) inputStream.readObject();
					carsList.add(car);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return carsList;
	}
}
