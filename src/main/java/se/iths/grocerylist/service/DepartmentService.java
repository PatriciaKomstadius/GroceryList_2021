package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
}
