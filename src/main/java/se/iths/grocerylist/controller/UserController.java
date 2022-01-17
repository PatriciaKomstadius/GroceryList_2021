package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
import se.iths.grocerylist.mapper.UserMapper;
import se.iths.grocerylist.model.UserModel;
import se.iths.grocerylist.sender.Sender;
import se.iths.grocerylist.service.UserService;


import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final Sender sender;
    private final UserMapper userMapper;

    public UserController(UserService userService, Sender sender, UserMapper userMapper) {
        this.userService = userService;
        this.sender = sender;
        this.userMapper = userMapper;
    }

    @PostMapping("signup")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BadRequestException("Username cannot be empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {

            throw new BadRequestException("Email cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {

            throw new BadRequestException("Password cannot be empty");
        }

//        sender.sendMessage(user.getUsername());
        UserEntity createdUser = userService.createUser(userMapper.userModelToUserEntity(user));
        UserModel response = userMapper.userEntityToUserModel(createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @GetMapping("{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id) {

        Optional<UserEntity> foundUser = userService.findUserById(id);

        if (foundUser.isEmpty()) {
            throw new EntityNotFoundException("Fel");
        }

        UserModel response = userMapper.userEntityToUserModel(foundUser.get());

        return new ResponseEntity<>(response, HttpStatus.FOUND);


    }

    @GetMapping()
    public ResponseEntity<Iterable<UserModel>> findAllUsers() {

        Iterable<UserEntity> allUsers = userService.findAllUsers();

        if (!allUsers.iterator().hasNext()) {
            throw new EntityNotFoundException("There are no registered users in the database.");
        }

        Iterable<UserModel> allUsersModels = userMapper.allEntityToAllModels(allUsers);

        return new ResponseEntity<>(allUsersModels, HttpStatus.FOUND);

    }

    @PutMapping()
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {

        if (user.getId() == null) {
            throw new MethodNotAllowedException("You need to specify ID on user to be updated");
        }
        if (userService.findUserById(user.getId()).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(user.getId()));
        }

        UserEntity updatedUser = userService.updateUser(userMapper.userModelToUserEntity(user));
        UserModel response = userMapper.userEntityToUserModel(updatedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserModel> updateUserEmail(@PathVariable Long id, @RequestBody UserModel user) {

        Optional<UserEntity> updatedUser = userService.updateUserEmail(id, user.getEmail());

        if (updatedUser.isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }
        UserModel response = userMapper.userEntityToUserModel(updatedUser.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        if (userService.findUserById(id).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String responseMessage(Long id) {
        return "There is no user with ID " + id + " in database.";
    }

}
