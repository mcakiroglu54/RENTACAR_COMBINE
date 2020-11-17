package BasicClasses;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
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
	public CreditCard creditCard;
	public Address address;
	private static final String filepath="src/main/java/Customers.txt";

	public Customer(){
		address = new Address();
		creditCard = new CreditCard();
	}
	public Customer(String firstName, String lastName, String ssn, String password ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = ssn;
		this.password = password;
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
	public String getFullName() {
		return firstName+" "+lastName;
	}


	public void setAddress(String streetAddr, String city, String state, String zip, String country){
		address = new Address(streetAddr, city,state, zip,country);
	}

	public void setCreditCard(String cardNumber, String fullName, String expDate, String cvv) {
		creditCard = new CreditCard(cardNumber, fullName,expDate,cvv);
	}

	public String toString() {
		return "FullName: "+this.getFullName()+" SSN:"+this.SSN+
				" "+this.creditCard.toString()+this.address.toString();
	}

	public void printCustomer(){
		System.out.println(this.toString());
	}
	/*
	 	The valid SSN (Social Security Number) must satisfy the following conditions:
		It should have 9 digits.
		It should be divided into 3 parts by hyphen (-).
		The first part should have 3 digits and should not be 000, 666, or between 900 and 999.
		The second part should have 2 digits and it should be from 01 to 99.
		The third part should have 4 digits and it should be from 0001 to 9999.
    */
	public static boolean isSSNValid (String ssn) {
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

	public boolean isCustomerExist() {
		List<Customer> customerList = Customer.takeCustomerList();
		for (Customer w : customerList) {
			if (w.getSSN().equals(this.getSSN())) {
				return true;
			}
		}
		return false;
	}

	public void addCustomer() {
		List<Customer> customerList = Customer.takeCustomerList();
		for (Customer w : customerList) {
			if (w.getSSN().equals(this.getSSN())) {
				return;
			}
		}
		customerList.add(this);
		File file = new File(filepath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			for(Customer w: customerList) {
				outputStream.writeObject(w);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public boolean removeCustomerBySSN(String SSN) {
		List<Customer> customerList = Customer.takeCustomerList();
		boolean exist=false;
		for (Customer w : customerList) {
			if (w.getSSN().equals(SSN)) {
				customerList.remove(w);
				System.out.println("Customer: " + SSN + " was removed:");
				exist=true;
				break;
			}
		}
		if(!exist) {
			System.out.println("the customer doesn't exist");
			return false;
		}

		// After removing an object we update the file by rewriting the list into that file.
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filepath));
			for (Customer w : customerList) {
				outputStream.writeObject(w);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return true;
	}

	public Customer getCustomerBySSN(String SSN) {
		List<Customer> customerList = Customer.takeCustomerList();
		for (Customer w : customerList) {
			if (w.getSSN().equals(SSN)) {
				return w;
			}
		}
		return null;
	}
	public List<Customer> searchByName(String name) {
		List<Customer> customerList = Customer.takeCustomerList();
		List<Customer> searchedNameList = new ArrayList<>();
		for (Customer w : customerList) {
			if (w.getFullName().equalsIgnoreCase(name)) {
				searchedNameList.add(w);
			}
		}
		return searchedNameList;
	}

	public static List<Customer> takeCustomerList() {
		List<Customer> CustomerList = new ArrayList<>();
		ObjectInputStream inputStream = null;
		try {
			// open the file for reading
			inputStream = new ObjectInputStream(new FileInputStream(filepath));
			boolean EOF = false;
			// Keep reading file until file ends
			while (!EOF) {
				try {
					Customer customer = (Customer) inputStream.readObject();
					CustomerList.add(customer);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (EOFException end) {
					EOF = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return CustomerList;
	}
}

