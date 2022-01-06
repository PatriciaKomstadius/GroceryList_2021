package se.iths.grocerylist.mapper;

import org.mapstruct.Mapper;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.model.UserModel;

import java.util.Optional;

@Mapper (componentModel = "spring")
public interface UserMapper {

    UserModel userEntityToUserModel(UserEntity entity);
    UserEntity userModelToUserEntity(UserModel model);

}
