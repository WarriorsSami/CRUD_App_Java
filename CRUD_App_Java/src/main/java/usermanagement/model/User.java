package usermanagement.model;

public class User {
    private int ID;
    private String Name;
    private String Email;
    private String Country;

    public User (int ID, String name, String email, String country) {
        super ();
        this.ID = ID;
        Name = name;
        Email = email;
        Country = country;
    }

    public User(String name, String email, String country) {
        super ();
        Name = name;
        Email = email;
        Country = country;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
