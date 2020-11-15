package all;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {

	private Date pickUpDate;
	private Date returnDate;
	private Car car;
	private Customer customer;

	private Location pickUpLocation;
	private Location returnLocation;
	private int reservationId;
	private static int ID=100;
	protected static List<Reservation> reservationList = new ArrayList<Reservation>();
	protected static List<Car> avalaibleCarList = new ArrayList<Car>();

	public int getReservationId() { return reservationId; }
	public void setReservationId(int reservationId) { this.reservationId = reservationId; }
	public Date getReturnDate() { return returnDate; }
	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
	public Location getPickUpLocation() { return pickUpLocation; }
	public void setPickUpLocation(Location pickUpLocation) { this.pickUpLocation = pickUpLocation; }
	public Location getReturnLocation() { return returnLocation; }
	public void setReturnLocation(Location returnLocation) { this.returnLocation = returnLocation; }
	public Date getPickUpDate() { return pickUpDate; }
	public void setPickUpDate(Date pickUpDate) { this.pickUpDate = pickUpDate; }
	public Customer getCustomer() { return customer; }
	public void setCustomer() { this.customer = customer; }
	public Car getCar() { return car; }
	public void setCar(Car car) { this.car = car; }

	public Reservation(){
		this.reservationId = ++ID;
	}

	public static boolean isReservationExist(Reservation reservation) {
		for(Reservation w: reservationList) {
			if(w.getReservationId() == reservation.getReservationId())
				return true;
		}
		return false;
	}
	public void createReservation(Reservation reservation){
		if(!isReservationExist(reservation))
			reservationList.add(reservation);
		else
			System.out.println("it is already exist");
	}

	public void cancelReservation(){


	}
	

}
