package Runners;
import BasicClasses.Customer;
import java.util.Scanner;

public class CreditCardPortal {

    public void enterCardNumber(Customer customer , String type ){
        UserPortal userPortal = new UserPortal();
        String number;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Please Enter your "+type+" Number:");
            number = scan.next();
            if (!customer.creditCard.isValid(number,type)) {
                System.out.println("Please Enter Correct "+type+ " Number or to Exit enter Q");
                number = scan.next();
                if (number.equalsIgnoreCase("q")) {
                    userPortal.menu();
                }
            }
        }while(!customer.creditCard.isValid(number,type));
        if (type.equalsIgnoreCase("creditCard"))
            customer.creditCard.setCardNumber(number);
        else if (type.equalsIgnoreCase("CVV"))
            customer.creditCard.setCvvCode(number);
    }

    public void enterCardNamesAndDates(Customer customer){
        Scanner scan = new Scanner(System.in);
        String cardFullName;
        String expDate;

        do {
            System.out.println("Please enter expiration date (MM/YYYY) of your card:");
            expDate = scan.next();
            if(!customer.creditCard.isValidExpDate(expDate))
                System.out.println("Please Enter Correct Month");
        }while(!customer.creditCard.isValidExpDate(expDate));

        System.out.println("Please enter Card Name");
        cardFullName = scan.next();
        customer.creditCard.setExpDate(expDate);
        customer.creditCard.setFullName(cardFullName);
    }
}
