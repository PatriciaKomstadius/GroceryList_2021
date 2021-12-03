package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.LayoutEntity;
import se.iths.grocerylist.service.LayoutService;

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
    public ResponseEntity<LayoutEntity> createLayout(@RequestBody LayoutEntity layout){
        LayoutEntity createdLayout = layoutService.createLayout(layout);

        return new ResponseEntity<>(createdLayout, HttpStatus.CREATED);
    }

    //GET id
    @GetMapping("{id}")
    public ResponseEntity<Optional<LayoutEntity>> findLayoutById(@PathVariable Long id){
        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        return new ResponseEntity<>(foundLayout, HttpStatus.FOUND);
    }

    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<LayoutEntity>> findAllLayouts(){
        Iterable<LayoutEntity> findAllLayouts = layoutService.findAllLayouts();

        return new ResponseEntity<>(findAllLayouts, HttpStatus.FOUND);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<LayoutEntity>> updateType(@PathVariable Long id, @RequestBody LayoutEntity type){
        Optional<LayoutEntity> updatedLayout = layoutService.updateTypeOfLayout(id, type.getType());

        return new ResponseEntity<>(updatedLayout, HttpStatus.OK);

    }

    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<LayoutEntity> deleteLayout(@PathVariable Long id){

        Optional<LayoutEntity> deletedLayout = layoutService.deleteLayout(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
