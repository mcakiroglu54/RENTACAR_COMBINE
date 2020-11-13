package all;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Address{
	Scanner input = new Scanner(System.in);
	private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

	/**
     * Create an address with all empty fields.
     *
     */

	public Address () {
		this.streetAddress  = getStreetAddress();
		this.city = getCity();
		this.state = getState();
		this.zipCode=getZipCode();
		this.country=getCountry();
	}

    public Address (String street, String city, String state, String zip, String country) {
		this.streetAddress  = street;
		this.city = city;
		this.state = state;
		this.zipCode = zip;
		this.country = country;
    }

    /**
     * Create an address.
     */
    /*
    public Address (String streetAddr, String city, 
        String state, String zip, String country) {
    	streetAddress = streetAddr;
        setCity(city);
        setState(state);
        setZipCode(zip);
        setCountry(country);
    }*/

	public String getStreetAddress() {
		System.out.println("Street: ");
		String street= input.nextLine();
		
		return street.trim();
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		System.out.println("City: ");
		String city= input.nextLine();
		return city.trim();
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		System.out.println("State: ");
		String state= input.nextLine();
		String stateCleared=state.replaceAll("[\\s]", "").toLowerCase();
		return stateInitials(stateCleared);
	}
	//CLear unwanted characters from state info
	public void setState(String state) {
		
		String stateCleared=state.replaceAll("[\\s]", "").toLowerCase();
		this.state = stateInitials(stateCleared);
	}
	//
    //Convert Full Text to  states initial
	public String stateInitials(String state)
	{
		//Enter states to a Map and assign the name to Short
				Map<String, String> states = new HashMap<String, String>();
				states.put("alabama","AL");
				states.put("alaska","AK");
				states.put("alberta","AB");
				states.put("american Samoa","AS");
				states.put("arizona","AZ");
				states.put("arkansas","AR");
				states.put("armedForces(AE)","AE");
				states.put("armedForcesAmericas","AA");
				states.put("armedForcesPacific","AP");
				states.put("britishColumbia","BC");
				states.put("california","CA");
				states.put("colorado","CO");
				states.put("connecticut","CT");
				states.put("delaware","DE");
				states.put("district Of Columbia","DC");
				states.put("florida","FL");
				states.put("georgia","GA");
				states.put("guam","GU");
				states.put("hawaii","HI");
				states.put("idaho","ID");
				states.put("illinois","IL");
				states.put("indiana","IN");
				states.put("iowa","IA");
				states.put("kansas","KS");
				states.put("kentucky","KY");
				states.put("louisiana","LA");
				states.put("maine","ME");
				states.put("manitoba","MB");
				states.put("maryland","MD");
				states.put("massachusetts","MA");
				states.put("michigan","MI");
				states.put("minnesota","MN");
				states.put("mississippi","MS");
				states.put("missouri","MO");
				states.put("montana","MT");
				states.put("nebraska","NE");
				states.put("nevada","NV");
				states.put("newbrunswick","NB");
				states.put("newhampshire","NH");
				states.put("newjersey","NJ");
				states.put("newmexico","NM");
				states.put("newyork","NY");
				states.put("newfoundland","NF");
				states.put("northcarolina","NC");
				states.put("northdakota","ND");
				states.put("northwestterritories","NT");
				states.put("novascotia","NS");
				states.put("nunavut","NU");
				states.put("ohio","OH");
				states.put("oklahoma","OK");
				states.put("ontario","ON");
				states.put("oregon","OR");
				states.put("pennsylvania","PA");
				states.put("princeedwardisland","PE");
				states.put("puertorico","PR");
				states.put("quebec","QC");
				states.put("rhodeisland","RI");
				states.put("saskatchewan","SK");
				states.put("southcarolina","SC");
				states.put("southdakota","SD");
				states.put("tennessee","TN");
				states.put("texas","TX");
				states.put("utah","UT");
				states.put("vermont","VT");
				states.put("virginislands","VI");
				states.put("virginia","VA");
				states.put("washington","WA");
				states.put("westvirginia","WV");
				states.put("wisconsin","WI");
				states.put("wyoming","WY");
				states.put("yukonterritory","YT");
				if (states.get(state)==null) {
					System.out.println("Invalid State name");
				    return getState();
				}
				return states.get(state);
	}
	public String getZipCode() {
		String zip;
		boolean result;
		do {
		System.out.println("Zip: ");
		zip= input.nextLine().trim();
		result=!allNumbers(zip) || zip.length()!=5;
		if (result)
			System.out.println("Invalid Zip- Enter 5 digit Zipcode ");
		}while(result);
		
		return zip;
	}

	public void setZipCode(String zipCode) {
	
		boolean result =allNumbers(zipCode) && zipCode.length()==5;
		
		if (result)
			this.zipCode = zipCode;
		else
		{
			System.out.println("Invalid Zip- Enter 5 digit Zipcode ");
			this.zipCode=getZipCode();
		}
	}
	 public String getCountry() {
		    String cnt;
			do {
			System.out.println("Country: ");
			cnt= input.nextLine();
			}while(!allLetters(cnt));
			
			return cnt;
	}

	public void setCountry(String country) {
		    if (allLetters(country))
		    	this.country = country;
		    else
		    {
		    	System.out.println("Invalid Country- Enter Only Letters ");
		    	this.zipCode=getCountry();
		    }
		    	
	}
		
	public boolean allNumbers(String cardNum) {
			int count = 0;
			for (int j = 0; j < cardNum.length(); j++) {
				 if(cardNum.charAt(j)>='0' &&  cardNum.charAt(j)<='9') 
					 count++;		
			}
			return count == cardNum.length();
	}
	public boolean allLetters(String input) {
		 int count = 0;
		 String name = input.toLowerCase();
		 for (int j = 0; j < input.length(); j++) {
			 if(name.charAt(j)>='a' &&  name.charAt(j)<='z') 
				 count++;		
		}
		 return count == input.length();
	}
	
	 public boolean equals (Object right)
	    {
	        Address r = (Address)right;
	        return  streetAddress.equals(r.streetAddress)
	                && city.equals(r.city)
	                && state.equals(r.state)
	                && zipCode.equals(r.zipCode)
	                && country.equals(r.country);
	    }
	 public boolean equalsCity (Address right)
	    {
	      	return  city.trim().toLowerCase().equals(right.city.trim().toLowerCase());
	             
	    }
	 public boolean equalsState (Address right)
	    {
	        return  state.trim().toLowerCase().equals(right.state.trim().toLowerCase());
	    }
	 public boolean equalsCountry (Address right)
	    {
	       
	        return  country.trim().toLowerCase().equals(right.country.trim().toLowerCase());
	    }
	 public boolean equalsZip (Address right)
	    {
	       
	        return   zipCode.equals(right.zipCode);
	    }

	 public int hashCode ()
	    {
	        return  3 * streetAddress.hashCode()
	        + 5 * city.hashCode()
	        + 7 * state.hashCode()
	        + 11 * zipCode.hashCode();
	    }

	 public String toString()
	    {
	        return   streetAddress + ": " 
	                + city + ", " + state + " " + zipCode+ " "+ country ;
	    }


}
