import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class DataAccess {

    private final String url = "jdbc:mysql://localhost:3306/hotel_management_project";
    private final String user = "root";
    private final String password = "";
    private final Connection connection;

    public DataAccess() throws SQLException, ClassNotFoundException {
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(url, user, password);
    }



    public void closeDB() throws SQLException {
        System.out.println("Closing connection...");
        connection.close();
    }

    public ArrayList<Room> getAllRooms() {
        String sql = "SELECT * FROM room";
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int roomID = rs.getInt("Room_ID");
                int capacity = rs.getInt("Capacity");
                double size = rs.getDouble("Size");
                double price = rs.getDouble("Price");
                String description = rs.getString("Description");

                String category = rs.getString("Category");
                roomCategory cat = roomCategory.valueOf(category.toUpperCase());

                /*

                int has_wlan = rs.getInt("Has_Wlan");
                int has_coffee_machine = rs.getInt("Has_Coffee_Machine");
                int has_tv = rs.getInt("Has_TV");

                 */

                roomList.add(new Room(roomID, capacity, size, price, description, cat));
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<Booking> getAllBookings() {
        String sql = "SELECT * FROM booking";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int bookingID = rs.getInt("Booking_ID");
                int roomID = rs.getInt("Room_ID");
                int guestID = rs.getInt("Guest_ID");
                Date checkInDate = rs.getDate("Check_in_Date");
                Date checkOutDate = rs.getDate("Check_out_Date");



                bookingList.add(new Booking(bookingID, roomID, guestID, checkInDate, checkOutDate));
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public void displayAllRooms() {
        ArrayList<Room> roomList = getAllRooms();
        System.out.println("All Rooms:");
        for (Room room : roomList) {
            System.out.println(room);
        }
    }

    public void displayAllBookings() {
        ArrayList<Booking> bookingList = getAllBookings();
        System.out.println("All Booked Rooms :");
        for (Booking booking : bookingList) {
            System.out.println("Room_Id: " + booking.getRoomID()  + " | " + " rented by Guest_Id: "  +  booking.getGuestID() + " | " + " Check_In_date: " + booking.getCheckInDate() + " | " + " Check_out_date: " + booking.getCheckOutDate());
        }
    }

    public void displayAllFreeRooms() {
        ArrayList<Booking> bookingList = getAllBookings();
        ArrayList<Room> roomList = getAllRooms();
        for (Booking booking : bookingList) {
            try {
                roomList.remove(bookingList.indexOf(booking));
            } catch (Exception e) {
                e.getMessage();
            }
        }
        for (Room room : roomList) {
            System.out.println("ROOM_Id " + room.getRoomID() + " Category " + room.getCategory() + " Price: " + room.getPrice());
        }
    }

}
