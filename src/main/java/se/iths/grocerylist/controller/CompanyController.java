package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.CompanyEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.service.CompanyService;

import java.util.Optional;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping()
    public ResponseEntity<CompanyEntity> createCompany(@RequestBody CompanyEntity company){
        if (company.getCompanyName()==null || company.getCompanyName().isEmpty()){
            throw new BadRequestException("Company Name is Empty");
        }
        if (company.getCity()==null || company.getCity().isEmpty()){
            throw new BadRequestException("Company City is Empty");
        }
        CompanyEntity createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<CompanyEntity>> findCompanyById(@PathVariable Long id){
        Optional<CompanyEntity> foundCompany = companyService.findCompanyById(id);
        return new ResponseEntity<>(foundCompany, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<CompanyEntity>> findAllCompanies(){
        Iterable<CompanyEntity> allCompanies = companyService.findAllCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<CompanyEntity>updateCompany(@RequestBody CompanyEntity company){
        if (company.getCompanyName()==null || company.getCompanyName().isEmpty()){
            throw new BadRequestException("Company Name is Empty");
        }
        if (company.getCity()==null || company.getCity().isEmpty()){
            throw new BadRequestException("Company City is Empty");
        }
        CompanyEntity updatedCompany = companyService.updateCompany(company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<CompanyEntity>> updateCompanyName(@PathVariable Long id, @RequestBody CompanyEntity company){
        if (company.getCompanyName()==null || company.getCompanyName().isEmpty()){
            throw new BadRequestException("Company Name is Empty");
        }
        Optional<CompanyEntity> updatedCompany = companyService.updateCompanyName(id, company.getCompanyName());
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}