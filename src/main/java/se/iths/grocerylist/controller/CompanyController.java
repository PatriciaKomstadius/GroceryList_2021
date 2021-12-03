package se.iths.grocerylist.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.CompanyEntity;
import se.iths.grocerylist.service.CompanyService;

import javax.annotation.security.DenyAll;
import java.util.Optional;

@RestController
@RequestMapping("companys")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping()
    public ResponseEntity<CompanyEntity> createCompany(@RequestBody CompanyEntity company){
        CompanyEntity createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<CompanyEntity>> findCompanyById(@PathVariable Long id){
        Optional<CompanyEntity> foundCompany = companyService.findCompanyById(id);
        return new ResponseEntity<>(foundCompany, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<CompanyEntity>> findAllCompanys(){
        Iterable<CompanyEntity> allCompanys = companyService.findAllCompanys();
        return new ResponseEntity<>(allCompanys, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<CompanyEntity>updateCompany(@RequestBody CompanyEntity company){
        CompanyEntity updatedCompany = companyService.updateCompany(company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<CompanyEntity>> updateCompanyName(@PathVariable Long id, @RequestBody CompanyEntity company){
        Optional<CompanyEntity> updatedCompany = companyService.updateCompanyName(id, company.getCompanyName());
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}