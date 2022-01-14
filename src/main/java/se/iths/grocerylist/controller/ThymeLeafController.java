package se.iths.grocerylist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductListCreationEntity;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.mapper.GroceryListMapper;
import se.iths.grocerylist.mapper.UserMapper;
import se.iths.grocerylist.model.GroceryListModel;
import se.iths.grocerylist.model.UserModel;
import se.iths.grocerylist.service.GroceryListService;
import se.iths.grocerylist.service.ProductService;
import se.iths.grocerylist.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("app")
public class ThymeLeafController {

    private final GroceryListService groceryListService;
    private final ProductService productService;
    private final GroceryListMapper groceryListMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public ThymeLeafController(GroceryListService groceryListService, ProductService productService, GroceryListMapper groceryListMapper, UserService userService, UserMapper userMapper) {
        this.groceryListService = groceryListService;
        this.productService = productService;
        this.groceryListMapper = groceryListMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }



    @PostMapping("signup")
    public String createUserThyme( @ModelAttribute("signup") UserModel user, Model model){

        userService.createUser(userMapper.userModelToUserEntity(user));
        model.addAttribute("something", "user.getUsername()");

        return "redirect:/signupmessage";

    }

//    @GetMapping("/create")
//    public String showCreateForm ( Model model) {
//        ProductListCreationEntity groceryListForm = new ProductListCreationEntity();
//        for(int i = 1; i<=3; i++){
//            groceryListForm.addList(new GroceryListEntity());
//        }
//
//        model.addAttribute("form", groceryListForm);
//        return "createGrocerylistsForm";
//    }

    @PostMapping ("/save")
    public String saveLists(@ModelAttribute ProductListCreationEntity form, Model model) {

        List<ProductEntity> products = form.getProductslist();
        for (ProductEntity p: products) {
            Optional<ProductEntity> foundProduct = productService.findProductByName(p.getProductName());
            if(!foundProduct.isEmpty()){
                groceryListService.addProductsToList(form.getCurrentGroceryList(), foundProduct.get());
            }

        }


       // productService.saveAll(form.getProductslist());

        model.addAttribute("currentlistname", form.getCurrentGroceryList());
        model.addAttribute("currentlistproducts", groceryListService.getProductsOnGrocerylist(form.getCurrentGroceryList()));
        return "currentlistwithproducts";
    }


    @PostMapping("/edit")
    public String editGroceryListThyme(@ModelAttribute("name") String name, Model model){
        model.addAttribute("currentlistname",name);

        ProductListCreationEntity productListForm = new ProductListCreationEntity();
        productListForm.setCurrentGroceryList(name);

        for(int i = 1; i<=8; i++){
            productListForm.addList(new ProductEntity());
        }

        model.addAttribute("form", productListForm);
        model.addAttribute("allproducts", productService.findAllProducts());
        model.addAttribute("grocerylists", groceryListService.findAllGroceryLists());
        model.addAttribute("currentlistproducts", groceryListService.getProductsOnGrocerylist(name));
        return "currentlist";


    }

    @PostMapping()
    public String createGroceryListThyme(@ModelAttribute("groceryListModel") GroceryListModel groceryListModel, Model model) {


        System.out.println("LIST FROM UI = " + groceryListModel);
        groceryListService.createGroceryList(groceryListMapper.groceryListModelToGroceryListEntity(groceryListModel));


        model.addAttribute("currentlistname", groceryListModel.getName());

        ProductListCreationEntity productListForm = new ProductListCreationEntity();
        productListForm.setCurrentGroceryList(groceryListModel.getName());
        System.out.println(productListForm.getCurrentGroceryList());
        for(int i = 1; i<=8; i++){
            productListForm.addList(new ProductEntity());
        }

        model.addAttribute("form", productListForm);
        model.addAttribute("allproducts", productService.findAllProducts());
        model.addAttribute("grocerylists", groceryListService.findAllGroceryLists());
        model.addAttribute("currentlistproducts", groceryListService.getProductsOnGrocerylist(groceryListModel.getName()));
        return "currentlist";



    }

    @PostMapping("addproduct")
    public void addProductToListThyme(@ModelAttribute("name") String name, @ModelAttribute("productName")String productName){

        Optional<ProductEntity> foundProduct = productService.findProductByName(productName);

        if(!foundProduct.isEmpty()){
            GroceryListEntity updatedList = groceryListService.addProductsToList(name, foundProduct.get());
        }


    }

    @GetMapping("all")
    public String getAllGroceryListsThyme(Model model) {

        model.addAttribute("grocerylists", groceryListService.findAllGroceryLists());


        return "allgrocerylists";
    }


    @GetMapping("update")
    public String updateNameOfGrocerylistThyme(@RequestParam Long id, @RequestParam String name) {

        Optional<GroceryListEntity> foundGrocerylist = groceryListService.getGroceryListById(id);

        Optional<GroceryListEntity> updatedGroceryList = groceryListService.updateName(id, name);

        GroceryListModel response = groceryListMapper.groceryListEntityToGroceryListModel(updatedGroceryList.get());

        return "redirect:/application";
    }

    @GetMapping("deleteproduct")
    public String deleteProductFromGroceryListThyme(@RequestParam ("listname") String listName, @RequestParam("productname") String productName, Model model) {

        Optional<ProductEntity> foundProduct = productService.findProductByName(productName);
        groceryListService.removeProductsFromList(listName, foundProduct.get());

        model.addAttribute("currentlistname", listName);
        model.addAttribute("currentlistproducts", groceryListService.getProductsOnGrocerylist(listName));


        return "currentlistwithproducts";
    }


    @GetMapping("deletelist")
    public String deleteGroceryListThyme(@RequestParam String name) {

        GroceryListEntity foundGrocerylist = groceryListService.getGroceryListByName(name);


        groceryListService.removeGroceryList(foundGrocerylist.getId());



        return "redirect:/application";
    }
}
