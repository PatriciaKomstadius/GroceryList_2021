package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.mapper.UserMapper;
import se.iths.grocerylist.model.UserModel;
import se.iths.grocerylist.sender.Sender;
import se.iths.grocerylist.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final Sender sender;
    private final UserMapper userMapper;

    public UserController(UserService userService, Sender sender, UserMapper userMapper){
        this.userService = userService;
        this.sender = sender;
        this.userMapper = userMapper;
    }

    @PostMapping("signup")
    public ResponseEntity<UserModel> createUser(@ModelAttribute("signup") @RequestBody UserModel user){

        if(user.getUsername()==null || user.getUsername().isEmpty()){
            throw new BadRequestException("Empty Username");
        }

        if(user.getEmail()==null || user.getEmail().isEmpty()) {

            throw new BadRequestException("Empty Email");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()) {

            throw new BadRequestException("Empty Password");
        }

//        sender.sendMessage(user.getUsername());
        UserEntity createdUser = userService.createUser(userMapper.userModelToUserEntity(user));
        UserModel response = userMapper.userEntityToUserModel(createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id){

            Optional<UserEntity> foundUser = userService.findUserById(id);

            if (foundUser.isEmpty()) {
                throw new EntityNotFoundException("Fel");
            }

            UserModel response = userMapper.userEntityToUserModel(foundUser.get());

            return new ResponseEntity<>(response, HttpStatus.FOUND);


    }

    @GetMapping()
    public ResponseEntity<Iterable<UserModel>> findAllUsers(){

            Iterable<UserEntity> allUsers = userService.findAllUsers();
            Iterable<UserModel> allUsersModels = userMapper.allEntityToAllModels(allUsers);

            return new ResponseEntity<>(allUsersModels, HttpStatus.FOUND);

    }

    @PutMapping()
    public ResponseEntity<UserModel>updateUser(@RequestBody UserModel user){
        UserEntity updatedUser = userService.updateUser(userMapper.userModelToUserEntity(user));
        UserModel response = userMapper.userEntityToUserModel(updatedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PatchMapping("{id}")
    public ResponseEntity<UserModel> updateUserEmail(@PathVariable Long id, @RequestBody UserModel user){
        Optional<UserEntity> updatedUser = userService.updateUserEmail(id, user.getEmail());
        UserModel response = userMapper.userEntityToUserModel(updatedUser.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
