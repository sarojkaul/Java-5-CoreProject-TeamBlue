import java.sql.*;
import java.util.Properties;

public class GuestRepo {
    private final String databaseUrl;
    private final Properties connectionProperties;

    public GuestRepo(String databaseUrl, Properties connectionProperties) {
        this.databaseUrl = databaseUrl;
        this.connectionProperties = connectionProperties;
    }

    public void displayAllGuests() {
        try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties)) {

            String sql = "SELECT\n" +
                    "    GuestID,\n" +
                    "    NAME,\n" +
                    "    Surname,\n" +
                    "    Telephone_Number,\n" +
                    "    Gender,\n" +
                    "    Nationality\n" +
                    "FROM\n" +
                    "    guest";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    int Guest_ID = resultSet.getInt("GuestID");
                    String Telephone_Number = resultSet.getString("Telephone_Number");
                    String Gender = resultSet.getString("Gender");
                    String Nationality = resultSet.getString("Nationality");
                    System.out.println(String.join(" | ", "" + Guest_ID, Name, Surname, Gender,
                            "" + Telephone_Number, Nationality));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}