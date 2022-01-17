package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
import se.iths.grocerylist.mapper.RoleMapper;
import se.iths.grocerylist.model.RoleModel;
import se.iths.grocerylist.service.RoleService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping()
    public ResponseEntity<RoleModel> createRole(@Valid @RequestBody RoleModel role) {

        if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
            throw new BadRequestException("roleName cannot be empty");
        }

        RoleEntity createdRole = roleService.createRole(roleMapper.roleModelToRoleEntity(role));
        RoleModel response = roleMapper.roleEntityToRoleModel(createdRole);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleModel> findRoleById(@PathVariable Long id) {
        Optional<RoleEntity> foundRole = roleService.findRoleById(id);

        if (foundRole.isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }

        RoleModel response = roleMapper.roleEntityToRoleModel(foundRole.get());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<RoleModel>> findAllRoles() {
        Iterable<RoleEntity> allRoles = roleService.findAllRoles();

        if (!allRoles.iterator().hasNext()) {
            throw new EntityNotFoundException("There are no roles registered in the database");
        }

        Iterable<RoleModel> allRoleModels = roleMapper.allEntityToAllModels(allRoles);

        return new ResponseEntity<>(allRoleModels, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<RoleModel> updateRole(@RequestBody RoleModel role) {

        if (role.getId() == null) {
            throw new MethodNotAllowedException("You need to specify ID on product to be updated");
        }
        if (roleService.findRoleById(role.getId()).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(role.getId()));
        }

        RoleEntity updatedRole = roleService.updateRole(roleMapper.roleModelToRoleEntity(role));
        RoleModel response = roleMapper.roleEntityToRoleModel(updatedRole);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<RoleModel> updateRoleName(@PathVariable Long id, @RequestBody RoleModel newUpdatedRole) {

        Optional<RoleEntity> updatedRole = roleService.updateRoleName(id, newUpdatedRole.getRoleName());
        if (updatedRole.isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }
        RoleModel response = roleMapper.roleEntityToRoleModel(updatedRole.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {

        if (roleService.findRoleById(id).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }
        roleService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String responseMessage(Long id) {
        return "There is no role with ID " + id + " in database.";
    }

}
