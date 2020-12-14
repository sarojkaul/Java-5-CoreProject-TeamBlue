public class Guest {

    private int guestID;
    private String passportID;
    private String name;
    private String email;
    private String adress;
    private String phone;
    private String birthdate;

    // constructor
    public Guest(int guestID, String passportID, String name, String email, String adress, String phone, String birthdate) {
        this.guestID = guestID;
        this.passportID = passportID;
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    // getter | setter
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestID=" + guestID +
                ", passportID='" + passportID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
