package se.iths.grocerylist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String departmentName;

    @ManyToMany()
    private Set<LayoutEntity> layouts = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products = new ArrayList<>();

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;

    }

    public void addProduct(ProductEntity product) {
        products.add(product);
        product.setDepartment(this);
    }

    public void addLayout(LayoutEntity layout) {
        layouts.add(layout);
        layout.getDepartments().add(this);
    }

    @JsonIgnore
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonIgnore
    public Set<LayoutEntity> getLayouts() {
        return layouts;
    }

    public void setLayouts(Set<LayoutEntity> layout) {
        this.layouts = layout;
    }
}
