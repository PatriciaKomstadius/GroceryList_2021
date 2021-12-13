package se.iths.grocerylist.entity;


import javax.persistence.*;

@Entity
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;

    @OneToOne
    @JoinColumn(name= "companyInfoEntity_id", referencedColumnName = "id")
    private CompanyInfoEntity companyInfoEntity;


    public CompanyEntity() {
    }

    public CompanyEntity(String companyName, CompanyInfoEntity companyInfoEntity) {
        this.companyName = companyName;
        this.companyInfoEntity = companyInfoEntity;
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

    public CompanyInfoEntity getCompanyInfoEntity() {
        return companyInfoEntity;
    }

    public void setCompanyInfoEntity(CompanyInfoEntity city) {
        this.companyInfoEntity = city;
    }
}
