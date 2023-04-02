public class Transaction{
    private Account sender;
    private Account reciever;
    private double price;
    
    public Account getSender() {
        return sender;
    }
    public Account getReciever() {
        return reciever;
    }
    public double getPrice() {
        return price;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }
    public void setReciever(Account reciever) {
        this.reciever = reciever;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
}