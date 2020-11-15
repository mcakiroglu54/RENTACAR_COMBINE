package all;
import java.util.ArrayList;
import java.util.List;

public class Car {
	private String plate ;
	private String type ;
	private String  carClass;
	private int numofSeat;
	private int model;
	private String make;
	private int carID;
	private static int ID=1000;

	protected static List<Car> carsList = new ArrayList<Car>();

	public Car(String plate, String type, String carClass, int numofSeat, int model, String make) {
		this.plate = plate;
		this.type = type;
		this.carClass = carClass;
		this.numofSeat = numofSeat;
		this.model = model;
		this.make = make;
		this.carID = ++ID;
		addCar(this);
	}

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

	public static void printCar(Car car) {
		System.out.println("\nID:"+car.getCarID() +"\nPlate:"+car.getPlate() + "\nModel:"+ car.getModel() +"\nType:" + car.getType()
				+"\nSeat:" + car.getNumofSeat() +"\nClass:" + car.getCarClass() );
	}

	public static void printAllCars() {
		System.out.println("\nAll Cars");
		System.out.println("********************");
		for(Car w:carsList) {
			printCar(w);
		}
	}

	public static void addCar(Car car) {
		boolean exist=false;
		for(Car w: carsList) {
			if(w.getPlate().equalsIgnoreCase(car.getPlate())) {
				exist=true;
				System.out.println(car.getPlate() +" plate car is already added..");
				break;
			}
		}
		if(!exist)
			carsList.add(car);
	}
	public static void getCarByModel(int model) {
		boolean exist=false;
		System.out.println("\n"+model+ " model Car List:");
		System.out.println("********************");
		for(Car w: carsList) {
			if(w.getModel() == model) {
				printCar(w);
				exist=true;
			}
		}
		if(!exist)
			System.out.println(model+ " Car doesn't exist:");
	}

	public static  void getCarBySeat(int numofSeat) {
		boolean exist=false;
		System.out.println("\n"+numofSeat+ " seat Car List:");
		System.out.println("********************");

		for(Car w: carsList) {
			if(w.getNumofSeat() == numofSeat) {
				printCar(w);
				exist=true;
			}
		}
		if(!exist)
			System.out.println(numofSeat+ " Car doesn't exist:");
	}

	public static  void getCarByType(String type) {
		boolean exist=false;
		System.out.println("\n"+type+ " Car List:");
		System.out.println("********************");

		for(Car w: carsList) {
			if(type.equalsIgnoreCase(w.getType())) {
				printCar(w);
				exist=true;
			}
		}
		if(!exist)
			System.out.println(type+ " Car doesn't exist:");
	}

	public static  void getCarByPlate(String plate) {
		System.out.println("\n"+plate+ " Car:");
		System.out.println("********************");
		for(Car w: carsList) {
			if(plate.equalsIgnoreCase(w.getPlate())) {
				printCar(w);
			}
		}

	}
}
