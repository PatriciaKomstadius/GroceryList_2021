package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.ProductEntity;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
