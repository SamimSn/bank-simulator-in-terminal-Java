import java.util.*;

public class Transaction {
    private Account sender;
    private Account reciever;
    private double amount;
    private Date date;

    public Transaction(Account sender, Account reciever, double amount) {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
        this.date = new Date();
    }

    public Account getSender() {
        return sender;
    }

    public Account getReciever() {
        return reciever;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReciever(Account reciever) {
        this.reciever = reciever;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // print transactions
    public void printTransaction() {
        System.out.println("\nfrom: " + sender.getOwnerName() + " > acc id : " + sender.getId());
        System.out.println("To: " + reciever.getOwnerName() + " > acc id : " + reciever.getId());
        System.out.println("amount: " + amount + " $");
        System.out.println("date: " + date);
    }

    // print transactions
    public static void printTransactions(ArrayList<Transaction> transaction) {
        for (Transaction i : transaction) {
            i.printTransaction();
            System.out.println("---------");
        }
    }
}