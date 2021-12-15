package se.iths.grocerylist.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private double price;
    private String category;
    private int quantity;

    @ManyToOne
    private DepartmentEntity department;

    @ManyToMany
    private Set<GroceryListEntity> grocerylists = new HashSet<>();


    public ProductEntity() {
    }

    public ProductEntity(String productName, double price, String category, int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public void addGroceryList(GroceryListEntity grocerylist){
       grocerylists.add(grocerylist);
    }

    public Long getId() {
        System.out.println();
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity departments) {
        this.department = department;
    }
}
