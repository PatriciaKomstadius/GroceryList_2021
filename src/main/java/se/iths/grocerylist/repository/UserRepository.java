package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.grocerylist.entity.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Long> {


}
