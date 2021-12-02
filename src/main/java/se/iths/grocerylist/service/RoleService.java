package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.repository.RoleRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleEntity createRole(RoleEntity role){
        return roleRepository.save(role);
    }

    public Optional<RoleEntity> findRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Iterable<RoleEntity>findAllRoles(){
        return roleRepository.findAll();
    }

    //Är denna nödvändig?? Finns bara rollnamn att uppdatera
    public RoleEntity updateRole(RoleEntity role){
        roleRepository.save(role);
        return role;
    }

    public Optional<RoleEntity>updateRoleName(Long id, String roleName){
        Optional<RoleEntity>foundRole = roleRepository.findById(id);
        foundRole.get().setRoleName(roleName);
        roleRepository.save(foundRole.get());
        return foundRole;
    }

    public void deleteUser(Long id){
        Optional<RoleEntity>foundRole = roleRepository.findById(id);
        roleRepository.delete(foundRole.get());
    }

}
