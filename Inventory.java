public class Inventory {
    private int id;
    private String bloodType;
    private int quantity;

    public Inventory(int id, String bloodType, int quantity) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
