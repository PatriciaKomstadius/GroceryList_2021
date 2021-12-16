package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.entity.LayoutEntity;


@Repository
public interface LayoutRepository extends CrudRepository<LayoutEntity, Long> {

    LayoutEntity findByType(String type);

}
