package Runners;
import java.util.Scanner;

public class UserPortal {

	public void menu() {
		AccountPortal accountPortal = new AccountPortal();
		ReservationPortal reservationPortal= new ReservationPortal();
		Scanner scan = new Scanner(System.in);
		System.out.println("************************************************************************");
		System.out.println("*************************  TECHPRO RENT A CAR   ************************");
		System.out.println("************************************************************************");
		System.out.println("------------------------------  ACCOUNT --------------------------------");
		System.out.println("	1-Create Account  	 |	2-Delete Account 	| 		3-View Account  ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("----------------------------  RESERVATION ------------------------------");
		System.out.println("	4-Create Reservation |	5-Delete Reservation | 	6-View Reservation  ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("							   Q- EXIT 									");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Please Enter your selection:");

		String option = scan.next().toUpperCase();
		
		switch (option) {
		
			case "1":
				accountPortal.create();
				break;
			
			case "2":
				accountPortal.delete();
				break;

			case "3":
				accountPortal.view();
				break;

			case "4":
				reservationPortal.create();
				break;

			case "5":
				reservationPortal.delete();
				break;

			case "6":
				reservationPortal.view();
				break;

			default:
				break;
		}
	}

}
