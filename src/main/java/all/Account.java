package all;

import java.util.Scanner;

public class Account extends Customer{

    Methods method = new Methods();

    public Account(){
    }
    public void delete() {
        String ssn;
        Scanner scan = new Scanner(System.in);
        Customer deleteCustomer = new Customer();
        System.out.println("**********************************************)");
        System.out.println("************   DELETE AN ACCOUNT  ************)");
        System.out.println("**********************************************)");

        ssn=enterValidSSN(deleteCustomer);
        if(ssn == null)
            method.menu();

        deleteCustomer=deleteCustomer.getCustomerBySSN(ssn);

        if (deleteCustomer==null) {
            System.out.println("The Account doesnt exist");
            method.menu();
        }

        if(!enterPassword(deleteCustomer)) {
            method.menu();
        }
        else{
            deleteCustomer.removeCustomerBySSN(ssn);
            method.menu();
        }
    }

    public void create() {
        Methods method= new Methods();
        Scanner scan = new Scanner(System.in);
        Customer newCustomer = new Customer();
        String ssn;
        System.out.println("**********************************************)");
        System.out.println("************   CREATE AN ACCOUNT  ************)");
        System.out.println("**********************************************)");
        do {
            ssn= enterValidSSN(newCustomer);
            if(ssn==null)
                method.menu();

            newCustomer.setSSN(ssn);
            if (newCustomer.isCustomerExist()) {
                System.out.println("Customer "+ssn+ " is already exist");
                System.out.println("Do you want to re-enter. Y/N?");
                if(scan.next().equalsIgnoreCase("N"))
                    method.menu();
            }

        }while(newCustomer.isCustomerExist());
        createPasswordAndNames(newCustomer);
        enterCardNumber(newCustomer,"creditCard");
        enterCardNumber(newCustomer,"CVV");
        enterCardNamesAndDates(newCustomer);
        newCustomer.addCustomer();
        method.menu();
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
                    method.menu();
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
