package package1;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

import com.mysql.cj.protocol.Resultset;


public class Customer {
    public static void customer() throws Exception{
         Scanner sc = new Scanner(System.in);
          Menu menu = new Menu();
        ArrayDeque<Order> orders = new ArrayDeque<>();
        Hashtable<Integer, List<Order>> ordersByReservation = new Hashtable<>();
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
        while(true){
        System.out.println("\t\t\t\t\t_____________________");
        System.out.println("\t\t\t\t\t||1:Make Reservation  ||");
        System.out.println("\t\t\t\t\t||2:cancel Reservation||");
        System.out.println("\t\t\t\t\t||3:Place Order       ||");
        System.out.println("\t\t\t\t\t||4:Generate Bill     ||");
        System.out.println("\t\t\t\t\t||5:View Menu         ||");
        System.out.println("\t\t\t\t\t||6:Exit              ||");
        System.out.println("\t\t\t\t\t______________________");
        System.out.println("Enter Your Choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
                case 1:
                    // Make Reservation
                    /*
                     * CREATE DEFINER=`root`@`localhost` PROCEDURE `reservationInsert`(out CUST_ID int,in CUST_NAME varchar(60),in CUST_NUMBER VARCHAR(10),IN CUST_DATE DATE,IN CUST_GUEST INT)
                       BEGIN
                       INSERT INTO reservation1(customer_name,customer_number,reservation_date,no_of_guest) values (CUST_NAME,CUST_NUMBER,CUST_DATE,CUST_GUEST);
                       SELECT reservation_id into CUST_ID from reservation1 where customer_name=CUST_NAME;
                       END
                     */
                    long contactNumber;
                    String sql = "{call reservationInsert(?,?,?,?,?)}  ";
                    CallableStatement cst = con.prepareCall(sql);
                    System.out.print("Enter your name: ");
                    String customerName = sc.nextLine();
                    String reservationDate;
                    while (true) {
                        try{
                        System.out.print("Enter your contact number: ");
                        contactNumber = sc.nextLong();
                        String contactNumberLength = String.valueOf(contactNumber);
                        if (contactNumberLength.length() == 10) {
                            break;
                        } else {
                            System.out.println("Not a valid number of 10 digits");
                            continue;
                        }
                    }catch(Exception e)
                    {
                        System.out.println("ENTER DIGITS ONLY!!!!");
                        sc.nextLine();
                        continue;

                    }
                    }sc.nextLine();
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    while (true) {
                        System.out.print("Enter reservation date (YYYY-MM-DD): ");
                        reservationDate = sc.nextLine();
                        try {
                            LocalDate reservationDate1 = LocalDate.parse(reservationDate, formatter);
                            if (reservationDate1.isAfter(currentDate) || reservationDate1.isEqual(currentDate)) {
                                break;
                            } else {
                                System.out.println("Entered date is before the current date.");
                                
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        }
                    }
                    System.out.print("Enter number of guests: ");
                    int numberOfGuests = sc.nextInt();
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setString(2, customerName);
                    cst.setLong(3, contactNumber);
                    cst.setString(4, reservationDate);
                    cst.setInt(5, numberOfGuests);
                    int i = cst.executeUpdate();
                    if (i != -1) {
                        System.out.println("Reservation confirmed!! Your reservation id is: " + cst.getInt(1));
                    }
                    break;
                    case 2:
                    System.out.println("Are You Sure That You Want To Cancel Your Reservation(yes/no)");
                    String cancelReservation=sc.next();
                    if(cancelReservation.equalsIgnoreCase("yes"))
                    {
                        System.out.println("Enter Your Reservation Id");
                        int enterdId=sc.nextInt();
                        System.out.println("Enter name");
                        String enteredName=sc.next();
                        String sqlIdVarification="select reservation_id from reservation1";
                        PreparedStatement pstId=con.prepareStatement(sqlIdVarification);
                        ResultSet rsId=pstId.executeQuery();
                       int flag=0;
                        while(rsId.next())
                        {

                        String sqlCacelReservation="delete from reservation1 where reservation_id=? and customer_name=?";
                        PreparedStatement pstDelete= con.prepareStatement(sqlCacelReservation);
                        pstDelete.setInt(1,enterdId);
                        pstDelete.setString(2,enteredName);
                        int iDelete=pstDelete.executeUpdate();
                        if(iDelete>0)
                        {flag++;
                            System.out.println("Reservation deleted successfully");
                        }  
                        }
                        if(flag==0)
                        {
                            
                            System.out.println("No reservation found at this id or name");
                        
                        }
                    }
                    else
                    {
                        System.out.println("Reservation not canceled");
                    }
                    break;
                     case 3:
                    // Place Order
                    System.out.println("Enter your reservation id");
                    int enteredId = sc.nextInt();
                    int flag = 0;
                    String check = "select reservation_id,reservation_date from reservation1";
                    PreparedStatement pst1 = con.prepareStatement(check);
                    ResultSet rs = pst1.executeQuery();
                    LocalDate today = LocalDate.now();
                    LocalDate reservationDate1=null;
                    while (rs.next()) {
                        if (enteredId == rs.getInt(1)) {
                             reservationDate1  = rs.getDate("reservation_date").toLocalDate();

                            flag = 1;
                        }
                    }
                    if (flag == 1) {
                        if (today.equals(reservationDate1)) {
                            System.out.println("Reservation ID matches today's date.");
                        Menu.display();
                        Order order = new Order();
                        while (true) {
                            System.out.print("Enter the ID of the menu item you want to order type 0 to finish: ");
                            int selectedItemId = sc.nextInt();
                            if (selectedItemId == 0) {
                                break;
                            }
                            MenuItem selectedMenuItem = null;
                            for (MenuItem menuItem : menu.getMenuItems()) {
                                if (menuItem.getId() == selectedItemId) {
                                    selectedMenuItem = menuItem;
                                    break;
                                }
                            }
                            if (selectedMenuItem == null) {
                                System.out.println("Invalid item ID. Please try again.");
                                continue;
                            }
                            System.out.print("Enter the quantity: ");
                            int quantity = sc.nextInt();
                            order.addItem(selectedMenuItem, quantity);
                            System.out.println("Item added to order.");
                        }
                        // Add the order to the list of orders for the reservation ID
                        if (!ordersByReservation.containsKey(enteredId)) {
                            ordersByReservation.put(enteredId, new ArrayList<>());
                        }
                        ordersByReservation.get(enteredId).add(order);
                        System.out.println("Order placed successfully!");
                    }
                    else{
                        System.out.println("No reservation for today");
                    }
                    }
                    else
                    {
                        System.out.println("No reservation for this id");
                    }

                    break;
              
                case 4:
                    // Bill
                    try {
                        int reservationId=0;
                        System.out.println("Enter Reservation Id");
                         reservationId = sc.nextInt();
                        double totalBill = 0;
                        boolean found = false;
                        BufferedWriter writeBill=new BufferedWriter(new FileWriter("D://bill.txt",true));
                        if (ordersByReservation.containsKey(reservationId)) {
                            List<Order> ordersForReservation = ordersByReservation.get(reservationId);
                            for (Order o : ordersForReservation) {
                                if (!o.getItems().isEmpty()) {
                                    totalBill += o.calculateTotalBill();
                                    found = true;
                                }
                            }
                            if (found) 
                            {
                                System.out.println("\nTotal Bill for Reservation ID " + reservationId + ":");
                                writeBill.write("reservation id="+reservationId);
                                writeBill.newLine();
                                for (Order o : ordersForReservation) {
                                    if (!o.getItems().isEmpty()) {
                                        for (MenuItem item : o.getItems()) {
                                            String itemName=item.getName();
                                            writeBill.write("ordered item="+itemName);
                                            writeBill.newLine();
                                            int quantitiesOrder=o.getQuantities().pop();
                                            writeBill.write(quantitiesOrder);
                                            writeBill.newLine();
                                            double price=item.getPrice();
                                            writeBill.write("price="+price);
                                            writeBill.newLine();
                                            System.out.println( itemName+ " (x" +quantitiesOrder  + ") - rupees" +price );
                                            writeBill.flush();
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("No order Placed");
                            }
                            System.out.println("Total Bill="+totalBill);
                            writeBill.write("total bill="+totalBill);
                            writeBill.newLine();
                            writeBill.newLine();
                            writeBill.newLine();
                            writeBill.flush();
                            writeBill.close();
                            Payment.payment(totalBill);
                        }
                        else{
                            System.out.println(reservationId+" id dosent exists");
                        }
                     }
                    catch(Exception e)
                    {
                        System.out.println("BILL FOR THIS ID IS ALREADY GIVEN");

                    }
                    break;
                    case 5:
                //view Menu
                Menu.display();
                break;
                case 6:
                System.out.println("Exiting the Customer Portal. Goodbye!");
                Main.mainMenu();
            default:
                System.out.println("Invalid choice, please enter a valid option.");
                break;
        }
    }

    }

}
