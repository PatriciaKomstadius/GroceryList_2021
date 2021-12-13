package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.service.DepartmentService;


import java.util.Optional;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody DepartmentEntity department){

        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        DepartmentEntity createdDepartment = departmentService.createDepartment(department);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<DepartmentEntity>> findDepartmentById(@PathVariable Long id){
        Optional<DepartmentEntity> foundDepartment = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(foundDepartment, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<DepartmentEntity>> findAllDepartments(){
        Iterable<DepartmentEntity> allDepartments = departmentService.findAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<DepartmentEntity>updateDepartment(@RequestBody DepartmentEntity department){

        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        DepartmentEntity updatedDepartment = departmentService.updateDepartment(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<DepartmentEntity>> updateDepartmentEmail(@PathVariable Long id, @RequestBody DepartmentEntity department){
        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        Optional<DepartmentEntity> updatedDepartment = departmentService.updateDepartmentName(id, department.getDepartmentName());
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}