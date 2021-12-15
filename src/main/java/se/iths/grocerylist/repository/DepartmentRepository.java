package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.entity.RoleEntity;


@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

    DepartmentEntity findByDepartmentName(String departmentName);

}
