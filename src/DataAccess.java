import java.sql.*;
import java.util.ArrayList;

public class DataAccess {

    private final Connection connection;

    public DataAccess(Connection connection) {
        this.connection = connection;
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
            System.out.println("Room_Id: " + booking.getRoomID() + " | " + " rented by Guest_Id: " + booking.getGuestID() + " | " + " Check_In_date: " + booking.getCheckInDate() + " | " + " Check_out_date: " + booking.getCheckOutDate());
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

        printListOfFreeRooms(roomList);
    }

    public static void printListOfFreeRooms(ArrayList<Room> roomList) {
        System.out.println("Room ID | Category   | Price");
        System.out.println("--------+------------+-----------");
        for (Room room : roomList) {
            System.out.printf("%5d   | %10s | %7.2f%n",
                        room.getRoomID(), room.getCategory(), room.getPrice());
        }
    }

}
