package se.iths.grocerylist.model;

import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.UserEntity;

import java.util.Set;

public class GroceryListModel {

    private Long id;
    private String name;
    private Set<UserModel> users;
    private Set<ProductModel>products;

    public GroceryListModel() {
    }

    public GroceryListModel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductModel> products) {
        this.products = products;
    }


    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroceryListModel{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
