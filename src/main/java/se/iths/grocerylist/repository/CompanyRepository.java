package se.iths.grocerylist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.grocerylist.entity.CompanyEntity;


@Repository
public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
}
