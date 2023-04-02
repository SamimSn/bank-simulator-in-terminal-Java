import java.util.ArrayList;
public class Users {
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Users.users = users;
    }
}
