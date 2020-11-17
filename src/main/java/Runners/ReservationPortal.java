package Runners;

import BasicClasses.Car;
import BasicClasses.Customer;
import BasicClasses.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReservationPortal {
    Date pickUpDate;
    Date returnDate;
    public void create() {
        CustomerPortal customerPortal = new CustomerPortal();
        UserPortal userPortal = new UserPortal();
        AccountPortal accountPortal = new AccountPortal();
        Car selectedCar;
        Reservation reservation;
        String pickUpLocation;
        Customer customer = new Customer();

        Scanner scan = new Scanner(System.in);
        System.out.println("************************************************************************");
        System.out.println("***********************  CREATE RESERVATION   **************************");
        System.out.println("************************************************************************");
		System.out.println("Do you have an account? Y/N");
        if(scan.next().equalsIgnoreCase("Y")) {
            customer = customerPortal.checkCustomer();
            if (customer == null)
                userPortal.menu();
            else if (!customerPortal.checkPassword(customer)) {
                userPortal.menu();
            } else {
                System.out.println("You login the system as "+customer.getSSN());
            }
        }else{
            accountPortal.create();
        }
        long diff,rentDay;
        double totalPrice;
        do {
            enterPickUpAndReturnDates();
            pickUpLocation = enterPickUpLocation(customer);
            diff = returnDate.getTime() - pickUpDate.getTime();
            rentDay = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            selectedCar = suggestAndSelectCar(pickUpLocation,rentDay);
            if(selectedCar == null) {
                System.out.println("Do you want to change your renting info Y/N:");
                if(scan.next().equalsIgnoreCase("N"))
                    userPortal.menu();
            }
        }while(selectedCar == null);
        totalPrice = rentDay * selectedCar.getDailyPrice();
        reservation = new Reservation(customer,selectedCar, pickUpLocation, pickUpDate, returnDate,totalPrice);
        reservation.addReservation();
        System.out.println("Congrulation!!. Your Reservation was completed..");
        reservation.getReserListByCustomer(customer);
        userPortal.menu();
    }

    public void view() {
        String option=null;
        Scanner scan = new Scanner(System.in);
        UserPortal userPortal = new UserPortal();
        System.out.println("************************************************************************");
        System.out.println("***************************  VIEW RESERVATION   ****************************");
        System.out.println("************************************************************************");
        System.out.println("1-Search by Your SSN , 2-Search by Your Name, Q- Exit");
        option = scan.next();
        if (option.equals("1"))
            searchAccountBySsn() ;
        else if (option.equals("2"))
            searchAccountByName();

        userPortal.menu();
    }
    public void searchAccountByName() {
        UserPortal userPortal = new UserPortal();
        Reservation reservation = new Reservation();
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
                        reservation.getReserListByCustomer(w);
                    }
                }
            }
        }while(nameList.size() == 0);
    }

    public void searchAccountBySsn() {
        UserPortal userPortal = new UserPortal();
        CustomerPortal customerPortal = new CustomerPortal();
        Reservation reservation = new Reservation();
        Scanner scan = new Scanner(System.in);
        Customer customer ;
        customer = customerPortal.checkCustomer();
        if(customer==null)
            userPortal.menu();
        else if (!customerPortal.checkPassword(customer)) {
            userPortal.menu();
        } else {
            reservation.getReserListByCustomer(customer);
        }
    }


    public void delete() {
        CustomerPortal customerPortal = new CustomerPortal();
        Reservation reservation = new Reservation();
        UserPortal userPortal = new UserPortal();
        Customer customer;
        List<Reservation> reservListByCustomer;
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************************************************");
        System.out.println("**************************  DELETE RESERVATION   ***********************");
        System.out.println("************************************************************************");
        customer = customerPortal.checkCustomer();
        if(customer==null)
            userPortal.menu();
        else if(!customerPortal.checkPassword(customer)) {
            userPortal.menu();
        }else {
            reservListByCustomer =  reservation.getReserListByCustomer(customer);
            int option;
            do {
                System.out.println("Please enter the reservation number of delete reservation ");
                System.out.println("Or to Cancel please enter 0::");
                option = scan.nextInt();
                if (option < 0 || option > reservListByCustomer.size()) {
                    System.out.println("Please correct number");
                    option = scan.nextInt();
                }
            }while(option < 0 || option > reservListByCustomer.size());

            if(option!=0) {
                reservation.removeReservationBySsn(customer.getSSN());
            }
            reservation.getReserListByCustomer(customer);
            userPortal.menu();
        }
    }


    public Car suggestAndSelectCar(String pickUpLocation, long rentDay) {
        Scanner scan = new Scanner(System.in);
        List<Car> avalaibleCars;

        avalaibleCars = Car.searchByCurrentCity(pickUpLocation);
        if (avalaibleCars.size() == 0) {
            System.out.println("Unfortunately there is no car at " + pickUpLocation);
            return null;
        } else {
            Car.printCarListed(avalaibleCars, rentDay);
            int selectedCar;
            do {
                System.out.println("Which Car do you want to reserve:");
                selectedCar = scan.nextInt();
                if (selectedCar < 0 || selectedCar > avalaibleCars.size()) {
                    System.out.println("Please enter correct number or to exist enter 0");
                    selectedCar = scan.nextInt();
                }
            }while(selectedCar < 0 || selectedCar > avalaibleCars.size());

            return avalaibleCars.get(selectedCar-1);
        }
    }
    public String enterPickUpLocation(Customer customer){
        String pickUpLocation;
        Scanner scan = new Scanner(System.in);
        System.out.println("Does the pickUp location equal to your location:Y/N");
        boolean no = scan.next().equalsIgnoreCase("N");
        if(no) {
            System.out.println("Please enter your PickUp Location:");
            pickUpLocation = scan.next();
        }else{
            pickUpLocation =  customer.address.getCity();
        }

        return pickUpLocation;
    }

    public void enterPickUpAndReturnDates() {
        do {
            System.out.println("Please enter your pick-up date as dd-mm-yyyy:");
            pickUpDate = enterDate();
        } while (pickUpDate == null);

        boolean equalOrSmaller=false;
        do{
            System.out.println("Please enter your return date");
            returnDate = enterDate();
            if(returnDate != null){
                equalOrSmaller = returnDate.compareTo(pickUpDate) == -1 || returnDate.compareTo(pickUpDate) == 0;
                if (equalOrSmaller) {
                    System.out.println("Return Date can not be smaller than or equal to pick-up date");
                }
            }
        }while(returnDate == null || equalOrSmaller);
    }

    public Date enterDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Scanner scan = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        String str = scan.nextLine();
        try {
            Date date = sdf.parse(str);
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (date.compareTo(calendar.getTime())== -1){
                System.out.println("Please Enter future date");
                return  null;
            }
            return date;
        } catch (ParseException e) {
            System.out.println("Incorrect Date Format");
            return  null;
        }
    }


}
