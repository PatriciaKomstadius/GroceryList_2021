package se.iths.grocerylist.model;


import org.springframework.stereotype.Component;


public class UserModel {

    private String username;
    private String email;
    private String firstName;
    private String password;


    public UserModel() {
    }


    public UserModel(String username, String email, String firstName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
