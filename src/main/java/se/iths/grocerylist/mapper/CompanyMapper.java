package se.iths.grocerylist.mapper;

import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.CompanyEntity;
import se.iths.grocerylist.model.CompanyModel;


@Mapper (componentModel = "spring")
public interface CompanyMapper {

    CompanyModel companyEntityToCompanyModel(CompanyEntity entity);
    CompanyEntity companyModelToCompanyEntity(CompanyModel model);
    Iterable<CompanyModel> allEntityToAllModels(Iterable<CompanyEntity> list);

}
