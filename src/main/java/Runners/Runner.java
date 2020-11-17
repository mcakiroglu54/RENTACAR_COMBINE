package Runners;
import BasicClasses.Reservation;

public class Runner {
	public static void main(String[] args) {
		TestData testData = new TestData();
		UserPortal userPortal = new UserPortal();
		testData.createTestData();
		Reservation.printAllReservation();
		userPortal.menu();
	}
}


