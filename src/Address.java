public class Address {

    private String city;
    private String street;
    private int homeNO;

    public Address(String city, String street, int homeNO) {
        this.city = city;
        this.street = street;
        this.homeNO = homeNO;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHomeNO() {
        return homeNO;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHomeNO(int homeNO) {
        this.homeNO = homeNO;
    }

    // print address
    public void printAddress() {
        System.out.println("*** Address ***");
        System.out.println("city: " + city);
        System.out.println("street: " + street);
        System.out.println("homeNO: " + homeNO);
    }
}