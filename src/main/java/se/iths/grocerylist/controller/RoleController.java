package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.service.RoleService;

import java.util.Optional;


@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController (RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<RoleEntity> createRole (@RequestBody RoleEntity role){
        RoleEntity createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<RoleEntity>>findRoleById(@PathVariable Long id){
        Optional<RoleEntity> foundRole = roleService.findRoleById(id);
        return new ResponseEntity<>(foundRole, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<RoleEntity>>findAllRoles(){
        Iterable<RoleEntity> allRoles = roleService.findAllRoles();
        return new ResponseEntity<>(allRoles, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<RoleEntity>updateRole(RoleEntity role){
        RoleEntity updatedRole = roleService.updateRole(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<RoleEntity>> updateRoleName(@PathVariable Long id, @RequestBody ProductEntity newProductName){
        Optional<RoleEntity>updatedRole = roleService.updateRoleName(id, newProductName.getProductName());
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteRole(@PathVariable Long id){
        roleService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
