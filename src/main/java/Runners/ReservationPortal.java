package Runners;

import BasicClasses.Address;
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
    UserPortal userPortal = new UserPortal();
    CustomerPortal customerPortal = new CustomerPortal();
    AccountPortal accountPortal = new AccountPortal();

    public void create() {
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
        reservation.add();
        Reservation.printReservation();

    }

    public void view() {
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************************************************");
        System.out.println("***************************  VIEW RESERVATION   ****************************");
        System.out.println("************************************************************************");
        System.out.println("1-Search by Your SSN , 2-Search by Your Name, Q- Exit");
    }

    public void delete() {
        String ssn;
        Scanner scan = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("************************************************************************");
        System.out.println("***************************  DELETE RESERVATION   **************************");
        System.out.println("************************************************************************");
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
