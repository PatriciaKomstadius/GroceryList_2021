package se.iths.grocerylist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GroceryListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "grocerylists", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(mappedBy = "grocerylists", cascade = CascadeType.ALL)
    private Set<ProductEntity> products = new HashSet<>();


    public GroceryListEntity() {
    }

    public GroceryListEntity(String name) {
        this.name = name;
    }

    public void addProduct(ProductEntity product){
        products.add(product);
        product.addGroceryList(this);

    }

    public void addUser(UserEntity user){
        users.add(user);
        user.addGroceryList(this);

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

//    @JsonIgnore
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }


    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
