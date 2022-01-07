package se.iths.grocerylist.mapper;

import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.DepartmentEntity;
import se.iths.grocerylist.model.DepartmentModel;


@Mapper (componentModel = "spring")
public interface DepartmentMapper {

    DepartmentModel departmentEntityToDepartmentModel(DepartmentEntity entity);
    DepartmentEntity departmentModelToDepartmentEntity(DepartmentModel model);
    Iterable<DepartmentModel> allEntityToAllModels(Iterable<DepartmentEntity> list);

}
