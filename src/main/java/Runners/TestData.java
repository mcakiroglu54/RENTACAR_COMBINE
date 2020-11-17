package Runners;
import BasicClasses.Car;
import BasicClasses.Customer;
import BasicClasses.Reservation;
import java.util.Date;

public class TestData {

    public void createTestData() {
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

        Car car1 =new Car("MIA-54", "Sedan", "Eco", 5, 2020, "Honda",30,"Miami");
        Car car2 =new Car("KLN-203", "SUV", "Lux", 7, 2019, "Toyota", 50,"Arlington");
        Car car3 =new Car("ARB-123", "Sport", "Mid", 5, 2010, "BMV",70,"SanDiego");
        Car car4 =new Car("KRC-121", "Sedan", "Mid", 7, 2018, "FIAT",20,"Miami");
        Car car5 =new Car("IST-1453", "Sport", "Lux", 2, 2020, "Mercedes",100,"Chicago");
        Car car6 =new Car("TX-456", "Sedan", "Eco", 5, 2020, "Renault",15,"NewYork");
        Car car7 =new Car("KLN-201", "SUV", "Lux", 7, 2019, "Lexus",120,"NewJersey");
        car1.addCar();
        car2.addCar();
        car3.addCar();
        car4.addCar();
        car5.addCar();
        car6.addCar();
        car7.addCar();

        Date pickUpDate = new Date();
        Date returnDate = new Date();
        pickUpDate.setTime(24-12-2020);
        returnDate.setTime(30-12-2020);
        Reservation r1 = new Reservation(c1,car1,"İzmir",pickUpDate, returnDate,400.0);
        Reservation r2 = new Reservation(c2,car2,"Arlington",pickUpDate, returnDate,600.0);
        Reservation r3 = new Reservation(c3,car3,"SanDİego",pickUpDate, returnDate,750.0);
        r1.addReservation();
        r2.addReservation();
        r3.addReservation();

    }
}
