package se.iths.grocerylist.mapper;

import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.model.ProductModel;

@Mapper (componentModel = "spring")
public interface ProductMapper {

    ProductModel productEntityToProductModel(ProductEntity entity);
    ProductEntity productModelToProductEntity(ProductModel model);
    Iterable<ProductModel>allEntityToAllModels(Iterable<ProductEntity> list);


}
