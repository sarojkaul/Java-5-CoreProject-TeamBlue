import java.util.Date;

public class Guest {

    private int guestID;
    private String name;
    private String surname;
    private String gender;
    private String email;
    private String adress;
    private String phone;
    private String passportID;
    private String nationality;
    private Date birthdate;

    // constructor
    public Guest(int guestID, String name, String surname, String gender, String email, String adress, String phone, String passportID, String nationality, Date birthdate) {
        this.guestID = guestID;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.passportID = passportID;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    // getter | setter
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
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

    public String getPassportID() {
        return passportID;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestID=" + guestID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", passportID='" + passportID + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
