import java.util.*;

public class Admin {
    private ArrayList<User> users = new ArrayList<>();
    private String adminName;
    private String adminPass;
    private double balance;
    private ArrayList<User> permissions = new ArrayList<>();

    public Admin(String adminName, String adminPass) {
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<User> getPermissions() {
        return permissions;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPermissions(ArrayList<User> permissions) {
        this.permissions = permissions;
    }

    public void setUsers(ArrayList<User> accounts) {
        this.users = accounts;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    // print users and their accounts
    public void printCustomers(ArrayList<User> users) {
        for (User i : users) {
            i.printUser();
        }
    }

    // check if such user exists
    public boolean hasName(Admin admin, String name) {
        boolean check = false;
        for (User i : admin.getUsers()) {
            if (i.getName().equals(name)) {
                check = true;
            }
        }
        return check;
    }

    // show all the account ids
    public void showAccIds() {
        System.out.println("\n");
        for (User i : users) {
            System.out.println("Name : " + i.getName());
            System.out.println();
            for (Account j : i.getAccounts()) {
                System.out.println("Acc id :" + j.getId());
            }
            System.out.println("****End Of User****");
        }
    }

    // search users by name
    public User searchUser(String name) {
        User user = null;
        for (User i : users) {
            if (i.getName().equals(name)) {
                user = i;
            }
        }
        return user;
    }

    // search users by id
    public User searchUser(int id) {
        User user = null;
        for (User i : users)
            if (i.getId() == id) {
                user = i;
            }
        return user;

    }

    // remove user
    public boolean removeUser(int id) {
        for (User i : users) {
            if (i.getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    // block money
    public void block(int userId, int accId, double amount, Admin admin) {
        searchUser(userId).find(accId, admin).setBlockedMoney(amount);
        double newWallet = searchUser(userId).find(accId, admin).getBalance();
        searchUser(userId).find(accId, admin).setBalance(newWallet - amount);
        searchUser(userId).updateNetWorth();
    }

    // return blocked money
    public void returnMoney(int userId, int accId, Admin admin) {
        double blockedMoney = searchUser(userId).find(accId, admin).getBlockedMoney();
        double balance = searchUser(userId).find(accId, admin).getBalance();
        searchUser(userId).find(accId, admin).setBalance(balance + blockedMoney);
        searchUser(userId).find(accId, admin).setBlockedMoney(0);
        searchUser(userId).updateNetWorth();
    }

    // send messages to User
    public void sendMessageToUser(String text, int userId) {
        searchUser(userId).getMessages().add("\u001B[34m" + "Admin: " + "\u001B[0m" + text);
    }

    // check if the id exist
    public boolean userIdExistance(int id) {
        boolean check = false;
        for (User i : users) {
            if (i.getId() == id) {
                check = true;
            }
        }
        return check;
    }

    // check if the id exist
    public boolean accIdExistance(int id) {
        boolean check = false;
        for (User i : users) {
            for (Account j : i.getAccounts())
                if (j.getId() == id) {
                    check = true;
                }
        }
        return check;
    }

    // print all the messages
    public void printMessages() {
        for (User i : users) {
            System.out.println(i.getName() + "(ID: " + i.getId() + "):");
            i.printMessages();
            System.out.println("**end of user**");
        }
    }

    // sort by user's net worth
    public void sortUserByNetWorth() {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(j).getNetWorth() > users.get(i).getNetWorth()) {
                    Collections.swap(users, i, j);
                }
            }
        }
    }

    // return filtered users by net worth
    public ArrayList<User> filterByNetWorth(double netWorth) {
        ArrayList<User> newUsers = new ArrayList<>();
        for (User i : users) {
            if (i.getNetWorth() >= netWorth) {
                newUsers.add(i);
            }
        }
        return newUsers;
    }

    // sort users by id
    public void sortUserById() {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(j).getId() > users.get(j).getId()) {
                    Collections.swap(users, i, j);
                }
            }
        }
    }

    // check if the username is unique
    public boolean uniqueName(String name) {
        for (User i : users) {
            if (i.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    // return all the account ids
    public ArrayList<Integer> accIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (User i : users) {
            for (Account j : i.getAccounts()) {
                ids.add(j.getId());
            }
        }
        return ids;
    }

    // checks the given username and password
    public boolean checkUserPass(String name, String pass) {
        if (name.equals(adminName) && pass.equals(adminPass)) {
            return true;
        } else {
            return false;
        }
    }

    // changing user edit permission
    public void changeEditPermission(int userId) {
        searchUser(userId).setEditPermission(true);
    }

    // show balance
    public void printBalance() {
        System.out.println("\nYour balance is: " + balance + "$");
    }
}