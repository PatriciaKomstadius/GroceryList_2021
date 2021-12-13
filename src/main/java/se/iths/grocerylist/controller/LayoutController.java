package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.LayoutEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.service.LayoutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("layouts")
public class LayoutController {

    private final LayoutService layoutService;

    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    //POST
    @PostMapping()
    public ResponseEntity<LayoutEntity> createLayout(@RequestBody LayoutEntity layout) {

        if (layout.getType() == null || layout.getType().isEmpty()) {
            throw new BadRequestException("Layout type can not be empty.");
        }
        LayoutEntity createdLayout = layoutService.createLayout(layout);

        return new ResponseEntity<>(createdLayout, HttpStatus.CREATED);
    }

    //GET id
    @GetMapping("{id}")
    public ResponseEntity<Optional<LayoutEntity>> findLayoutById(@PathVariable Long id) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if (foundLayout.isEmpty()) {
            throw new EntityNotFoundException("Layout with id " + id + " doesn't exist.");
        }

        return new ResponseEntity<>(foundLayout, HttpStatus.FOUND);
    }

    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<LayoutEntity>> findAllLayouts() {

        Iterable<LayoutEntity> findAllLayouts = layoutService.findAllLayouts();
        List<LayoutEntity> foundLayouts = new ArrayList<>();

        for (LayoutEntity layout : findAllLayouts) {
            foundLayouts.add(layout);
        }
        if (foundLayouts.isEmpty()) {
            throw new EntityNotFoundException("No layouts exists.");
        }
        return new ResponseEntity<>(findAllLayouts, HttpStatus.FOUND);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<LayoutEntity>> updateType(@PathVariable Long id, @RequestBody LayoutEntity type) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if(foundLayout.isEmpty()){
            throw new EntityNotFoundException("Layout with id " + id + " doesn't exist in database.");
        }
        if (type.getType() == null || type.getType().isEmpty()) {
            throw new BadRequestException("Type can not be empty.");
        }

        Optional<LayoutEntity> updatedLayout = layoutService.updateTypeOfLayout(id, type.getType());

        return new ResponseEntity<>(updatedLayout, HttpStatus.OK);

    }

    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<LayoutEntity> deleteLayout(@PathVariable Long id) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if(foundLayout.isEmpty()) {
            throw new EntityNotFoundException("Layout with id " + id + "doens't exist in databse. Couldn't fulfill the request.");
        }

        //Optional<LayoutEntity> deletedLayout =
                layoutService.deleteLayout(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
