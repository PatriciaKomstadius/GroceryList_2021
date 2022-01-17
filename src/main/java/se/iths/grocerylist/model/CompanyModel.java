package se.iths.grocerylist.model;

import se.iths.grocerylist.entity.CompanyInfoEntity;

import java.util.Set;

public class CompanyModel {

    private Long id;
    private String companyName;
    private CompanyInfoEntity companyInfoEntity;
    private Set<LayoutModel> layouts;

    public CompanyModel() {
    }

    public CompanyModel(Long id, String companyName, CompanyInfoEntity companyInfoEntity, Set<LayoutModel> layouts) {
        this.id = id;
        this.companyName = companyName;
        this.companyInfoEntity = companyInfoEntity;
        this.layouts = layouts;
    }

    public CompanyInfoEntity getCompanyInfoEntity() {
        return companyInfoEntity;
    }

    public void setCompanyInfoEntity(CompanyInfoEntity companyInfoEntity) {
        this.companyInfoEntity = companyInfoEntity;
    }

    public Set<LayoutModel> getLayouts() {
        return layouts;
    }

    public void setLayouts(Set<LayoutModel> layouts) {
        this.layouts = layouts;
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

    @Override
    public String toString() {
        return "CompanyModel{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
