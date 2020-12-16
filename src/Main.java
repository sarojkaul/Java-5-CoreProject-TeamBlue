import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String databaseUrl = "jdbc:mysql://localhost:3306/hotel_management_project";

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "");

        System.out.println("Connecting to database...");
        try (Connection connection = DriverManager.getConnection(databaseUrl, connectionProperties)) {
            CancellationRepository cancellationRepository = new CancellationRepository(connection);
            GuestRepo guestRepo = new GuestRepo(connection);
            MethodsForMenu methodsForMenu = new MethodsForMenu(connection);
            DataAccess dataAccess = new DataAccess(connection);

            Menu menu = new Menu(cancellationRepository, guestRepo, methodsForMenu, dataAccess);
            menu.printHeader();
            menu.display_menu();
            menu.execute_menu();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // connection.close() is called by Java before catch- and finally-blocks
            // https://stackoverflow.com/a/24129101
            System.out.println("Connection closed");
        }
    }
}
