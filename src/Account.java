import java.util.*;

public class Account {
    private static int uniqueID = 0;
    private String ownerName;
    private int ownerID;
    private int id;
    private double balance;
    private Date date;
    private double blockedMoney;

    public Account(String ownerName, int ownerID) {
        this.id = ++uniqueID;
        this.date = new Date();
        this.balance = 50;
        this.ownerName = ownerName;
        this.ownerID = ownerID;
        this.blockedMoney = 0;
    }

    public static int getUniqueID() {
        return uniqueID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDate() {
        return date;
    }

    public double getBlockedMoney() {
        return blockedMoney;
    }

    public static void setUniqueID(int uniqueID) {
        Account.uniqueID = uniqueID;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBlockedMoney(double blockedMoney) {
        this.blockedMoney = blockedMoney;
    }

    // print account
    public void printAccount() {
        System.out.println("id: " + id);
        System.out.println("balance: " + balance);
        System.out.println("date created: " + date);
        System.out.println("blocked money: " + blockedMoney);
    }
}