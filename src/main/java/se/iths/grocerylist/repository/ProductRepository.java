package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.ProductEntity;

import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductName (String name);
}
