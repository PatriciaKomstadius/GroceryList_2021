package se.iths.grocerylist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GroceryListEntity {


    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public GroceryListEntity(String name, Set<UserEntity> users, ProductEntity products, ProductEntity product) {
        this.name = name;
        this.users = users;
        this.products = products;
        this.product = product;
    }

    @ManyToMany(mappedBy = "groceries", cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    @ManyToOne
    private ProductEntity products;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public GroceryListEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
