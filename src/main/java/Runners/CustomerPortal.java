package Runners;
import BasicClasses.Customer;

import java.util.Scanner;

public class CustomerPortal {

    UserPortal userPortal = new UserPortal();

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

    public void createTestCustomer() {
        Customer c1 = new Customer("Mustafa", "Kenar", "111-11-1111","1234");
        c1.setAddress("Mustafa kemal str.", "İzmir", "TR", "35100","TURKEY");
        c1.setCreditCard("554923456789986", "Mustafa Kenar", "03/21", "456");

        Customer c2 = new Customer("Ahmet", "Çalı", "134-45-3456","1234");
        c2.setAddress("Happy street", "Arlington", "Texas", "66666","USA");
        c2.setCreditCard("111123456789986", "Ahmet Çalı", "12/22", "222");

        Customer c3 = new Customer("Helga", "Hans", "444-45-1111","1234");
        c3.setAddress("ich mohte funf kofte str", "Köln", "NRV", "34567", "Germany");
        c3.setCreditCard("222233344445566", "Helga Hans", "10/21", "241");
        c1.addCustomer();
        c2.addCustomer();
        c3.addCustomer();

    }

}
