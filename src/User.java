import java.util.ArrayList;
public class User {

    private static int lastID = 1;
    private int id;
    private String name;
    private String phone;
    private String country;
    private String city; 
    private ArrayList<Account> accounts;

    public User(String name, String phone, String country, String city) {
        id = id();
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public static int getLastID() {
        return lastID;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void setLastID(int lastID) {
        User.lastID = lastID;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public int id(){
        id = lastID;
        lastID++;
        return id;
    }
}
