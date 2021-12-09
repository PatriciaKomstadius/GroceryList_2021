package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
import se.iths.grocerylist.service.GroceryListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grocerylists")
public class GroceryListController {

    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    //POST
    @PostMapping()
    public ResponseEntity<GroceryListEntity> createGroceryList(@RequestBody GroceryListEntity groceryList) {

        if (groceryList.getName() == null || groceryList.getName().isEmpty()) {

            throw new BadRequestException("This field can not be empty. Enter name of grocerylist.");
        }
        GroceryListEntity createdGrocerylist = groceryListService.createGroceryList(groceryList);

        return new ResponseEntity<>(createdGrocerylist, HttpStatus.CREATED);
    }

    //GET id
    @GetMapping("{id}")
    public ResponseEntity<Optional<GroceryListEntity>> getGrocerylistById(@PathVariable Long id) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        if (foundGrocerylist.isEmpty()) {
            throw new EntityNotFoundException("No grocerylist with id " + id + " found.");
        }

        return new ResponseEntity<>(foundGrocerylist, HttpStatus.OK);
    }

    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<GroceryListEntity>> getAllGroceryLists() {

        Iterable<GroceryListEntity> grocerylists = groceryListService.findAllGroceryLists();

        List<GroceryListEntity> foundGrocerylists = new ArrayList<>();

        for (GroceryListEntity grocerylist : grocerylists)
            foundGrocerylists.add(grocerylist);

        if (foundGrocerylists.isEmpty()) {
            throw new EntityNotFoundException("No grocerylists found.");
        }
        return new ResponseEntity<>(grocerylists, HttpStatus.FOUND);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<GroceryListEntity>> updateNameOfGrocerylist(@PathVariable Long id, @RequestBody GroceryListEntity name) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        if (foundGrocerylist.isEmpty()) {
            throw new EntityNotFoundException("Can not find grocerylist with id " + id + ".");
        }
        if (name.getName() == null || name.getName().isEmpty()) {
            throw new BadRequestException("Name can not be empty.");
        }

        Optional<GroceryListEntity> updatedGroceryList = groceryListService.updateName(id, name.getName());

        return new ResponseEntity<>(updatedGroceryList, HttpStatus.OK);
    }


    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroceryList(@PathVariable Long id) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        if (foundGrocerylist.isEmpty()) {
            throw new EntityNotFoundException("Can not find grocerylist with id " + id);
        }

        groceryListService.removeGroceryList(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
