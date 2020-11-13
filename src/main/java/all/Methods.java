package all;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Methods {

	public void menu() {
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
				createAccount();
				break;
			
			case "2":
				deleteAccount();
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
		String ssn=null;
		Customer customer = new Customer();
		Scanner scan = new Scanner(System.in);
		Date returnDate,pickUpDate;
/*
		System.out.println("Do you have an account? Y/N");
		if(scan.next().equalsIgnoreCase("Y")) {
			ssn = enterValidSSN(customer);
			if(ssn==null)
				menu();
			else if (Customer.getCustomerBySSN(ssn) == null) {
				System.out.println(ssn + "account doesn't exist ");
				menu();
			} else{
				customer = Customer.getCustomerBySSN(ssn);
				System.out.println("You login the system as "+ssn);
			}
		}else {
			createAccount();
		}
*/		do{
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


	public void deleteAccount() {
		String ssn;
		Scanner scan = new Scanner(System.in);
		Customer deleteCustomer = new Customer();
		System.out.println("**********************************************)");
		System.out.println("************   DELETE AN ACCOUNT  ************)");
		System.out.println("**********************************************)");

		ssn=enterValidSSN(deleteCustomer);
		if(ssn == null)
			menu();
		deleteCustomer = Customer.getCustomerBySSN(ssn);
			
		if (deleteCustomer==null) {
			System.out.println("The Account doesnt exist");
			menu();
		}

		if(!enterPassword(deleteCustomer))
			menu();
		else{
			Customer.removeCustomerBySSN(ssn);
			menu();
		}
	}

	public void createAccount() {
		Scanner scan = new Scanner(System.in);
		Customer newCustomer = new Customer();
		String ssn;
		System.out.println("**********************************************)");
		System.out.println("************   CREATE AN ACCOUNT  ************)");
		System.out.println("**********************************************)");
		do {
			ssn= enterValidSSN(newCustomer);
			if(ssn==null)
				menu();

			newCustomer.setSSN(ssn);
			if (Customer.isCustomerExist(newCustomer)) {
				System.out.println("Customer "+ssn+ " is already exist");
				System.out.println("Do you want to re-enter. Y/N?");
				if(scan.next().toUpperCase() == "N")
					menu();
			}
		}while(Customer.isCustomerExist(newCustomer));

		createPasswordAndNames(newCustomer);
		enterCardNumber(newCustomer,"creditCard");
		enterCardNumber(newCustomer,"CVV");
		enterCardNamesAndDates(newCustomer);
		Customer.addCustomer(newCustomer);
		menu();
	}
	public void createPasswordAndNames(Customer customer){
		Scanner scan = new Scanner(System.in);
		String password;
		String firstName,lastName;

		System.out.println("Please Create your password ");
		password= scan.next();
		customer.setPassword(password);

		System.out.println("Please Enter your First Name:");
		firstName= scan.next();
		System.out.println("Please Enter your Last Name:");
		lastName= scan.next();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
	}
	public boolean enterPassword(Customer customer){
		Scanner scan = new Scanner(System.in);
		int trial=0;
		String password;
		do {
			System.out.println("\nPlease enter your PASSWORD");
			password = scan.next();
			if (!password.equals(customer.getPassword())) {
				System.out.println("The Password incorrect");
				trial++;
			}
			if(trial>3) {
				System.out.println("You entered too much incorrect Password");
				System.out.println("You will be routed to main menu");
				return false;
			}
		}while(!password.equals(customer.getPassword()));
		return true;
	}

	public void enterCardNumber(Customer customer ,String type ){
		String number;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Please Enter your "+type+" Number:");
			number = scan.next();
			if (!customer.creditCard.isValid(number,type)) {
				System.out.println("Please Enter Correct "+type+ " Number or to Exit enter Q");
				number = scan.next();
				if (number.equalsIgnoreCase("q")) {
					menu();
				}
			}
		}while(!customer.creditCard.isValid(number,type));
		if (type.equalsIgnoreCase("creditCard"))
			customer.creditCard.setCardNumber(number);
		else if (type.equalsIgnoreCase("CVV"))
			customer.creditCard.setCvvCode(number);
	}

	public void enterCardNamesAndDates(Customer customer){
		Scanner scan = new Scanner(System.in);
		String cardFullName;
		String expDate=null;

		/* HUSNA HANIM BUNU DÜZELTEBİLİR MİSİNİZ?
		int expiryMonth=1;
		int expiryYear=1;
		do {
			System.out.println("Please enter expritation Month/Year of your card:");
			expDate = scan.next();
			customer.creditCard.setExpDate(expDate);
			if (expiryMonth < 1 || expiryMonth > 12) {
				System.out.println("Please Enter Correct Month");
			}
		}while(expiryMonth < 1 || expiryMonth > 12);
		*/
		System.out.println("Please enter Card Name");
		cardFullName = scan.next();
	//	customer.creditCard.setExpDate(expDate);
		customer.creditCard.setFullName(cardFullName);
	}

	public String enterValidSSN(Customer customer) {
		Scanner scan = new Scanner(System.in);
		String ssn;
		do {
			System.out.println("Please enter your SSN as 11 Digit with hypen(-)");
			ssn= scan.next();
			if (!customer.isSSNValid(ssn)) {
				System.out.println("Please Enter Correct SSN or to exist Enter Q");
				ssn = scan.next();
				if (ssn.equalsIgnoreCase("q"))
					return null;
			}
		}while(!customer.isSSNValid(ssn)) ;
		return ssn;
	}


}
