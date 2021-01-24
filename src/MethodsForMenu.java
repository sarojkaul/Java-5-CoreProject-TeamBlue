import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MethodsForMenu {

    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final Connection connection;

    public MethodsForMenu(Connection connection) {
        this.connection = connection;
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
        try {
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT `Room_ID`,`Category`,`Price` FROM `room`";
            ResultSet set = statement.executeQuery(sql);
            System.out.println("Room ID | Category  | Price ");
            System.out.println("--------+-----------+---------");
            while (set.next()) {
                int room_Id = set.getInt("Room_ID");
                String category = set.getString("Category");
                double price = set.getLong("Price");
                System.out.printf("%5d   | %10s | %7.2f%n" ,
                        room_Id, category, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // end All_rooms
    public void cancellation() {

        System.out.println("****** Cancel Booking ********");

        String sql = "INSERT INTO `cancellation`(`Cancellation_Date`, `Booking_ID`) VALUES(?,?)";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Booking_ID want to cancel: ");
        int booking_id = scanner.nextInt();
        // Hack to consume the rest of the current line before reading the cancellation date.
        // To see the difference, enter "1 999999" for the booking id
        scanner.nextLine();

        System.out.println("Enter cancellation date(\"MM/dd/yyyy\") : ");
        String cancellation_date = scanner.nextLine();
        LocalDate Cancellation_date = LocalDate.parse(cancellation_date, DATE_INPUT_FORMAT);
        System.out.println(Cancellation_date);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, Date.valueOf(Cancellation_date));
            preparedStatement.setInt(2, booking_id);
            preparedStatement.executeUpdate();

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (!generatedKey.next()) {
                System.err.println("Database did not return generated booking ID");
            } else {
                int generatedCancellationId = generatedKey.getInt(1);
                System.out.println("Saved Cancellation with cancellation_ID " + generatedCancellationId);

            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Booking with ID " + booking_id + "is already cancelled");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void All_booking() {
        System.out.println("****** All_Bookings Detail *******");
        try {
            Statement statement = connection.createStatement();
            String sql =
                    "SELECT booking.Booking_ID, " +
                            " booking.Check_in_Date, booking.Check_out_Date, " +
                            " booking.Guest_ID,guest.NAME,guest.Surname,booking.Room_ID," +
                            " cancellation.Cancellation_Date " +
                            "FROM booking" +
                            " JOIN guest ON booking.Guest_ID = guest.GuestID" +
                            " LEFT JOIN cancellation ON cancellation.Booking_ID = booking.Booking_ID";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Booking ID | Check-in date | Check-out date | Cancellation Date | Guest ID | Booked by            | Surname              | Booked Room ID ");
            System.out.println("-----------+---------------+----------------+-------------------+----------+----------------------+----------------------+----------------");
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("Booking_ID");
                Date checkInDate = resultSet.getDate("Check_in_Date");
                Date checkOutDate = resultSet.getDate("Check_out_Date");
                int guestId = resultSet.getInt("Guest_ID");
                String guestName = resultSet.getString("NAME");
                String surname = resultSet.getString("Surname");
                int roomId = resultSet.getInt("Room_ID");
                Date cancellationDate = resultSet.getDate("Cancellation_Date");

                // a place holder for a number from argument list, %s is for an object, toString() method to convert, object.to String is to the null object is not shown

                System.out.printf("%10d | %13s | %14s | %17s | %8d | %20s | %20s | %14d%n",
                        bookingId, checkInDate, checkOutDate, Objects.toString(cancellationDate, ""), guestId, guestName, surname, roomId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rooms_12() {
        System.out.println("***** Display all Rooms which are booked For December *****");
        try {
            Statement statement = connection.createStatement();
            String sql;
            sql = "SELECT Room_ID, Check_in_Date, Check_out_Date FROM booking" +
                    " WHERE booking.Check_in_Date BETWEEN \"2020-12-01\" AND \"2020-12-31\"";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Room_ID | Check-in date | Check-out date ");
            System.out.println("--------+---------------+---------------");
            while (resultSet.next()) {
                int room_id = resultSet.getInt("Room_ID");
                Date check_In_date = resultSet.getDate("Check_in_Date");
                Date check_out_date = resultSet.getDate("Check_out_Date");
                System.out.printf("%7d | %13s | %14s%n",
                        room_id, check_In_date, check_out_date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void info_guest() {
        System.out.println("***** Show Bookings of a Guest *****");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Guest Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Surname: ");
            String surname = scanner.nextLine();

            String sql;
            sql = "SELECT guest.NAME,guest.Surname, booking.Room_ID, guest.City,guest.Telephone_Number" +
                    " FROM guest INNER JOIN " +
                    "booking ON booking.Guest_ID = guest.GuestID " +
                    "WHERE guest.NAME = ? AND guest.Surname = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Guest> guests = new ArrayList<>();
            System.out.println("Name       | Surname    | Room_no. | City       |  Phone Number ");
            System.out.println("-----------+------------+----------+------------+----------------");
            while (resultSet.next()) {
                guests.add(new Guest(name, surname));
                int room = resultSet.getInt("Room_ID");
                String City = resultSet.getString("City");
                String number = resultSet.getString("Telephone_Number");

                System.out.printf("%10s | %10s | %8s | %10s | %14s%n",
                        name, surname, room, City, number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void update_booking(){
            System.out.println("****** Udate Booking *******");
            String sql = "UPDATE `booking` SET `Check_in_Date`= ?," +
                    "`Check_out_Date`=?" +
                    "WHERE Booking_ID = ?";
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Booking Id want to update : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new Check_In date\"MM/dd/yyyy\"");
                String check_In = scanner.nextLine();
                LocalDate Check_In = LocalDate.parse(check_In,DATE_INPUT_FORMAT);
                System.out.println(Check_In);
                System.out.println("Enter new check_out date: ");
                String check_out = scanner.nextLine();
                LocalDate Check_out = LocalDate.parse(check_out,DATE_INPUT_FORMAT);
                System.out.println(Check_out);
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDate(1, Date.valueOf(Check_In));
                statement.setDate(2, Date.valueOf(Check_out));
               statement.setInt(3,id);

                statement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
        }
            System.out.println(" Sucessfully updated!!!!!");
    }
    public void check_availability() {
        System.out.println("***** Check room availability *******");
        String sql = "SELECT room.Room_ID, room.Size, room.Description," +
                " room.Price FROM room WHERE room.Room_ID NOT IN" +
                "( SELECT booking.Room_ID FROM booking " +
                "WHERE booking.Check_in_Date AND booking.Check_out_Date " +
                "BETWEEN ? AND ?)";
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Check_in date");
            String check_in = scanner.nextLine();
            LocalDate Check_In = LocalDate.parse(check_in,DATE_INPUT_FORMAT);
            System.out.println(Check_In);
            System.out.println("Enter Check_out Date");
            String check_out = scanner.nextLine();
            LocalDate Check_out_date = LocalDate.parse(check_out,DATE_INPUT_FORMAT);
            System.out.println(Check_out_date);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(Check_In));
            preparedStatement.setDate(2,Date.valueOf(Check_out_date));
            int count = 0;
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("***** All Available Rooms  ****");
            while (rs.next()){
                count++;

                System.out.println("Room_id: " +rs.getInt("Room_ID") +" | " +" Category: " +rs.getString("Description") +" | "+" Price: " +rs.getBigDecimal("Price"));
            }
            if(count ==0){
                System.out.println("Sorry! No room Available");
            }
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
    /*public void userPassword(){
        try{
            System.out.println("Enter UserName: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();
            PreparedStatement passwordDatabase = connection.prepareStatement("SELECT login.PASSWORD1 from login");
             ResultSet set = passwordDatabase.executeQuery();
             PreparedStatement userName = connection.prepareStatement("SELECT username FROM login");
             if(passwordDatabase.equals(password)){


             }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }*/

}









