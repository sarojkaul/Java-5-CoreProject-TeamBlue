public class PeterTests {

    static DataAccess DataAccess;

    public static void main(String[] args) {
       // Menu menu = new Menu();
        try {
            DataAccess = new DataAccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //menu.display_menu(DataAccess);
        try {
            DataAccess.closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
