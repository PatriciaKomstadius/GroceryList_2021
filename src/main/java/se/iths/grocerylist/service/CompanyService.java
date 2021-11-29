package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
}
