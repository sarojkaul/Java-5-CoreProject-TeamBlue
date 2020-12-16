import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GuestRepo {

    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

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

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

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

    public void createAllGuestReport(File outputFilePath) {
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

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery();
                 FileWriter writer = new FileWriter(outputFilePath)) {

                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    int Guest_ID = resultSet.getInt("GuestID");
                    String Telephone_Number = resultSet.getString("Telephone_Number");
                    String Gender = resultSet.getString("Gender");
                    String Nationality = resultSet.getString("Nationality");
                    writer.write(String.join(" | ", "" + Guest_ID, Name, Surname, Gender,
                            "" + Telephone_Number, Nationality));
                    writer.write('\n');
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public int addCreditCard() throws SQLException {
        System.out.println("****** Add Credit Card ******");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Credit Card Holder Name");
        String payee_name = scanner.nextLine();
        System.out.println(payee_name);

        System.out.println("Please enter Credit Card Number");
        String credit_card_number = scanner.nextLine();
        System.out.println(credit_card_number);

        System.out.println("Please enter CVC");
        int cvc = scanner.nextInt();
        System.out.println(cvc);

        System.out.println("Please enter Expiry Month");
        int expiry_month = scanner.nextInt();
        System.out.println(expiry_month);

        System.out.println("Please enter Expiry Year");
        int expiry_year = scanner.nextInt();
        System.out.println(expiry_year);

        final String sql = "INSERT INTO `credit_card`(" +
                "Payee_Name,\n" +
                "Credit_Card_Number,\n" +
                "CVC,\n" +
                "Expiry_Month,\n" +
                "Expiry_Year\n) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, payee_name);
            preparedStatement.setString(2, credit_card_number);
            preparedStatement.setInt(3, cvc);
            preparedStatement.setInt(4, expiry_month);
            preparedStatement.setInt(5, expiry_year);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new RuntimeException("Database did not return generated credit card ID");
            } else {
                return generatedKeys.getInt(1);
            }
        }
    }

    public void addNewGuest() {

        System.out.println("****** Add A New Guest ********");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Guest Name");
        String guest_name = scanner.nextLine();
        System.out.println(guest_name);

        System.out.println("Please enter Guest Surname");
        String guest_surname = scanner.nextLine();
        System.out.println(guest_surname);

        System.out.println("Please enter Guest's Email");
        String email = scanner.nextLine();
        System.out.println(email);

        System.out.println("Please enter Guest's Telephone_Number");
        String Telephone_Number = scanner.nextLine();
        System.out.println(Telephone_Number);

        System.out.println("Please enter Guest's Street");
        String Street = scanner.nextLine();
        System.out.println(Street);

        System.out.println("Please enter Guest's Building_Number");
        String Building_Number = scanner.nextLine();
        System.out.println(Building_Number);

        System.out.println("Please enter Guest's Post_Code");
        String Post_Code = scanner.nextLine();
        System.out.println(Post_Code);

        System.out.println("Please enter Guest's City");
        String City = scanner.nextLine();
        System.out.println(City);

        System.out.println("Please enter Guest's Country");
        String country = scanner.nextLine();
        System.out.println(country);

        System.out.println("Please enter Guest's Passport_ID");
        String Passport_ID = scanner.nextLine();
        System.out.println(Passport_ID);

        System.out.println("Please enter Guest's Gender");
        String Gender = scanner.nextLine();
        System.out.println(Gender);

        System.out.println("Please enter Guest's Nationality");
        String nationality = scanner.nextLine();
        System.out.println(nationality);

        System.out.println("Please enter Guest's Birthday");
        String birthdayInput = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayInput, DATE_INPUT_FORMAT);

        final String sql = "INSERT INTO `guest`(" +
                "NAME,\n" +
                "Surname,\n" +
                "Email,\n" +
                "Telephone_Number,\n" +
                "Street,\n" +
                "Building_Number,\n" +
                "Post_Code,\n" +
                "City,\n" +
                "Country,\n" +
                "Passport_ID,\n" +
                "Gender,\n" +
                "Nationality,\n" +
                "Birthday,\n" +
                "Credit_Card_ID\n) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, guest_name);
            preparedStatement.setString(2, guest_surname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, Telephone_Number);
            preparedStatement.setString(5, Street);
            preparedStatement.setString(6, Building_Number);
            preparedStatement.setString(7, Post_Code);
            preparedStatement.setString(8, City);
            preparedStatement.setString(9, country);
            preparedStatement.setString(10, Passport_ID);
            preparedStatement.setString(11, Gender);
            preparedStatement.setString(12, nationality);
            preparedStatement.setDate(13, Date.valueOf(birthday));
            preparedStatement.setInt(14, addCreditCard());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                // Should never happen
                System.err.println("Database did not return generated guest ID");
            } else {
                int generatedBookingId = generatedKeys.getInt(1);
                System.out.println("Saved booking with guest ID " + generatedBookingId);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();

        }//end try

        System.out.println("Guest Added!!");
    } // end Add_new_reservation)
}