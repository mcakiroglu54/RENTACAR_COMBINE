package all;
import data.TestData;

public class Runner {
	public static void main(String[] args) {
		TestData testData = new TestData();
		testData.createTestCustomer();
		testData.createTestCar();
		Methods method = new Methods();
		method.menu();

	}
	
}


