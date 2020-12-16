import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/hotel_management_project";
        //String url = "jdbc:mysql://localhost:3306/hotel_management_project";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        CancellationRepository cancellationRepository = new CancellationRepository(url, properties);
        GuestRepo guestRepo = new GuestRepo(url, properties);
        MethodsForMenu methodsForMenu = new MethodsForMenu(url, properties);

        Menu menu = new Menu(cancellationRepository, guestRepo, methodsForMenu);
        menu.printHeader();
        menu.display_menu();
        menu.execute_menu();

    }
}
