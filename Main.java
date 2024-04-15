package package1;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
    mainMenu();
    }
    public static void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to CHEESEBEE!");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Admin.adminLogin();
                    break;
                case 2:
                    Customer.customer();
                    break;
               case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please enter a valid option.");
                    break;
            }
        }
    }
}