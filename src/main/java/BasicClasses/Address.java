package BasicClasses;
import java.io.Serializable;

public class Address implements Serializable {

	private String streetAddress;
    private String city;
	private String state;
	private String zipCode;
	private String country;

	public Address (){
	}

	public Address (String street, String city, String state, String zip, String country) {
		this.streetAddress  = street;
		this.city = city;
		this.state = state;
		this.zipCode = zip;
		this.country = country;
	}

	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String toString() {
	        return   "\nAddress:"+streetAddress + ": "
	                + city + ", " + state + " " + zipCode+ " "+ country ;
	}
}
