import java.util.*;

public class User {
    private static int uniqueID = 100;
    private ArrayList<String> messages = new ArrayList<>();
    private double netWorth;
    private String job;
    private Address address;
    private String name;
    private String password;
    private int id;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private boolean editPermission;

    public User(String name, String password, Address address, String job) {
        this.name = name;
        this.password = password;
        this.job = job;
        this.address = address;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.netWorth = 0;
        this.id = ++uniqueID;
        this.editPermission = false;
    }

    public boolean getEditPermission() {
        return editPermission;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public String getJob() {
        return job;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setEditPermission(boolean editPermission) {
        this.editPermission = editPermission;
    }

    public void setNetWorth(double sumBalance) {
        this.netWorth = sumBalance;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // updating balance
    public void updateNetWorth() {
        double sumBalance = 0;
        for (Account i : accounts) {
            sumBalance += i.getBalance();
        }
        this.netWorth = sumBalance;
    }

    // printing user
    public void printUser() {
        System.out.println("\n");
        System.out.println("*** User Info ***");
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("password: " + password);
        System.out.println("job: " + job);
        System.out.println("net worth: " + netWorth);
        address.printAddress();
        System.out.println("*** Accounts ***");
        for (Account i : accounts) {
            i.printAccount();
            System.out.println("** End of account **");
        }
    }

    // printin account ids
    public void printAccIds() {
        for (Account i : accounts) {
            System.out.println("Acc id: " + i.getId());
        }
    }

    // creating account
    public void createAccount(String OwnerName, int ownerId) {
        if (accounts.size() < 3) {
            Account account = new Account(name, id);
            accounts.add(account);
            updateNetWorth();
            System.out.println("\nCreated Succesfully!");
        } else {
            System.out.println("\nOut of range!");
        }
    }

    // finding account by id
    public Account find(int id, Admin admin) {
        Account account = null;
        for (Account i : allAccounts(admin)) {
            if (i.getId() == id) {
                account = i;
            }
        }
        return account;
    }

    // return all accounts
    private ArrayList<Account> allAccounts(Admin admin) {
        ArrayList<Account> allAccounts = new ArrayList<>();
        for (User i : admin.getUsers()) {
            for (Account j : i.getAccounts()) {
                allAccounts.add(j);
            }
        }
        return allAccounts;
    }

    // edit
    public void editUser(String job, String name, String password, Address address) {
        setName(name);
        setPassword(password);
        setJob(job);
        setAddress(address);
    }

    // transfer
    public void transfer(int AccIdSender, int AccIdReciever, double amount, Admin admin) {
        Account account1 = find(AccIdSender, admin);
        Account account2 = find(AccIdReciever, admin);
        account1.setBalance(account1.getBalance() - amount - (0.005 * amount));
        account2.setBalance(account2.getBalance() + amount);
        User user1 = userFinder(account1, admin);
        User user2 = userFinder(account2, admin);
        user1.updateNetWorth();
        user2.updateNetWorth();
        admin.setBalance(admin.getBalance() + 0.005 * amount);
        if (user1.getName().equals(user2.getName())) {
            user1.transactions.add(new Transaction(account1, account2, amount));
        } else {
            user1.transactions.add(new Transaction(account1, account2, amount));
            user2.transactions.add(new Transaction(account1, account2, amount));
        }

    }

    // find user
    private User userFinder(Account account, Admin admin) {
        User user = null;
        for (User i : admin.getUsers()) {
            if (i.getName().equals(account.getOwnerName())) {
                user = i;
            }
        }
        return user;
    }

    // transaction print
    public void printTransaction(ArrayList<Transaction> transaction) {
        for (Transaction i : transaction) {
            i.printTransaction();
            System.out.println("********");
        }
    }

    // sort descending amount
    public void sortByAmountDes() {
        for (int i = 0; i < transactions.size(); i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(j).getAmount() > transactions.get(i).getAmount()) {
                    Collections.swap(transactions, i, j);
                }
            }
        }
    }

    // sort ascending amount
    public void sortByAmountAsc() {
        for (int i = 0; i < transactions.size(); i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(j).getAmount() < transactions.get(i).getAmount()) {
                    Collections.swap(transactions, i, j);
                }
            }
        }
    }

    // sorting by date
    public void sortByDate() {
        for (int i = 0; i < transactions.size(); i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(j).getDate().compareTo(transactions.get(i).getDate()) < 0) {
                    Collections.swap(transactions, i, j);
                }
            }
        }
    }

    // filter by minimum amount
    public ArrayList<Transaction> filterByAmountMin(double price, ArrayList<Transaction> transaction) {
        ArrayList<Transaction> newTransaction = new ArrayList<>();
        for (Transaction i : transaction) {
            if (i.getAmount() >= price) {
                newTransaction.add(i);
            }
        }
        return newTransaction;
    }

    // filter by maximum amount
    public ArrayList<Transaction> filterByAmountMax(double price, ArrayList<Transaction> transaction) {
        ArrayList<Transaction> newTransaction = new ArrayList<>();
        for (Transaction i : transaction) {
            if (i.getAmount() <= price) {
                newTransaction.add(i);
            }
        }
        return newTransaction;
    }

    // message
    public void sendMessage(String text) {
        messages.add("\u001B[31m" + "- " + "\u001B[0m" + text);
    }

    // print messages
    public void printMessages() {
        for (String i : messages) {
            System.out.println(i);
        }
    }

    // id checker
    public boolean idChecker(int accIdSender, int accIdReciever, Admin admin) {
        boolean check = false;
        if (accIdSender == accIdReciever) {
            check = false;
        } else if (!(idAvailability(accIdSender, admin) && idAvailability(accIdReciever, admin))) {
            check = false;
        } else if (!rightIdCheck(accIdSender)) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    // check id availability

    private boolean idAvailability(int accId, Admin admin) {
        if (admin.accIds().contains(accId)) {
            return true;
        }
        return false;
    }

    private boolean rightIdCheck(int accIdSender) {
        for (int i : userAccountIds()) {
            if (i == accIdSender) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> userAccountIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Account i : accounts) {
            ids.add(i.getId());
        }
        return ids;
    }

    // end of id availabilities
}