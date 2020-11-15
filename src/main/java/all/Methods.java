package all;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Methods {


	public void menu() {
		Account account = new Account();

		Scanner scan = new Scanner(System.in);
		System.out.println("**********************************************************");
		System.out.println("***************  TECHPRO RENT A CAR   ********************");
		System.out.println("**********************************************************");	
		System.out.println();
		System.out.println("1- Create an account");
		System.out.println("2- Delete an account");
		System.out.println("3- Create a reservation");
		System.out.println("4- Cancel your reservation");
		System.out.println("5- Search Car By Information");
		System.out.println("6- List Avalaible Cars By Info");
		String option = scan.next();
		
		switch (option) {
		
			case "1":
				account.create();
				break;
			
			case "2":
				account.delete();
				break;

			case "3":
				createReservation();
				break;

			case "4":
				cancelReservation();
				break;

		default:
			break;
		}
	}

	public void cancelReservation() {

	}

	public void createReservation() {
		Account account = new Account();
		String ssn=null;
		Customer customer = new Customer();
		Scanner scan = new Scanner(System.in);
		Date returnDate,pickUpDate;

		System.out.println("Do you have an account? Y/N");
		if(scan.next().equalsIgnoreCase("Y")) {
			ssn = account.enterValidSSN(customer);
			if(ssn==null)
				menu();
			else if (customer.getCustomerBySSN(ssn) == null) {
				System.out.println(ssn + "account doesn't exist ");
				menu();
			} else{
				customer = customer.getCustomerBySSN(ssn);
				System.out.println("You login the system as "+ssn);
			}
		}else {
			account.create();
		}

		do{
			System.out.println("Please enter your pick-up date");
			pickUpDate = enterDate();
			if(pickUpDate == null)
				System.out.println("Please enter a valid date");
		}while(pickUpDate == null);

		System.out.println("Your pick-up Date: " + pickUpDate);
		boolean equalOrSmaller=false;
		do{
			System.out.println("Please enter your return date");
			returnDate = enterDate();
			equalOrSmaller =returnDate.compareTo(pickUpDate)== -1 || returnDate.compareTo(pickUpDate)== 0;
			if(returnDate == null)
				System.out.println("Please enter a valid date");
			else if (equalOrSmaller)
				System.out.println("Return Date can not be smaller than or equal to pick-up date");
		}while(returnDate == null || equalOrSmaller);

		System.out.println("Your Return Date: " + returnDate);
	}

	public Date enterDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Scanner scan = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();

		System.out.print("Enter date as dd-mm-yyyy ");
		String str = scan.nextLine();

		try {

			Date date = sdf.parse(str);
			sdf = new SimpleDateFormat("dd-MM-yyyy");

			if (date.compareTo(calendar.getTime())== -1){
				System.out.println("Please Enter future date");
				return  null;
			}
			System.out.println(date);
			return date;
		} catch (ParseException e) {
			System.out.println("Incorrect Date Format");
			return  null;
		}

	}
}
