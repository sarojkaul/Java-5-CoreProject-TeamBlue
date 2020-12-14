import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hotel_management_team_blue";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        CancellationRepository cancellationRepository =
                new CancellationRepository(url, properties);

        GuestRepo guestRepo = new GuestRepo(url, properties);

        Menu menu = new Menu(cancellationRepository, guestRepo);
        menu.printHeader();
        menu.display_menu();
        menu.execute_menu();

    }
}
