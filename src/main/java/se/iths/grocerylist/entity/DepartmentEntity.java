package se.iths.grocerylist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String departmentName;

    @ManyToOne
    private LayoutEntity layout;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentName, LayoutEntity layoutEntity) {
        this.departmentName = departmentName;
        this.layout = layoutEntity;
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

    public LayoutEntity getLayout() {
        return layout;
    }

    public void setLayout(LayoutEntity layoutEntity) {
        this.layout = layoutEntity;
    }
}
