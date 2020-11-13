package data;
import all.Customer;
import all.Car;
public class TestData {

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

        Customer.addCustomer(c1);
        Customer.addCustomer(c2);
        Customer.addCustomer(c3);

    }

    public void createTestCar() {

        Car car1 =new Car("MIA-54", "Sedan", "Eco", 5, 2020, 1);
        Car car2 =new Car("KLN-203", "SUV", "Lux", 7, 2019, 1);
        Car car3 =new Car("ARB-123", "Sport", "Mid", 5, 2010, 1);
        Car car4 =new Car("KRC-121", "Sedan", "Mid", 7, 2018, 1);
        Car car5 =new Car("IST-1453", "Sport", "Lux", 2, 2020, 1);
        Car car6 =new Car("TX-456", "Sedan", "Eco", 5, 2020, 1);
        Car car7 =new Car("KLN-201", "SUV", "Lux", 7, 2019, 1);

    }

}
