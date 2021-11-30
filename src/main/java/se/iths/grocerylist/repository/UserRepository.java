package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {


}
