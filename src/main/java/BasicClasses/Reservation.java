package BasicClasses;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation implements Serializable{
	private Car car;
	private Customer customer;
	private String pickUpLocation;
	private String returnLocation;
	private double totalPrice;
	private int reservationId;
	private Date pickUpDate;
	private Date returnDate;
	private static int ID=100;
	private static final String filepath="src/main/java/Reservations.txt";

	public int getReservationId() { return reservationId; }
	public void setReservationId(int reservationId) { this.reservationId = reservationId; }
	public Date getReturnDate() { return returnDate; }
	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
	public String getPickUpLocation() { return pickUpLocation; }
	public void setPickUpLocation(String pickUpLocation) { this.pickUpLocation = pickUpLocation; }
	public String getReturnLocation() { return returnLocation; }
	public void setReturnLocation(String returnLocation) { this.returnLocation = returnLocation; }
	public Date getPickUpDate() { return pickUpDate; }
	public void setPickUpDate(Date pickUpDate) { this.pickUpDate = pickUpDate; }
	public Customer getCustomer() { return customer; }
	public void setCustomer() { this.customer = customer; }
	public Car getCar() { return car; }
	public void setCar(Car car) { this.car = car; }
	public double getTotalPrice() { return totalPrice; }
	public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

	public Reservation(Customer customer, Car car,String pickUpLocation, Date pickUpDate, Date returnDate, double totalPrice) {
		this.reservationId = ++ID;
		this.customer = customer;
		this.car = car;
		this.pickUpLocation = pickUpLocation;
		this.pickUpDate = pickUpDate;
		this.returnDate = returnDate;
		this.totalPrice = totalPrice;
	}
	public static void printReservation() {
		List<Reservation> reservationList = Reservation.takeReservationList();
		for (Reservation w : reservationList) {
			System.out.println("NAME:"+w.getCustomer().getFullName());
			System.out.println("CAR:"+w.getCar().getMake()+" "+ w.getCar().getPlate());
			System.out.println("PICK UP DATE:"+w.getPickUpDate());
			System.out.println("RETURN DATE "+ w.getReturnDate());
			System.out.println("TOTAL PRICE:$"+w.getTotalPrice());
		}
	}
	public void add() {
		List<Reservation> reservationList = Reservation.takeReservationList();
		for (Reservation w : reservationList) {
			if (w.getReservationId() == this.reservationId) {
				return;
			}
		}
		reservationList.add(this);
		File file = new File(filepath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			for (Reservation w : reservationList) {
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

	public void Remove() {
		List<Reservation> reservation = Reservation.takeReservationList();
	}


	public Reservation searchBySSN(String ssn) {
		return null;

	}


	public static Reservation searchByPlate(String plate) {
		return null;
	}


	public static List<Car> getReservedCars() {
		List<Car> reservedCars = new ArrayList<>();
		List<Reservation> reservation = Reservation.takeReservationList();
		return reservedCars;
	}

	public static List<Car> getUnbookedCars() {
	 return null;
	}

	public static List<Reservation> takeReservationList() {
		List<Reservation> reservationList = new ArrayList<>();
		ObjectInputStream inputStream = null;
		try {
			// open the file for reading
			inputStream = new ObjectInputStream(new FileInputStream(filepath));
			boolean EOF = false;
			// Keep reading file until file ends
			while (!EOF) {
				try {
					Reservation reservation = (Reservation) inputStream.readObject();
					reservationList.add(reservation);
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
		return reservationList;
	}

}
