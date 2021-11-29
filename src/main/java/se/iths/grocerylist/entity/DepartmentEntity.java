package se.iths.grocerylist.entity;

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
    private LayoutEntity layoutEntity;


    @OneToMany(mappedBy = "departments", cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentName, LayoutEntity layoutEntity) {
        this.departmentName = departmentName;
        this.layoutEntity = layoutEntity;
    }

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

    public LayoutEntity getLayoutEntity() {
        return layoutEntity;
    }

    public void setLayoutEntity(LayoutEntity layoutEntity) {
        this.layoutEntity = layoutEntity;
    }
}
