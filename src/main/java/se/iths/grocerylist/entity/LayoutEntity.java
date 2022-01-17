package se.iths.grocerylist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class LayoutEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    @ManyToMany(mappedBy = "layouts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DepartmentEntity> departments = new HashSet<>();

    @ManyToOne
    private CompanyEntity companyEntity;

    public LayoutEntity() {
    }

    public LayoutEntity(String type) {
        this.type = type;
    }

    public void addDepartment(DepartmentEntity department) {
        departments.add(department);
        department.getLayouts().add(this);

        System.out.println("----------------------------------------------------------");
        System.out.println(this.type);
        for (DepartmentEntity d : departments
        ) {
            System.out.println("SET " + d.getDepartmentName());
        }

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


    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }
}
