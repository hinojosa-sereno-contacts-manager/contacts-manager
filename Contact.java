public class Contact {

    private String name;
    private String phone;



    //constructor
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() != 10) {
            throw new IllegalArgumentException("Phone number requires 10 digits");
        }
        this.phone = phone;
    }

    public String checkNull(String contactProperty) {
        return contactProperty == null ? "" : contactProperty;
    }

    @Override
    public String toString() {
        return name + " | " + phone + '\n';
    }

}