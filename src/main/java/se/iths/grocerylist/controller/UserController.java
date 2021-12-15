package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.UnauthorizedException;
import se.iths.grocerylist.sender.Sender;
import se.iths.grocerylist.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    //private final Sender sender;

    public UserController(UserService userService){
        this.userService = userService;
       // this.sender = sender;
    }

    @PostMapping("signup")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){

        if(user.getUsername()==null || user.getUsername().isEmpty()){
            throw new BadRequestException("Empty Username");
        }

        if(user.getEmail()==null || user.getEmail().isEmpty()) {

            throw new BadRequestException("Empty Email");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()) {

            throw new BadRequestException("Empty Password");
        }

        //sender.sendMessage(user.getUsername());
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> findUserById(@PathVariable Long id){

            Optional<UserEntity> foundUser = userService.findUserById(id);

            if (foundUser.isEmpty()) {
                throw new EntityNotFoundException("Fel");
            }

            return new ResponseEntity<>(foundUser, HttpStatus.FOUND);


    }

    @GetMapping()
    public ResponseEntity<Iterable<UserEntity>> findAllUsers(){

            Iterable<UserEntity> allUsers = userService.findAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.FOUND);

    }

    @PutMapping()
    public ResponseEntity<UserEntity>updateUser(@RequestBody UserEntity user){
        UserEntity updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }




    @PatchMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> updateUserEmail(@PathVariable Long id, @RequestBody UserEntity user){
        Optional<UserEntity> updatedUser = userService.updateUserEmail(id, user.getEmail());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
