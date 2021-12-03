package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity createUser (UserEntity user){
        return userRepository.save(user);
    }

    public Optional<UserEntity> findUserById (Long id){
        return userRepository.findById(id);
    }

    public Iterable<UserEntity>findAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity updateUser(UserEntity user){
        return userRepository.save(user);
    }

    public Optional<UserEntity> updateUserEmail(Long id, String email){
        Optional<UserEntity> foundUser = userRepository.findById(id);
        foundUser.get().setMail(email);
        userRepository.save(foundUser.get());
        return foundUser;

    }

    public void deleteUser(Long id){
        Optional<UserEntity> foundUser = userRepository.findById(id);
        userRepository.deleteById(foundUser.get().getId());
    }



}

