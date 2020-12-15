import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class CancellationRepository {
    private String databaseUrl;
    private Properties connectionProperties;

    public CancellationRepository(String databaseUrl, Properties connectionProperties) {
        this.databaseUrl = databaseUrl;
        this.connectionProperties = connectionProperties;
    }

    public void displayAllCancellation() {
        try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties)) {
            String sql = "SELECT\n" +
                    "    cancellation.Cancellation_ID,\n" +
                    "    cancellation.Cancellation_Date,\n" +
                    "    cancellation.Booking_ID,\n" +
                    "    booking.Check_in_Date,\n" +
                    "    booking.check_out_date,\n" +
                    "    guest.NAME,\n" +
                    "    guest.Surname,\n" +
                    "    booking.Room_ID\n" +
                    "FROM\n" +
                    "    cancellation\n" +
                    "JOIN booking ON booking.Booking_ID = cancellation.Booking_ID\n" +
                    "JOIN guest ON guestID = booking.Guest_ID";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    int Booking_ID = resultSet.getInt("Booking_ID");
                    LocalDate Check_in_date = resultSet.getDate("Check_in_Date").toLocalDate();
                    LocalDate Check_out_date = resultSet.getDate("Check_out_Date").toLocalDate();
                    int Cancellation_ID = resultSet.getInt("Cancellation_ID");
                    LocalDate Cancellation_Date = resultSet.getDate("Cancellation_Date").toLocalDate();
                    int Room_ID = resultSet.getInt("Room_ID");

                    System.out.println(String.join(" | ", "" + Booking_ID, Name, Surname, "" + Room_ID,
                            Check_in_date.toString(), Check_out_date.toString(), Cancellation_Date.toString()));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
