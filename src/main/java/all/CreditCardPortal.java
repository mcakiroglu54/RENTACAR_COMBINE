package all;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditCardPortal {
	/*
	 * There should be a cardnumber that is 16 digit long
	 * There should be full name of the card owner 
	 * There should be expiration date as month and year 
	 * There should be a CVV code on card that is 3 digit long  */
	
	private String cardNumber;
	private String fullName;
	private String expYear; 
	private String expMonth; //it should be in number format 
	private String expDate;// formatted as MM/YYYY
	private String cvvCode;
	
	public CreditCardPortal() {
		
	}
	
	public CreditCardPortal(String cardNumber, String fullName, String expDate, String cvvCode) {
		this.setCardNumber(cardNumber);
		this.setExpDate(expDate);
		this.setFullName(fullName);
		this.setCvvCode(cvvCode);
	}
	
	//I created main for testing purposes
	/*public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
		System.out.println(dtf.format(currentDate.minusMonths(9)));
		
		System.out.println("Year is valid?"+isValidexpDate("12","2020"));
		CreditCardPortal obj = new CreditCardPortal();
		obj.setExpDate("02/2023");
		System.out.println("Date "+obj.getExpDate());
		System.out.println("Month "+obj.getExpMonth());
		System.out.println("Year "+obj.getExpYear());
		obj.setExpDate("2/2023");
		System.out.println("Date "+obj.getExpDate());
		System.out.println("Month "+obj.getExpMonth());
		System.out.println("Year "+obj.getExpYear());
		CreditCardPortal obj2 = new CreditCardPortal("12345678909876","husna ucar","12/2020","123");
		System.out.println(obj2);
	}*/

	/**
	 * A valid Card Number has 16 digits, valid CVV has 3 digits.
	 * They have not contain special character
	 */
	public boolean isValid(String number,String type) {

		int numberLength=0;
		if(type.equals("CVV")) {
			numberLength=3;
		}else if (type.equals("creditCard")) {
			numberLength=16;
		}
		if (number.length() == numberLength) {
			for (int i = 0; i < numberLength; i++) {
				if (!Character.isDigit(number.charAt(i))) {
					return false;
				}
			}
		}else {
			return false;
		}
		return true;
	}
	/*This method checks whether the given expiration date is valid or not.*/
	public boolean isValidExpDate(String expMonth,String expYear) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");
		try {
		if(Integer.parseInt(dtf1.format(currentDate))== Integer.parseInt(expYear)) {
			//check months
			if(Integer.parseInt(dtf2.format(currentDate)) <= Integer.parseInt(expMonth))
				return true;
			else 
				return false;
		}			
		else if(Integer.parseInt(dtf1.format(currentDate))<= Integer.parseInt(expYear))
			return true;
		else
			return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) { this.cvvCode = cvvCode; }

	public String getExpYear() {
		return expYear;
	}

	public String getExpMonth() {
		return expMonth;
	}

	// I intentionally did not create setter methods for expYear and expMonth

	public String getExpDate() {
		expDate = expMonth + "/"+ expYear;
		return expDate;
	}
	public void setExpDate(String expDate) {
		String s[] = expDate.split("/");
		if(isValidExpDate(s[0],s[1])) {
			this.expMonth = s[0];
			this.expYear = s[1];
			this.expDate = expDate;
		}
		else
			System.out.println("Please enter a valid expiration date.");
	}

	@Override
	public String toString() {
		return "CreditCardPortal [cardNumber=" + cardNumber + ", fullName=" + fullName + ", expDate=" + expDate
				+ ", cvvCode=" + cvvCode + "]";
	}

	

}
