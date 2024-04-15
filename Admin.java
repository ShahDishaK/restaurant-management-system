package package1;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;


public class Admin {
    static Scanner sc = new Scanner(System.in);
    static Menu menu=new Menu();
       
    public static void adminLogin() throws Exception {
        String driverName = "com.mysql.cj.jdbc.Driver";
       // System.out.println("Driver loaded successfully");
        String dburl = "jdbc:mysql://localhost:3306/restaurant";
        String dbuser = "root";
        String dbpass = "dishansh";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        // if (con != null) {
        //     System.out.println("Connection successful");
        // } else {
        //     System.out.println("Connection failed");
        // }
        java.sql.Statement st = con.createStatement();
        String username = "Admin";
        String pass = "1234";
        System.out.print("Enter username=");
        String entered_username = sc.next();
        while (true) {
            if (entered_username.equals(username)) {
                break;
            } else {
                System.out.println("Invalid username, please enter again!");
                entered_username = sc.next();
            }
        }
        System.out.println("Enter password=");
        String entered_pass = sc.next();
        while (true) {
            if (entered_pass.equals(pass)) {
                break;
            } else {
                System.out.println("Invalid password, please enter again!");
                entered_pass = sc.next();
            }
        }
       
        while (true) {
            System.out.println("\t\t\t\t\t_____________________");
            System.out.println("\t\t\t\t\t||1:Add Menu Item     ||");
            System.out.println("\t\t\t\t\t||2:View Reservation  ||");
            System.out.println("\t\t\t\t\t||3:View Bill file    ||");
            System.out.println("\t\t\t\t\t||4:View Menu         ||");
            System.out.println("\t\t\t\t\t||5:Exit              ||");
            System.out.println("\t\t\t\t\t______________________");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    // Add Menu Items
                    Menu.display();
                    System.out.println("Enter Menu Item ID");
                    int itemId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Menu Item Name");
                    String itemName = sc.nextLine();
                    System.out.print("Enter Menu Item Description: ");
                    String itemDescription = sc.nextLine();
                    System.out.print("Enter Menu Item Price: ");
                    double itemPrice = sc.nextDouble();
                    menu.addItem(new MenuItem(itemId, itemName, itemDescription, itemPrice));
                    System.out.println("Menu Item added successfully!");
                    break;
                    case 2:
                    // View Reservations
                     String seeDate;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               while (true) {
                try{
                System.out.println("Enter the date you want to see the reservation of(yyyy-mm-dd)");
                seeDate=sc.next();
            
               LocalDate reservationDate1 = LocalDate.parse(seeDate, formatter);

            }catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                sc.nextLine();
                continue;}
             System.out.println("\nList of Reservations:");
                String sql2 = "SELECT * FROM reservation1 where reservation_date=?";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                pst2.setString(1,seeDate);
                ResultSet rs2 = pst2.executeQuery();boolean found=true;
            while (rs2.next()) {
             int reservationID = rs2.getInt("reservation_id");
             String customerName1 = rs2.getString("customer_name");
             String contactNumber1 = rs2.getString("customer_number");
             String reservationDate1 = rs2.getString("reservation_date");
             int numberOfGuest1 = rs2.getInt("no_og_guest");
             System.out.println("Reservation ID: " + reservationID);
             System.out.println("Customer Name: " + customerName1);
             System.out.println("Contact Number: " + contactNumber1);
             System.out.println("Reservation Date: " + reservationDate1);
             System.out.println("Number of Guests: " + numberOfGuest1);
             found=false;
             System.out.println();
             }
             if(found)
             {
                System.out.println("No reservation on this date");
             }
              break;}
              break;
              case 3:
              BufferedReader readBill=new BufferedReader(new FileReader("D://bill.txt"));
              String i=readBill.readLine();
              while(i!=null)
              {
                System.out.println(i);
                i=readBill.readLine();
              }
              break;
              case 4:
              Menu.display();
              break;
              case 5:
              System.out.println("Exiting Admin side");
              Main.mainMenu();
                            break;
              default:
              System.out.println("Not valid Choice");
        }
    }
}
}