package se.iths.grocerylist.model;

import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.UserEntity;

import java.util.Set;

public class GroceryListModel {

    private String name;
   // private Set<UserEntity> users;
   // private Set<ProductEntity> products;

    public GroceryListModel() {
    }

    public GroceryListModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Set<UserEntity> getUsers() {
        return users;
    }


    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

 */

    /*
    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

     */


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroceryListModel{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
