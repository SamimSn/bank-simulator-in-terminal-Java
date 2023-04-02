public class Account {

    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double balance;
    private String iBAN;
    private String password;

    public Account(int index,double balance, String iBAN, String password) {

        this.id = index;
        this.balance = balance;
        this.iBAN = iBAN;
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public String getiBAN() {
        return iBAN;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
