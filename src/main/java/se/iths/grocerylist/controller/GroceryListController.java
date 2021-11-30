package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.service.GroceryListService;

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
        GroceryListEntity createdGrocerylist = groceryListService.createGroceryList(groceryList);

        return new ResponseEntity<>(createdGrocerylist, HttpStatus.CREATED);
    }

    //GET id
    @GetMapping({"id"})
    public ResponseEntity<Optional<GroceryListEntity>> getGrocerylistById(@PathVariable Long id) {
        Optional<GroceryListEntity> getList = groceryListService.getGroceryListById(id);

        return new ResponseEntity<>(getList, HttpStatus.OK);
    }

    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<GroceryListEntity>> getAllGroceryLists() {
        Iterable<GroceryListEntity> grocerylists = groceryListService.findAllGroceryLists();

        return new ResponseEntity<>(grocerylists, HttpStatus.FOUND);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<GroceryListEntity>> updateNameOfGrocerylist(@PathVariable Long id, @RequestBody GroceryListEntity name) {
        Optional<GroceryListEntity> updatedGroceryList = groceryListService.updateName(id, name.getName());

        return new ResponseEntity<>(updatedGroceryList, HttpStatus.OK);
    }


    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroceryList(@PathVariable Long id) {

        groceryListService.removeGroceryList(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
