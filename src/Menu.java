import java.util.*;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    // role menu
    public static void roleMenu(Admin admin) {
        System.out.println("\nChoose your role:");
        System.out.println("=====================");
        System.out.println("1-Admin");
        System.out.println("2-Customer");
        System.out.println("3-exit");
        chooseRole(admin);
    }

    // choose role
    public static void chooseRole(Admin admin) {
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                adminSignIn(admin);
                break;
            case "2":
                userSignMenu(admin);
                break;
            case "3":
                System.out.println("GoodBye !");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid!");
                roleMenu(admin);
        }
    }

    // admin sign in
    public static void adminSignIn(Admin admin) {
        System.out.println("Another beautiful day to manage your customers ^-^");
        System.out.println("Enter your username:");
        String userName = sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.nextLine();
        if (admin.checkUserPass(userName, password)) {
            adminMenu(admin);
        } else {
            System.out.println("Invalid!");
            adminSignIn(admin);
        }
    }

    // admin menu
    public static void adminMenu(Admin admin) {
        System.out.println("\nwelcome back " + "**" + admin.getAdminName() + "**");
        System.out.println("choose:");
        System.out.println("=====================");
        System.out.println("1-See all users");
        System.out.println("2-search user");
        System.out.println("3-filter users by networth");
        System.out.println("4-sort users by networth");
        System.out.println("5-answer questions");
        System.out.println("6-remove a user");
        System.out.println("7-block money");
        System.out.println("8-edit permission");
        System.out.println("9-show admin's balance");
        System.out.println("10-exit");

        adminChoice(admin);
    }

    // admin choice
    public static void adminChoice(Admin admin) {
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                adminError(admin);
                admin.sortUserById();
                admin.printCustomers(admin.getUsers());
                break;
            case "2":
                adminError(admin);
                findUser(admin);
                break;
            case "3":
                adminError(admin);
                filterByNetWorth(admin);
                break;
            case "4":
                adminError(admin);
                admin.sortUserByNetWorth();
                admin.printCustomers(admin.getUsers());
                break;
            case "5":
                adminError(admin);
                admin.printMessages();
                adminMessageSender(admin);
                System.out.println("\nMessage Sent");
                break;
            case "6":
                adminError(admin);
                removeUser(admin);
                break;
            case "7":
                adminError(admin);
                blockChoice(admin);
                break;
            case "8":
                adminError(admin);
                editPermission(admin);
                break;
            case "9":
                adminError(admin);
                admin.printBalance();
                break;
            case "10":
                roleMenu(admin);
                break;
            default:
                System.out.println("\nInvalid!");
                adminMenu(admin);
        }
        adminMenu(admin);
    }

    // block choice
    public static void blockChoice(Admin admin) {
        System.out.println("\nchoose:");
        System.out.println("---------");
        System.out.println("1-block money");
        System.out.println("2-return block money");

        blockOrReturn(admin);
    }

    // block or return
    public static void blockOrReturn(Admin admin) {
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                blockMoney(admin);
                break;
            case "2":
                returnMoney(admin);
                break;
            default:
                blockChoice(admin);
        }
    }

    // edit permission
    public static void editPermission(Admin admin) {
        System.out.println("\nEnter the user id, who you want to allow to be edited");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        if (admin.getUsers().size() == 0 || !admin.userIdExistance(id)) {
            System.out.println("\nInvalid");
            adminMenu(admin);
        } else {
            admin.changeEditPermission(id);
            System.out.println("\n" + admin.searchUser(id).getName() + " is now able to edit their info");
        }
    }

    // block money
    public static void blockMoney(Admin admin) {
        System.out.println("\nEnter user id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter account id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int accId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter amount of blocking money: ");
        while (!sc.hasNextDouble()) {
            sc.next();
        }
        double amount = sc.nextDouble();
        sc.nextLine();
        if (!admin.userIdExistance(userId) || !admin.accIdExistance(accId)
                || amount > admin.searchUser(userId).find(accId, admin).getBalance()) {
            System.out.println("\nInvalid!");
            adminMenu(admin);
        } else {
            admin.block(userId, accId, amount, admin);
            System.out.println("\nblocked " + amount + "$ of  " + admin.searchUser(userId).getName() + "(acc id: "
                    + admin.searchUser(userId).find(accId, admin).getId() + ")");
        }
    }

    // return money
    public static void returnMoney(Admin admin) {
        System.out.println("\nEnter user id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter account id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int accId = sc.nextInt();
        sc.nextLine();
        if (admin.searchUser(userId).find(accId, admin).getBlockedMoney() == 0) {
            System.out.println("\nInvalid!");
            adminMenu(admin);
        } else {
            admin.returnMoney(userId, accId, admin);
            System.out.println("\nrevealed blocked money of" + admin.searchUser(userId).getName() + " (acc id:"
                    + admin.searchUser(userId).find(accId, admin).getId() + ")");
        }
    }

    // remove user
    public static void removeUser(Admin admin) {
        System.out.println("\nEnter user id:");
        while(!sc.hasNextInt()){
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        if(admin.removeUser(id)){
            System.out.println("\nremoved succesfully");
            adminMenu(admin);
        }else{
            System.out.println("\nCan't remove this user");
            adminMenu(admin);
        }
    }

    // admin message sender
    public static void adminMessageSender(Admin admin) {
        System.out.println("\nEnter user id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        if (!admin.userIdExistance(id)) {
            System.out.println("\nInvalid!");
            adminMenu(admin);
        } else if (admin.searchUser(id).getMessages().size() == 0) {
            System.out.println("\nNo Messages Yet!");
            adminMenu(admin);
        } else {
            System.out.println("Enter your response: ");
            String answer = sc.nextLine();
            admin.sendMessageToUser(answer, id);
        }
    }

    // filter by net worth
    public static void filterByNetWorth(Admin admin) {
        System.out.println("\nEnter the filtering amount:");
        if (!sc.hasNextInt()) {
            sc.next();
        }
        int answer = sc.nextInt();
        sc.nextLine();
        ArrayList<User> filteredUsers = admin.filterByNetWorth(answer);
        if (filteredUsers.isEmpty()) {
            System.out.println("\nNot Found!");
            adminMenu(admin);
        } else {
            admin.printCustomers(filteredUsers);
        }
    }

    // find user
    public static void findUser(Admin admin) {
        System.out.println("\nwrite a user name to show their info:");
        String answer = sc.nextLine();
        if (!admin.hasName(admin, answer)) {
            System.out.println("\nNot Found!");
            adminMenu(admin);
        } else {
            admin.searchUser(answer).printUser();
        }
    }

    // user sign menu
    public static void userSignMenu(Admin admin) {
        System.out.println("\nChoose an option:");
        System.out.println("=====================");
        System.out.println("1- Sign up");
        System.out.println("2- Sign in");
        System.out.println("3- exit");
        chooseSign(admin);
    }

    // choose sign
    public static void chooseSign(Admin admin) {
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                userSignUp(admin);
                break;
            case "2":
                if (admin.getUsers().size() == 0) {
                    System.out.println("\nNo User To Sign In!");
                    roleMenu(admin);
                }
                userMenu(admin);
                break;
            case "3":
                roleMenu(admin);
            default:
                System.out.println("\nInvalid!");
                userSignMenu(admin);
        }
    }

    // user sign up
    public static void userSignUp(Admin admin) {
        if (admin.getUsers().size() >= 10) {
            System.out.println();
            userSignMenu(admin);
        } else {
            System.out.println("\nWhat's your name:");
            String name = sc.nextLine();
            if (admin.uniqueName(name) == false) {
                System.out.println("\nname has been taken!");
                userSignUp(admin);
            }
            System.out.println("Create a password:");
            String password = sc.nextLine();
            System.out.println("What city do you live in?");
            String city = sc.nextLine();
            System.out.println("What street do you live in?");
            String street = sc.nextLine();
            System.out.println("What is your homeNO?");
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int homeNO = sc.nextInt();
            sc.nextLine();
            System.out.println("What is your job ?");
            String job = sc.nextLine();
            User user = new User(name, password, new Address(city, street, homeNO), job);
            admin.getUsers().add(user);
            System.out.println("\nUser Created succesfully!");
            roleMenu(admin);
        }

    }

    // user sign in
    public static User userSignIn(Admin admin) {
        User user = null;
        System.out.println("\nEnter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your password:");
        String password = sc.nextLine();
        for (User i : admin.getUsers()) {
            if (i.getName().equals(name) && i.getPassword().equals(password)) {
                user = i;
            }
        }
        return user;
    }

    // user menu
    public static void userMenu(Admin admin) {
        User user = userSignIn(admin);
        if (user == null) {
            System.out.println("\nNOT FOUND!");
            userSignMenu(admin);
        } else {
            mainMenu(user, admin);
        }
    }

    // mainMenu
    public static void mainMenu(User user, Admin admin) {
        System.out.println("\nwelcome back  **" + user.getName() + "**");
        System.out.println("choose:");
        System.out.println("=====================");
        System.out.println("1-Create a new account");
        System.out.println("2-Edit personal info");
        System.out.println("3-print financial info's");
        System.out.println("4-send money");
        System.out.println("5-show transactions");
        System.out.println("6-filter transactions");
        System.out.println("7-sort transactions");
        System.out.println("8-feedback support");
        System.out.println("9-exit");

        userChoice(user, admin);
    }

    // user choice
    public static void userChoice(User user, Admin admin) {
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                user.createAccount(user.getName(), user.getId());
                break;
            case "2":
                editChoice(user, admin);
                break;
            case "3":
                user.printUser();
                break;
            case "4":
                if (admin.accIds().size() < 2) {
                    System.out.println("\nNo Account!");
                    mainMenu(user, admin);
                }
                sendMoneyChoice(user, admin);
                break;
            case "5":
                transactionError(user, admin);
                user.sortByDate();
                user.printTransaction(user.getTransactions());
                break;
            case "6":
                transactionError(user, admin);
                System.out.println("Enter the filtering amount:");
                double amount = sc.nextDouble();
                sc.nextLine();
                userFilterChoice(amount, user, admin);
                break;
            case "7":
                transactionError(user, admin);
                userSortChoice(user, admin);
                break;
            case "8":
                user.printMessages();
                System.out.println("Enter your text:");
                String text = sc.nextLine();
                user.sendMessage(text);
                System.out.println("\nMessage Sent");
                break;
            case "9":
                roleMenu(admin);
                break;
            default:
                System.out.println("Invalid!");
                mainMenu(user, admin);
        }
        mainMenu(user, admin);
    }

    // transaction error
    public static void transactionError(User user, Admin admin) {
        if (user.getTransactions().size() == 0) {
            System.out.println("\nNo Transaction!");
            mainMenu(user, admin);
        }
    }

    // admin error
    public static void adminError(Admin admin) {
        if (admin.getUsers().size() == 0) {
            System.out.println("\nNo User!");
            adminMenu(admin);
        }
    }

    // edit choice
    public static void editChoice(User user, Admin admin) {
        if (user.getEditPermission() == false) {
            System.out.println("\nyou don't have the permission to edit your info");
            mainMenu(user, admin);
        } else {
            String name;
            do{
                System.out.println("Enter your new name:");
                name = sc.nextLine();
                if(!admin.uniqueName(name)) {
                    System.out.println("\nThis username has been taken");
                }
            }while(!admin.uniqueName(name));
            System.out.println("Enter your new password:");
            String password = sc.nextLine();
            System.out.println("Enter your new job:");
            String job = sc.nextLine();
            System.out.println("Enter your new city:");
            String city = sc.nextLine();
            System.out.println("Enter your new town street:");
            String street = sc.nextLine();
            System.out.println("Enter your new homeNO:");
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int homeNO = sc.nextInt();
            sc.nextLine();
            user.editUser(job, name, password, new Address(city, street, homeNO));
            user.setEditPermission(false);
            System.out.println("\nedited succesfuly!");
        }
    }

    // send money choice
    public static void sendMoneyChoice(User user, Admin admin) {
        admin.showAccIds();
        System.out.println("**Your acc ids**");
        user.printAccIds();
        System.out.println();
        System.out.println("Enter the sending id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int senderId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the recieving id:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int recieverId = sc.nextInt();
        sc.nextLine();
        if (!(user.idChecker(senderId, recieverId, admin))) {
            System.out.println("\nInvalid!");
            sendMoneyChoice(user, admin);
        }
        System.out.println("How much money do you want to transfer? ($)");
        double amount = sc.nextInt();
        sc.nextLine();
        if ((user.find(senderId, admin).getBalance() <= amount + (0.005 * amount)) || amount <= 0) {
            System.out.println("\nInvalid Money!");
            sendMoneyChoice(user, admin);
        } else {
            user.transfer(senderId, recieverId, amount, admin);
            System.out.println("\nTransaction completed");
            mainMenu(user, admin);
        }

    }

    // filter choice
    public static void filterChoice() {
        System.out.println("choose:");
        System.out.println("------");
        System.out.println("1-Accounts under the amount");
        System.out.println("2-Accounts above the amount");
    }

    // user filter choice
    public static void userFilterChoice(double amount, User user, Admin admin) {
        filterChoice();
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                ArrayList<Transaction> transactionMax = user.filterByAmountMax(amount, user.getTransactions());
                if (transactionMax.isEmpty()) {
                    System.out.println("Not Found!");
                    mainMenu(user, admin);
                } else {
                    user.printTransaction(transactionMax);
                }
                break;
            case "2":
                ArrayList<Transaction> transactionMin = user.filterByAmountMin(amount, user.getTransactions());
                if (transactionMin.isEmpty()) {
                    System.out.println("\nNot Found!");
                    mainMenu(user, admin);
                } else {
                    user.printTransaction(transactionMin);
                }
                break;
            default:
                mainMenu(user, admin);
        }
    }

    // sort choice
    public static void sortChoice() {
        System.out.println("choose:");
        System.out.println("------");
        System.out.println("1-date");
        System.out.println("2-ascending amount");
        System.out.println("3-descending amount");
    }


    // user sort choice
    public static void userSortChoice(User user, Admin admin) {
        sortChoice();
        String answer = sc.nextLine();
        switch (answer) {
            case "1":
                user.sortByDate();
                Transaction.printTransactions(user.getTransactions());
                break;
            case "2":
                user.sortByAmountAsc();
                Transaction.printTransactions(user.getTransactions());
                break;
            case "3":
                user.sortByAmountDes();
                Transaction.printTransactions(user.getTransactions());
                break;
            default:
                mainMenu(user, admin);
        }
    }
}