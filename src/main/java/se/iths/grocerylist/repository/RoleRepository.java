package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.RoleEntity;


@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
