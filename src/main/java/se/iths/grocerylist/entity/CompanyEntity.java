package se.iths.grocerylist.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "companyInfoEntity_id", referencedColumnName = "id")
    private CompanyInfoEntity companyInfoEntity;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<LayoutEntity> layouts = new HashSet<>();

    public CompanyEntity() {
    }

    public CompanyEntity(String companyName, CompanyInfoEntity companyInfoEntity) {
        this.companyName = companyName;
        this.companyInfoEntity = companyInfoEntity;
    }

    public void addLayout (LayoutEntity layout){
        layouts.add(layout);
        layout.setCompanyEntity(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

   // @JsonIgnore
    public CompanyInfoEntity getCompanyInfoEntity() {
        return companyInfoEntity;
    }

    public void setCompanyInfoEntity(CompanyInfoEntity city) {
        this.companyInfoEntity = city;
    }
}
