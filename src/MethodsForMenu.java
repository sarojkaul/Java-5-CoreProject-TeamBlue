import javax.xml.soap.Text;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MethodsForMenu {

    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/hotel_management_project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    Connection connection = null;

    public void Add_new_reservation(){
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** Add New Booking ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "INSERT INTO `booking`(`Booking_ID`,`Check_in_Date`, `Check_out_Date`, `Guest_ID`, `Room_ID`)VALUES(?,?,?,?,?)";


            Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Booking Id: ");
                int booking_Id = scanner.nextInt();

                System.out.println("Enter Check_in Date :");
               String check_in_date = scanner.nextLine();
               //change String into DATE datatype
            scanner.nextLine();
            System.out.println(check_in_date);

                Date check_in_date1 = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(check_in_date);
                System.out.println("Enter check_out_Date");
                String check_out_date = scanner.nextLine();
                Date check_out_date1 = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(check_out_date);
                System.out.println("Enter Guest_Id: ");
                int guest_Id = scanner.nextInt();
                System.out.println("Enter Room_Id: ");
                int room_Id = scanner.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,booking_Id);
                preparedStatement.setDate(2, check_in_date1);
                preparedStatement.setDate(3, check_out_date1);
                preparedStatement.setInt(4,guest_Id);
                preparedStatement.setInt(5,room_Id);
                 preparedStatement.executeUpdate();

        } catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();

        }//end try
        System.out.println("Successfully updated!!");
    }//end main*/


    public void All_rooms() {
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** All ROOMS********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT `Room_ID`,`Category`,`Price` FROM `room`";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int room_Id= set.getInt("Room_ID");
                String category = set.getString("Category");
                long price = set.getLong("Price");
                System.out.println("ROOM_Id " + room_Id +" Category " +category +" Price: " +price);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*public void cancellation(){
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** Add New Booking ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "INSERT INTO `cancellation`(`Cancellation_ID`, `Cancellation_Date`, `Booking_ID`) VALUES(?,?,?)";

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Cancellation Id: ");
            int cancellation_Id = scanner.nextInt();
            System.out.println("Enter cancellation date: ");

            System.out.println("Enter Booking_ID: ");

        }*/

}






