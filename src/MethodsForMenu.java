import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class MethodsForMenu {

    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final String databaseUrl;
    private final Properties connectionProperties;

    public MethodsForMenu(String databaseUrl, Properties connectionProperties) {
        this.databaseUrl = databaseUrl;
        this.connectionProperties = connectionProperties;
    }

    public void Add_new_reservation() {
        //Step:3 Open a connection
        System.out.println("****** Add New Booking ********");

        // Query for display all students
        final String sql = "INSERT INTO `booking`(`Check_in_Date`, `Check_out_Date`, `Guest_ID`, `Room_ID`)" +
                " VALUES(?,?,?,?)";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Check_in Date :");
        String check_in_dateInput = scanner.nextLine();
        //change String into DATE datatype
        System.out.println(check_in_dateInput);

        LocalDate checkInDate = LocalDate.parse(check_in_dateInput, DATE_INPUT_FORMAT);
        System.out.println("Enter check_out_Date");
        String check_out_dateInput = scanner.nextLine();
        LocalDate check_out_date = LocalDate.parse(check_out_dateInput, DATE_INPUT_FORMAT);
        System.out.println("Enter Guest_Id: ");
        int guest_Id = scanner.nextInt();
        System.out.println("Enter Room_Id: ");
        int room_Id = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, Date.valueOf(checkInDate));
            preparedStatement.setDate(2, Date.valueOf(check_out_date));
            preparedStatement.setInt(3, guest_Id);
            preparedStatement.setInt(4, room_Id);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                // Should never happen
                System.err.println("Database did not return generated booking ID");
            } else {
                int generatedBookingId = generatedKeys.getInt(1);
                System.out.println("Saved booking with booking ID " + generatedBookingId);
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        }//end try

        System.out.println("Successfully updated!!");
    } // end Add_new_reservation

    public void All_rooms() {
        //Step:3 Open a connection
        System.out.println("****** All ROOMS********");
        try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties)) {
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT `Room_ID`,`Category`,`Price` FROM `room`";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int room_Id = set.getInt("Room_ID");
                String category = set.getString("Category");
                long price = set.getLong("Price");
                System.out.println("ROOM_Id " + room_Id + " Category " + category + " Price: " + price);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end All_rooms
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






