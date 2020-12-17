import java.io.File;
import java.util.Scanner;

public class Menu {

    private final CancellationRepository cancellationRepository;
    private final GuestRepo guestRepo;
    private final MethodsForMenu methodsForMenu;
    private final DataAccess dataAccess;

    public Menu(CancellationRepository cancellationRepository, GuestRepo guestRepo, MethodsForMenu methodsForMenu, DataAccess dataAccess) {
        this.cancellationRepository = cancellationRepository;
        this.guestRepo = guestRepo;
        this.methodsForMenu = methodsForMenu;
        this.dataAccess = dataAccess;
    }

    public void display_menu() {
        System.out.println("1)  Display all Available Rooms");
        System.out.println("2)  Display all Booked_Rooms");
        System.out.println("3)  Display all cancelled_Reservations");
        System.out.println("4)  Add new Reservation");
        System.out.println("5)  Add new Guest");
        System.out.println("6)  Cancel Reservation");
        System.out.println("7)  All_Guests Detail");
        System.out.println("8)  Show Bookings of a Guest");
        System.out.println("9)  Display all rooms");
        System.out.println("10) Display Rooms which are booked for December");
        System.out.println("11) Create Report with all Guests");
        System.out.println("12) Update Booking");
        System.out.println("13) Exit");
    }

    public void execute_menu() {
        int n = 0;
        try {
            do {
                Scanner user_input = new Scanner(System.in);
                System.out.println("Enter Your choice: ");
                int x = user_input.nextInt();
                if (x >= 0 && x <= 13) {
                    switch (x) {
                        case 1: {
                            System.out.println("Display all Available Rooms");
                            dataAccess.displayAllFreeRooms();
                            break;
                        }
                        case 2: {
                            System.out.println("Display all Booked_Rooms");
                            dataAccess.displayAllBookings();
                            break;
                        }
                        case 3: {
                            displayAllCancelledReservations();
                            break;
                        }
                        case 4: {
                            System.out.println("****** These Rooms are Available *****");
                            dataAccess.displayAllFreeRooms();
                            methodsForMenu.Add_new_reservation();

                            break;
                        }
                        case 5: {
                            guestRepo.addNewGuest();
                            break;
                        }
                        case 6: {
                            methodsForMenu.All_booking();
                            methodsForMenu.cancellation();

                            break;
                        }
                        case 7: {
                            guestRepo.displayAllGuests();
                            break;
                        }
                        case 8: {
                            methodsForMenu.info_guest();
                            break;
                        }
                        case 9: {
                            methodsForMenu.All_rooms();
                            break;
                        }

                        case 10: {
                            methodsForMenu.rooms_12();
                            break;
                        }

                        case 11: {
                            createAllGuestReport();
                            break;
                        }
                        case 12: {
                            methodsForMenu.All_booking();
                            methodsForMenu.update_booking();
                            break;
                        }

                        case 13: {
                            System.out.println("Exit");
                            n = -2;
                        }

                    }
                } else {
                    System.out.println("Enter Valid number");
                }
            } while (n == 0);
            System.out.println("End");
        } catch (NumberFormatException e) {
            System.out.println("Enter numeric value");
        }
    }

    private void displayAllCancelledReservations() {
        cancellationRepository.displayAllCancellation();
    }

    private void createAllGuestReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the path of the output file: ");
        File outputFileName = new File(scanner.nextLine());
        guestRepo.createAllGuestReport(outputFileName);

    }

    public void printHeader() {
        System.out.println("*********************");
        System.out.println("*** Welcome  ********");
        System.out.println("***   to     ********");
        System.out.println("*** Hotel System ***");
        System.out.println("*********************");
    }

}

