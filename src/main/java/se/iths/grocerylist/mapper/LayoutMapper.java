package se.iths.grocerylist.mapper;

import ch.qos.logback.core.Layout;
import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.LayoutEntity;
import se.iths.grocerylist.model.GroceryListModel;
import se.iths.grocerylist.model.LayoutModel;

@Mapper(componentModel = "spring")
public interface LayoutMapper {

    LayoutModel layoutEntityToLayoutModel(LayoutEntity entity);
    LayoutEntity layoutModelToLayoutEntity(LayoutModel model);

    Iterable<LayoutModel> allEntityToAllModels(Iterable<LayoutEntity> all);

}
