public class Donor {
    private int id;
    private String name;
    private String bloodType;
    private String contact;
    private String address;

    public Donor(int id, String name, String bloodType, String contact, String address) {
        this.id = id;
        this.name = name;
        this.bloodType = bloodType;
        this.contact = contact;
        this.address = address;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
