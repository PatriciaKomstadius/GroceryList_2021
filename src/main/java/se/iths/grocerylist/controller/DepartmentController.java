package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.mapper.DepartmentMapper;
import se.iths.grocerylist.model.DepartmentModel;
import se.iths.grocerylist.service.DepartmentService;


import java.util.Optional;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;


    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper){
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping()
    public ResponseEntity<DepartmentModel> createDepartment(@RequestBody DepartmentModel department){

        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        DepartmentEntity createdDepartment = departmentService.createDepartment(departmentMapper.departmentModelToDepartmentEntity(department));
        DepartmentModel response = departmentMapper.departmentEntityToDepartmentModel(createdDepartment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentModel> findDepartmentById(@PathVariable Long id){
        Optional<DepartmentEntity> foundDepartment = departmentService.findDepartmentById(id);
        DepartmentModel response = departmentMapper.departmentEntityToDepartmentModel(foundDepartment.get());
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<DepartmentModel>> findAllDepartments(){
        Iterable<DepartmentEntity> allDepartments = departmentService.findAllDepartments();
        Iterable<DepartmentModel> allDepartmentModels = departmentMapper.allEntityToAllModels(allDepartments);
        return new ResponseEntity<>(allDepartmentModels, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<DepartmentModel>updateDepartment(@RequestBody DepartmentModel department){

        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        DepartmentEntity updatedDepartment = departmentService.updateDepartment(departmentMapper.departmentModelToDepartmentEntity(department));
        DepartmentModel response = departmentMapper.departmentEntityToDepartmentModel(updatedDepartment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<DepartmentModel> updateDepartmentEmail(@PathVariable Long id, @RequestBody DepartmentModel department){
        if (department.getDepartmentName()==null || department.getDepartmentName().isEmpty()){
            throw new BadRequestException("Department Name is Empty");
        }
        Optional<DepartmentEntity> updatedDepartment = departmentService.updateDepartmentName(id, department.getDepartmentName());
        DepartmentModel response = departmentMapper.departmentEntityToDepartmentModel(updatedDepartment.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}