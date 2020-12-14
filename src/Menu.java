import java.util.Scanner;

public class Menu {
    private final CancellationRepository cancellationRepository;
    private final GuestRepo guestRepo;

    public Menu(CancellationRepository cancellationRepository, GuestRepo guestRepo) {
        this.cancellationRepository = cancellationRepository;
        this.guestRepo = guestRepo;
    }

    public void display_menu() {
        System.out.println("1) Display all Available Rooms");
        System.out.println("2) Display all Booked_Rooms");
        System.out.println("3) Display all cancelled_Reservations");
        System.out.println("4) Add new Reservation");
        System.out.println("5) Cancel Reservation");
        System.out.println("6) All_Guests Detail");
        System.out.println("7) Exit");
    }

    public void execute_menu() {
        int n = 0;
        try {
            do {
                Scanner user_input = new Scanner(System.in);
                System.out.println("Enter Your choice: ");
                int x = user_input.nextInt();
                if (x >= 0 && x <= 7) {
                    switch (x) {
                        case 1: {
                            System.out.println("Display all Available Rooms");
                            break;
                        }
                        case 2: {
                            System.out.println("Display all Booked_Rooms");
                            break;
                        }
                        case 3: {
                            displayAllCancelledReservations();
                            break;
                        }
                        case 4: {
                            System.out.println("Add new Reservation");
                            break;
                        }
                        case 5: {
                            System.out.println("Cancel Reservation");
                            break;
                        }
                        case 6: {
                            guestRepo.displayAllGuests();
                            break;
                        }
                        case 7: {
                            System.out.println("Exit");
                            n = -1;
                        }

                    }
                } else {
                    System.out.println("Enter Valid number");
                }
            } while (n >= 0);
            System.out.println("End");
        } catch (NumberFormatException e) {
            System.err.println("Please enter a number!");
        }
        System.out.println("Enter numeric value");
    }

    private void displayAllCancelledReservations() {
        cancellationRepository.displayAllCancellation();
    }

    public void printHeader() {
        System.out.println("*********************");
        System.out.println("*** Welcome  ********");
        System.out.println("***   to     ********");
        System.out.println("*** Hotel System ***");
        System.out.println("*********************");
    }
}

