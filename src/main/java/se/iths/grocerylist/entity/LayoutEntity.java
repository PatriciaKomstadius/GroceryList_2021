package se.iths.grocerylist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LayoutEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL)
    private List<DepartmentEntity> departments = new ArrayList<>();

    @ManyToOne
    private CompanyEntity companyEntity;

    public LayoutEntity() {
    }

    public LayoutEntity(String type, List<DepartmentEntity> departments) {
        this.type = type;
        this.departments = departments;
    }

    public void addDepartment(DepartmentEntity department){
        departments.add(department);
        department.setLayout(this);
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }
}
