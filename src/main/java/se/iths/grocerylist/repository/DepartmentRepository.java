package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.DepartmentEntity;


@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {



}
