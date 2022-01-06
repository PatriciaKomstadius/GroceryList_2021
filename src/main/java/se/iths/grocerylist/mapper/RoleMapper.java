package se.iths.grocerylist.mapper;

import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.model.RoleModel;

@Mapper (componentModel = "spring")
public interface RoleMapper {

    RoleModel roleEntityToRoleModel(RoleEntity entity);
    RoleEntity roleModelToRoleEntity(RoleModel model);
    Iterable<RoleModel> allEntityToAllModels(Iterable<RoleEntity> list);

}
