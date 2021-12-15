package se.iths.grocerylist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "company_info_entity")
@Entity

public class CompanyInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orgnr;
    private String address;
    private String zipCode;
    private String city;

    @OneToOne(mappedBy = "companyInfoEntity")
    private CompanyEntity companyEntity;

    public CompanyInfoEntity(String orgnr, String address, String zipCode, String city) {
        this.orgnr = orgnr;
       this.address = address;
        this.zipCode = zipCode;
         this.city = city;

    }


    public CompanyInfoEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgnr() {
        return orgnr;
    }

    public void setOrgnr(String orgnr) {
        this.orgnr = orgnr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonIgnore
    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }
}