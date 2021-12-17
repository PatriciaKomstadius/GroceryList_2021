package se.iths.grocerylist.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.GroceryListEntity;


@Repository
public interface GroceryListRepository extends CrudRepository<GroceryListEntity, Long> {
    GroceryListEntity findByName (String name);

}
