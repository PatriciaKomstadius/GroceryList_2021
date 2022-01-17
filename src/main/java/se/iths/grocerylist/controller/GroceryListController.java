package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.mapper.GroceryListMapper;
import se.iths.grocerylist.model.GroceryListModel;
import se.iths.grocerylist.service.GroceryListService;
import se.iths.grocerylist.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grocerylists")
public class GroceryListController {

    private final GroceryListService groceryListService;
    private final ProductService productService;
    private final GroceryListMapper groceryListMapper;

    public GroceryListController(GroceryListService groceryListService, ProductService productService, GroceryListMapper groceryListMapper) {
        this.groceryListService = groceryListService;
        this.productService = productService;
        this.groceryListMapper = groceryListMapper;
    }

    @PostMapping()
    public ResponseEntity<GroceryListModel> createGroceryList(@RequestBody GroceryListModel groceryList) {

        if (groceryList.getName() == null || groceryList.getName().isEmpty()) {
            throw new BadRequestException("This field can not be empty. Enter name of grocerylist.");
        }

        GroceryListEntity createdGrocerylist = groceryListService.createGroceryList(groceryListMapper.groceryListModelToGroceryListEntity(groceryList));
        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(createdGrocerylist);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("addproduct/{name}/{productname}")
    public ResponseEntity<GroceryListEntity> addProductToList(@PathVariable String name, @PathVariable String productname) {

        Optional<ProductEntity> foundProduct = productService.findProductByName(productname);

        GroceryListEntity updatedList = groceryListService.addProductsToList(name, foundProduct.get());

        return new ResponseEntity<>(updatedList, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<GroceryListModel> getGrocerylistById(@PathVariable Long id) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        if (foundGrocerylist.isEmpty()) {
            throw new EntityNotFoundException("No grocerylist with id " + id + " found.");
        }

        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(foundGrocerylist.get());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<GroceryListModel>> getAllGroceryLists() {

        Iterable<GroceryListEntity> grocerylists = groceryListService.findAllGroceryLists();
        List<GroceryListEntity> foundGrocerylists = new ArrayList<>();

        for (GroceryListEntity grocerylist : grocerylists)
            foundGrocerylists.add(grocerylist);

        if (foundGrocerylists.isEmpty()) {
            throw new EntityNotFoundException("No grocerylists found.");
        }

        Iterable<GroceryListModel> grocerylistsModels = groceryListMapper.allEntityToAllModels(foundGrocerylists);

        return new ResponseEntity<>(grocerylistsModels, HttpStatus.FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<GroceryListModel> updateNameOfGrocerylist(@PathVariable Long id, @RequestBody GroceryListModel name) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        if (foundGrocerylist.isEmpty()) {
            throw new EntityNotFoundException("Can not find grocerylist with id " + id + ".");
        }
        if (name.getName() == null || name.getName().isEmpty()) {
            throw new BadRequestException("Name can not be empty.");
        }

        Optional<GroceryListEntity> updatedGroceryList = groceryListService.updateName(id, name.getName());

        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(updatedGroceryList.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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