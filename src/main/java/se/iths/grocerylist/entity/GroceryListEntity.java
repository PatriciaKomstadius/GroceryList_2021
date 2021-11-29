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

    @ManyToMany(mappedBy = "groceries", cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    @ManyToOne
    private ProductEntity products;



}
