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

        System.out.println("Enter Check_in Date (\"MM/dd/yyyy\") :");

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
    }
        // end All_rooms
        public void cancellation () {

                System.out.println("****** Cancel Booking ********");

                String sql;
                // Query for display all students
                sql = "INSERT INTO `cancellation`(`Cancellation_ID`, `Cancellation_Date`, `Booking_ID`) VALUES(?,?,?)";

                Scanner scanner = new Scanner(System.in);
            System.out.println("Enter cancelation_ID: " );
            int id = scanner.nextInt();
            scanner.nextLine();
                System.out.println("Enter cancellation date(\"MM/dd/yyyy\") : ");
               String cancellation_date = scanner.nextLine();
                LocalDate Cancellation_date = LocalDate.parse(cancellation_date, DATE_INPUT_FORMAT);
                System.out.println(Cancellation_date);
                System.out.println("Enter Booking_ID want to cancel: ");
                int booking_id = scanner.nextInt();
            try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                preparedStatement.setInt(1,id);
               preparedStatement.setDate(2, Date.valueOf(cancellation_date));
                preparedStatement.setInt(3, booking_id);
                preparedStatement.executeUpdate();

                ResultSet generatedkey = preparedStatement.getGeneratedKeys();
                if(!generatedkey.next()){
                    System.err.println("Database did not return generated booking ID");
                } else {
                    int generatedCancellationId = generatedkey.getInt(1);
                    System.out.println("Saved Cancellation with cancellation_ID" + generatedCancellationId);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void All_booking(){
            System.out.println("****** All_Bookings Detail *******");
            try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties))
            {
                Statement statement = connection.createStatement();
                String sql;
                // Query for display all students
                sql = "SELECT booking.Booking_ID, " +
                        "booking.Check_in_Date, booking.Check_out_Date, " +
                        "booking.Guest_ID,guest.NAME,guest.Surname,booking.Room_ID " +
                        "FROM booking INNER JOIN guest on booking.Guest_ID = guest.GuestID";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    int booking_ID = resultSet.getInt("Booking_ID");
                    Date checkin_Date = resultSet.getDate("Check_in_Date");
                    Date check_outdate = resultSet.getDate("Check_out_Date");
                    int guest_Id = resultSet.getInt("Guest_ID");
                    String guest_name = resultSet.getString("NAME");
                    String surname = resultSet.getString("Surname");
                    int Room_ID = resultSet.getInt("Room_ID");
                    System.out.println("Booking_Id: " +booking_ID +" |" +" Check_in: " +checkin_Date + " | "+" Check_out: " +check_outdate
                            +" | " +" Guest_Id: " +guest_Id +" | "+" Booked by: " +guest_name +" | "+" Surname: " +surname +" | "+" Booked Room_Id: " +Room_ID);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }









