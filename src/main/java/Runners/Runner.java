package Runners;

import BasicClasses.Car;

public class Runner {
	public static void main(String[] args) {
		UserPortal userPortal = new UserPortal();
		CustomerPortal customerPortal = new CustomerPortal();
		CarPortal carPortal = new CarPortal();

		customerPortal.createTestCustomer();
		carPortal.createTestCar();
		userPortal.menu();

	}
	
}


