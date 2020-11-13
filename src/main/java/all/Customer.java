package all;
import java.util.ArrayList;
import java.util.List;

public class Customer {
/*
  	There should be full name of the customer
	There should be address information of a customer
	There should be SSN number of customer that contains just 10 digits, no spec chars or chars
	There should be Credit card Information of a customer
	
*/
	private String firstName;
	private String lastName;
	private String SSN;
	private String password;
	protected CreditCardPortal creditCard;
	protected Address address;

	protected static List<Customer> customersList = new ArrayList<Customer>();

	public Customer(){
		address = new Address();
		creditCard = new CreditCardPortal();
	}
	public Customer(String firstName, String lastName, String ssn, String password ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = ssn;
		this.password = password;
	}

	public void setAddress(String streetAddr, String city, String state, String zip, String country){
		address = new Address(streetAddr, city,state, zip,country);
	}

	public void setCreditCard(String cardNumber, String fullName, String expDate, String cvv) {
		creditCard = new CreditCardPortal(cardNumber, fullName,expDate,cvv);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return SSN;
	}
	public void setSSN(String ssn) {
		SSN = ssn;
	}

	/*
	 	The valid SSN (Social Security Number) must satisfy the following conditions:
		It should have 9 digits.
		It should be divided into 3 parts by hyphen (-).
		The first part should have 3 digits and should not be 000, 666, or between 900 and 999.
		The second part should have 2 digits and it should be from 01 to 99.
		The third part should have 4 digits and it should be from 0001 to 9999.
    */
	public  boolean isSSNValid (String ssn) {
		if (ssn.length() != 11) {
			return false;
		}
		String part1=ssn.substring(0, 2);
		for(int i=0;i<ssn.length();i++) {
			if(i!=3 && i!=6) {
				if (!Character.isDigit(ssn.charAt(i)))
					return false;
			}else if(i==3 || i==6) {
				if(ssn.charAt(3) != '-')
					return false;
			}
		}
		if(part1.equals("000") || part1.equals("666") || part1.charAt(0)>'8') {
			return false;
		}
		return true;
	}

	public static boolean isCustomerExist(Customer customer) {
		return customersList.stream().anyMatch(t->t.getSSN().equals(customer.getSSN()));
	}

	public static void addCustomer(Customer customer) {
		if(!isCustomerExist(customer))
			customersList.add(customer);
	}

	public static Customer getCustomerBySSN(String SSN ) {
		for(Customer w: customersList) {
			if(w.getSSN().equals(SSN) ){
				return w;
			}
		}
		return null;
	}

	public static boolean removeCustomerBySSN(String SSN) {
		for (Customer w : customersList) {
			if (w.getSSN().equals(SSN)) {
				customersList.remove(w);
				System.out.println("Customer: " + SSN + " was removed:");
				return true;
			}
		}
		return false;
	}
}
