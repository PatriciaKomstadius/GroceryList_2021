package se.iths.grocerylist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.repository.RoleRepository;

@SpringBootApplication
public class GroceryListApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryListApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpRole(RoleRepository roleRepository){
        return (args) -> {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            roleRepository.save(new RoleEntity("ROLE_EMPLOYEE"));
            roleRepository.save(new RoleEntity("ROLE_CUSTOMER"));

        };
    }


}
