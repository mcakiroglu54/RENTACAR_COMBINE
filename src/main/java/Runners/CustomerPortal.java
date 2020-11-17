package Runners;
import BasicClasses.Customer;

import java.util.Scanner;

public class CustomerPortal {

    public String enterValidSSN() {
        Scanner scan = new Scanner(System.in);
        String ssn;
        do {
            System.out.println("Please enter your SSN as 11 Digit with hypen(-)");
            ssn= scan.next();
            if (!Customer.isSSNValid(ssn)) {
                System.out.println("Please Enter Correct SSN or to exist Enter Q");
                ssn = scan.next();
                if (ssn.equalsIgnoreCase("q"))
                    return null;
            }
        }while(!Customer.isSSNValid(ssn)) ;
        return ssn;
    }
    public Customer checkCustomer(){
        UserPortal userPortal = new UserPortal();
        Customer customer = new Customer();
        String ssn;
        do{
            ssn=enterValidSSN();
            if(ssn==null)
                userPortal.menu();
            else{
                if(customer.getCustomerBySSN(ssn)==null){
                    System.out.println("The Account doesn't exist");
                    System.out.println("Please Re-Enter your SSN");
                }
            }
        }while(customer.getCustomerBySSN(ssn)==null);

        return customer.getCustomerBySSN(ssn);
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

    public boolean checkPassword(Customer customer){
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

}
