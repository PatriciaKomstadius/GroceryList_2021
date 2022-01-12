package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("app")
public class ThymeLeafController {

    private final GroceryListService groceryListService;
    private final ProductService productService;
    private final GroceryListMapper groceryListMapper;

    public ThymeLeafController(GroceryListService groceryListService, ProductService productService, GroceryListMapper groceryListMapper) {
        this.groceryListService = groceryListService;
        this.productService = productService;
        this.groceryListMapper = groceryListMapper;
    }

    @PostMapping()
    public ResponseEntity<GroceryListModel> createGroceryListThyme(@ModelAttribute("groceryListModel") GroceryListModel groceryListModel) {


        System.out.println("LIST FROM UI = " + groceryListModel);
        GroceryListEntity createdGrocerylist = groceryListService.createGroceryList(groceryListMapper.groceryListModelToGroceryListEntity(groceryListModel));
        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(createdGrocerylist);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("addproduct")
    public ResponseEntity<GroceryListEntity>addProductToListThyme(@ModelAttribute("name") String name, @ModelAttribute("productName")String productName){

        ProductEntity foundProduct = productService.findProductByName(productName);

        GroceryListEntity updatedList = groceryListService.addProductsToList(name, foundProduct);

        return new ResponseEntity<>(updatedList, HttpStatus.OK);

    }

    @GetMapping("getall")
    public String getAllGroceryListsThyme(Model model) {

        model.addAttribute("grocerylists", groceryListService.findAllGroceryLists());


        return "test";
    }


    @GetMapping("update")
    public String updateNameOfGrocerylistThyme(@RequestParam Long id, @RequestParam String name) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        Optional<GroceryListEntity> updatedGroceryList = groceryListService.updateName(id, name);

        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(updatedGroceryList.get());

        return "redirect:/application";
    }




    @GetMapping("deletelist")
    public String deleteGroceryListThyme(@RequestParam String name) {

        GroceryListEntity foundGrocerylist = groceryListService.getGroceryListByName(name);


        groceryListService.removeGroceryList(foundGrocerylist.getId());



        return "redirect:/application";
    }
}
