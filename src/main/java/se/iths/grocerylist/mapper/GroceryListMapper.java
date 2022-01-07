package se.iths.grocerylist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.model.GroceryListModel;
import se.iths.grocerylist.model.UserModel;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface GroceryListMapper {


   GroceryListModel groceryListEntityToGroceryListModel(GroceryListEntity entity);
    GroceryListEntity groceryListModelToGroceryListEntity(GroceryListModel model);

    Iterable<GroceryListModel> allEntityToAllModels(Iterable<GroceryListEntity> all);


}
