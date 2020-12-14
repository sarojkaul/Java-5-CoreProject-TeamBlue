public class Room {

    private int roomID;
    private int capacity;
    private double size;
    private double price;
    private String description;

    // enums
    private roomFacilities facilities;
    private roomCategory category;

    // constructor
    public Room(int roomID, int capacity, double size, double price, String description, roomCategory category) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.size = size;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    // getter | setter
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFacilities(roomFacilities facilities) {
        this.facilities = facilities;
    }

    public void setCategory(roomCategory category) {
        this.category = category;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public roomFacilities getFacilities() {
        return facilities;
    }

    public roomCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", capacity=" + capacity +
                ", size=" + size +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", facilities=" + facilities +
                ", category=" + category +
                '}';
    }
}
