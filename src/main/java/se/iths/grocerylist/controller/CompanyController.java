package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.CompanyEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.mapper.CompanyMapper;
import se.iths.grocerylist.model.CompanyModel;
import se.iths.grocerylist.service.CompanyService;

import java.util.Optional;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping()
    public ResponseEntity<CompanyModel> createCompany(@RequestBody CompanyModel company) {
        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new BadRequestException("Company Name is Empty");
        }

        CompanyEntity createdCompany = companyService.createCompany(companyMapper.companyModelToCompanyEntity(company));
        CompanyModel response = companyMapper.companyEntityToCompanyModel(createdCompany);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyModel> findCompanyById(@PathVariable Long id) {
        Optional<CompanyEntity> foundCompany = companyService.findCompanyById(id);
        CompanyModel response = companyMapper.companyEntityToCompanyModel(foundCompany.get());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<CompanyModel>> findAllCompanies() {
        Iterable<CompanyEntity> allCompanies = companyService.findAllCompanies();
        Iterable<CompanyModel> allCompanyModels = companyMapper.allEntityToAllModels(allCompanies);

        return new ResponseEntity<>(allCompanyModels, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<CompanyModel> updateCompany(@RequestBody CompanyModel company) {
        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new BadRequestException("Company Name is Empty");
        }

        CompanyEntity updatedCompany = companyService.updateCompany(companyMapper.companyModelToCompanyEntity(company));
        CompanyModel response = companyMapper.companyEntityToCompanyModel(updatedCompany);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CompanyModel> updateCompanyName(@PathVariable Long id, @RequestBody CompanyModel company) {

        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new BadRequestException("Company Name is Empty");
        }
        Optional<CompanyEntity> updatedCompany = companyService.updateCompanyName(id, company.getCompanyName());
        CompanyModel response = companyMapper.companyEntityToCompanyModel(updatedCompany.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}