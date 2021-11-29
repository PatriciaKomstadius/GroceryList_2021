package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
}
