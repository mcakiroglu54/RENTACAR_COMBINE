package Runners;
import BasicClasses.Customer;
import java.util.List;
import java.util.Scanner;

public class AccountPortal {

    public void delete() {
        UserPortal userPortal = new UserPortal();
        CustomerPortal customerPortal = new CustomerPortal();
        Customer customer;
        System.out.println("************************************************************************");
        System.out.println("***************************  DELETE ACCOUNT   **************************");
        System.out.println("************************************************************************");

        customer = customerPortal.checkCustomer();
        if(customer==null)
            userPortal.menu();
        else if(!customerPortal.checkPassword(customer)) {
            userPortal.menu();
        }else {
            customer.removeCustomerBySSN(customer.getSSN());
            userPortal.menu();
        }
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        UserPortal userPortal = new UserPortal();
        Customer customer = new Customer();
        CreditCardPortal creditCardPortal = new CreditCardPortal();
        AddressPortal addressPortal = new AddressPortal();
        CustomerPortal customerPortal = new CustomerPortal();

        String ssn;
        System.out.println("************************************************************************");
        System.out.println("***************************  CREATE ACCOUNT   **************************");
        System.out.println("************************************************************************");
        do {
            ssn = customerPortal.enterValidSSN();
            if(ssn==null)
                userPortal.menu();

            customer.setSSN(ssn);
            if (customer.isCustomerExist()) {
                System.out.println("Customer "+ssn+ " is already exist");
                System.out.println("Do you want to re-enter. Y/N?");
                if(scan.next().equalsIgnoreCase("N"))
                    userPortal.menu();
            }

        }while(customer.isCustomerExist());
        customerPortal.createPasswordAndNames(customer);
        creditCardPortal.enterCardNumber(customer,"creditCard");
        creditCardPortal.enterCardNumber(customer,"CVV");
        creditCardPortal.enterCardNamesAndDates(customer);
        addressPortal.enterAddress(customer);
        customer.addCustomer();
        System.out.println("The Account of: "+customer.getSSN()+ " was created");
        userPortal.menu();
    }

    public void view() {
        Scanner scan = new Scanner(System.in);
        UserPortal userPortal = new UserPortal();
        System.out.println("************************************************************************");
        System.out.println("***************************  VIEW ACCOUNT   ****************************");
        System.out.println("************************************************************************");
        System.out.println("1-Search by Your SSN , 2-Search by Your Name, Q- Exit");
        String option = scan.next();
        if (option.equals("1"))
           searchAccountBySsn() ;
        else if (option.equals("2"))
            searchAccountByName();

        userPortal.menu();
    }
    public void searchAccountByName() {
        UserPortal userPortal = new UserPortal();
        String password;
        String fullName;
        List <Customer> nameList;
        Scanner scan = new Scanner(System.in);
        Customer customer = new Customer();
        do {
            System.out.println("Enter your full name");
            fullName = scan.nextLine();
            nameList = customer.searchByName(fullName);
            if (nameList.size() == 0) {
                System.out.println(fullName + "'s account doesn't exist");
                System.out.println("Please Re-Enter your Full Name or Type Q to exit");
                if (scan.next().equalsIgnoreCase("Q"))
                    userPortal.menu();
            } else {
                System.out.println("Please enter your password:");
                password = scan.next();
                for (Customer w : nameList) {
                    if (w.getPassword().equals(password)) {
                        System.out.println("************************************************************************");
                        System.out.println("****************************  YOUR ACCOUNT    **************************");
                        System.out.println("************************************************************************");
                        w.printCustomer();
                    }
                }
            }
        }while(nameList.size() == 0);
    }

    public void searchAccountBySsn() {
        UserPortal userPortal = new UserPortal();
        CustomerPortal customerPortal = new CustomerPortal();
        Scanner scan = new Scanner(System.in);
        Customer customer ;
        customer = customerPortal.checkCustomer();
        if(customer==null)
            userPortal.menu();
        else if (!customerPortal.checkPassword(customer)) {
            userPortal.menu();
        } else {
            System.out.println("************************************************************************");
            System.out.println("****************************  YOUR ACCOUNT    **************************");
            System.out.println("************************************************************************");
            customer.printCustomer();
        }
    }

}
