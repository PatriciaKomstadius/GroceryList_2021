package se.iths.grocerylist.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.repository.UserRepository;

@Service
public class GroceryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public GroceryUserDetailsService(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
            System.out.println("Can´t find user with username: " + username);
        }

        return new GroceryUserPrincipal(user);
    }
}
