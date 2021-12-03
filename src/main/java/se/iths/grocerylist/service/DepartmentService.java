package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity createDepartment (DepartmentEntity department){
        return departmentRepository.save(department);
    }

    public Optional<DepartmentEntity> findDepartmentById (Long id){
        return departmentRepository.findById(id);
    }

    public Iterable<DepartmentEntity>findAllDepartments(){
        return departmentRepository.findAll();
    }

    public DepartmentEntity updateDepartment(DepartmentEntity department){
        departmentRepository.save(department);

        return department;
    }

    public Optional<DepartmentEntity> updateDepartmentName(Long id, String departmentName){
        Optional<DepartmentEntity> foundDepartment = departmentRepository.findById(id);
        foundDepartment.get().setDepartmentName(departmentName);
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;

    }

    public void deleteDepartment(Long id){
        Optional<DepartmentEntity> foundDepartment = departmentRepository.findById(id);
        departmentRepository.deleteById(foundDepartment.get().getId());
    }


}