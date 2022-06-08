import java.util.List;

public class User {
    //Members of User class
    private static User instance;
    final String id;
    final String name;
    final String email;
    private String username;
    private String password;

    //Getter and Setter methods for members
    public static boolean getIsLoggedIn() {
        return instance != null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Singleton User constructor
    private User(String id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username=username;
        this.password=password;
    }

    // This function returns null if user is not yet registered to the system
    // So we should force user to register first
    public static User getInstance() {
        return instance;
    }

    public static void registerUser(String id, String name, String email,String username,String password) {
        instance = new User(id, name, email,username,password);
    }

    public void logOut() {
        instance = null;
    }
}
