package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
import se.iths.grocerylist.service.RoleService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController (RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<RoleEntity> createRole (@Valid @RequestBody RoleEntity role){

        if(role.getRoleName()==null || role.getRoleName().isEmpty()){
            throw new BadRequestException("roleName cannot be empty");
        }

        RoleEntity createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<RoleEntity>>findRoleById(@PathVariable Long id){
        Optional<RoleEntity> foundRole = roleService.findRoleById(id);

        if(foundRole.isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        return new ResponseEntity<>(foundRole, HttpStatus.FOUND);
    }



    @GetMapping()
    public ResponseEntity<Iterable<RoleEntity>>findAllRoles(){
        Iterable<RoleEntity> allRoles = roleService.findAllRoles();

        if (!allRoles.iterator().hasNext()){
            throw new EntityNotFoundException("There are no roles registered in the database");
        }

        return new ResponseEntity<>(allRoles, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<RoleEntity>updateRole(@RequestBody RoleEntity role){

        if(role.getId()==null){
            throw new MethodNotAllowedException("You need to specify ID on product to be updated");
        }
        if(roleService.findRoleById(role.getId()).isEmpty()){
            throw new EntityNotFoundException(responseMessage(role.getId()));
        }

        RoleEntity updatedRole = roleService.updateRole(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<RoleEntity>> updateRoleName(@PathVariable Long id, @RequestBody RoleEntity newUpdatedRole){

        if(roleService.findRoleById(id).isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        Optional<RoleEntity>updatedRole = roleService.updateRoleName(id, newUpdatedRole.getRoleName());
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteRole(@PathVariable Long id){

        if(roleService.findRoleById(id).isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        roleService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private String responseMessage(Long id) {
        return "There is no role with ID " + id + " in database.";
    }




}
