package model;

public class User extends Person {

    private String username;
    private String password;

    public User(String id, String name, String phone, String email,
                String username, String password) {

        super(id, name, phone, email);

        this.username = username;
        this.password = password;
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
}