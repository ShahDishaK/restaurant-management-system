package package1;
import java.util.*;
public class Payment {
    static Scanner scanner=new Scanner(System.in);
    public static void payment(double totalBill)
    {
        double balance=100000;
        String confirm;
        System.out.println("select your payment method");
        System.out.println("1:Credit Card");
        System.out.println("2:Debit Card");
        System.out.println("3:UPI");
        int paymentChoice=scanner.nextInt();
        scanner.nextLine();
        switch(paymentChoice)
        {
            case 1:
            System.out.println("Enter your 16-digit credit card number");
                    String credit_number = scanner.next();
                    while (true) {
                        if (credit_number.length() != 16) {
                            System.out.println("please enter valid number");
                            credit_number = scanner.next();
                        } else {
                            System.out.println("Valid!");
                            break;
                        }
                    }

                    System.out.println("Enter your 4 or 6-digit pin");
                    String credit_pin = scanner.next();
                    while (true) {
                        if (credit_pin.length() == 4 || credit_pin.length() == 6) {
                            System.out.println("valid!");
                            break;
                        } else {
                            System.out.println("please enter valid pin");
                            credit_pin = scanner.next();

                        }
                    }
                    System.out.println("Do you want to confirm you payment?(yes/no)");
                    confirm = scanner.next();
                    while (true) {
                        if (confirm.equalsIgnoreCase("yes")) {
                            balance = balance - totalBill;
                            System.out.println("Your new balance is " + balance + "rupees");
                            System.out.println("Payment Successful");
                            break;
                        } else if (confirm.equalsIgnoreCase("no")) {
                            System.out.println("Payment failed!");
                            break;
                        } else {
                            System.out.println("Invalid entry! please enter yes or no!");
                            confirm = scanner.next();
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter your 16-digit Debit card number");
                    String debit_number = scanner.next();
                    while (true) {
                        // String debit_number_string=Double.toString(debit_number);
                        if (debit_number.length() != 16) {
                            System.out.println("please enter valid number");
                            debit_number = scanner.next();
                        } else {
                            System.out.println("Valid!");
                            break;
                        }
                    }

                    System.out.println("Enter your 4 or 6-digit pin");
                    String debit_pin = scanner.next();
                    while (true) {
                        if (debit_pin.length() == 4 || debit_pin.length() == 6) {
                            System.out.println("valid!");
                            break;
                        } else {
                            System.out.println("please enter valid pin");
                            debit_pin = scanner.next();
                        }
                    }
                    System.out.println("Do you want to confirm you payment?(yes/no)");
                    confirm = scanner.next();
                    while (true) {
                        if (confirm.equalsIgnoreCase("yes")) {
                            balance = balance - totalBill;
                            System.out.println("Your new balance is " + balance + "rupees");
                            System.out.println("Payment Successful");
                            break;
                        } else if (confirm.equalsIgnoreCase("no")) {
                            System.out.println("Payment failed!");
                            break;
                        } else {
                            System.out.println("Invalid entry! please enter yes or no!");
                            confirm = scanner.next();
                        }
                    }

                    break;
                case 3:
                    System.out.println("Enter your UPI ID!");
                    String UPI_id = scanner.next();
                    while (true) {
                        if (UPI_id.contains("@")) {
                            System.out.println("Valid");
                            break;
                        } else {
                            System.out.println("Please enter valid upi address");
                            UPI_id = scanner.next();
                        }
                    }
                    System.out.println("Enter your 4-digit pin");
                    String pin = scanner.next();
                    while (true) {

                        if (pin.length() == 4) {
                            System.out.println("Valid");
                            break;
                        } else {
                            System.out.println("Please enter valid pin!");
                            pin = scanner.next();
                        }
                    }
                    System.out.println("Do you want to confirm you payment?(yes/no)");
                    confirm = scanner.next();
                    while (true) {
                        if (confirm.equalsIgnoreCase("yes")) {
                            balance = balance - totalBill;
                            System.out.println("Your new balance is " + balance + "rupees");
                            System.out.println("Payment Successful");
                            break;
                        } else if (confirm.equalsIgnoreCase("no")) {
                            System.out.println("Payment failed!");
                            break;
                        } else {
                            System.out.println("Invalid entry! please enter yes or no!");
                            confirm = scanner.next();
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println("Payment failed!");
                    break;
            }
        } 
        }