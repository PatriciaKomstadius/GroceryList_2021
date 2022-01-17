package se.iths.grocerylist.model;


import se.iths.grocerylist.entity.GroceryListEntity;

import java.util.HashSet;
import java.util.Set;

public class UserModel {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private Set<GroceryListModel> grocerylists = new HashSet<>();


    public UserModel() {
    }

    public UserModel(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

    }


    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<GroceryListModel> getGrocerylists() {
        return grocerylists;
    }

    public void setGrocerylists(Set<GroceryListModel> grocerylists) {
        this.grocerylists = grocerylists;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
