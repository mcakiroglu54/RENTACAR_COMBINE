package Runners;
import BasicClasses.Car;

public class CarPortal {

    // If Admin login account is created,
    // it can be added carAdd(), carRemove() etc. methods



    public void createTestCar() {
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
    }


}
