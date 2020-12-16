import java.sql.*;

public class GuestRepo {

    private final Connection connection;

    public GuestRepo(Connection connection) {
        this.connection = connection;
    }

    public void displayAllGuests() {
        try {
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