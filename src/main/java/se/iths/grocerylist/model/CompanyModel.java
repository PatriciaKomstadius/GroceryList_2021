package se.iths.grocerylist.model;

public class CompanyModel {

    private Long id;
    private String companyName;

    public CompanyModel() {
    }

    public CompanyModel(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
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
