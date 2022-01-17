package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.CompanyEntity;
import se.iths.grocerylist.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public CompanyEntity createCompany(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public Optional<CompanyEntity> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Iterable<CompanyEntity> findAllCompanies() {
        return companyRepository.findAll();
    }

    public CompanyEntity updateCompany(CompanyEntity company) {
        companyRepository.save(company);
        return company;
    }

    public Optional<CompanyEntity> updateCompanyName(Long id, String companyName) {
        Optional<CompanyEntity> foundCompany = companyRepository.findById(id);
        foundCompany.get().setCompanyName(companyName);
        companyRepository.save(foundCompany.get());
        return foundCompany;

    }

    public void deleteCompany(Long id) {
        Optional<CompanyEntity> foundCompany = companyRepository.findById(id);
        companyRepository.deleteById(foundCompany.get().getId());
    }

}