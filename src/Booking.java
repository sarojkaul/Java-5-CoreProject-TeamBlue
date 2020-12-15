import java.time.LocalDate;
import java.util.Date;

public class Booking {

    private int bookingID;
    private int roomID;
    private int guestID;
    private Date checkInDate;
    private Date checkOutDate;

    public Booking(int bookingID, int roomID, int guestID, Date checkInDate, Date checkOutDate) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.guestID = guestID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Booking(int bookingID, int roomID, int guestID) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.guestID = guestID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getGuestID() {
        return guestID;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", roomID=" + roomID +
                ", guestID=" + guestID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
