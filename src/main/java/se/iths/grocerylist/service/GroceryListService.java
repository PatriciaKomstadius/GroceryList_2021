package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.repository.GroceryListRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;

    public GroceryListService(GroceryListRepository groceryListRepository) {
        this.groceryListRepository = groceryListRepository;
    }


    //Create
    public GroceryListEntity createGroceryList( GroceryListEntity groceryListEntity) {
        return groceryListRepository.save(groceryListEntity);
    }

    public GroceryListEntity addProductsToList(String groceryListName, ProductEntity product){
        GroceryListEntity listToUpdate =  groceryListRepository.findByName(groceryListName);
        listToUpdate.addProduct(product);
        groceryListRepository.save(listToUpdate);

        return listToUpdate;
    }

    public GroceryListEntity removeProductsFromList(String groceryListName, ProductEntity product){
        GroceryListEntity listToUpdate =  groceryListRepository.findByName(groceryListName);
        listToUpdate.removeProduct(product);
        groceryListRepository.save(listToUpdate);

        return listToUpdate;
    }

    public Set<ProductEntity> getProductsOnGrocerylist(String groceryListName){
        GroceryListEntity foundGroceryList = groceryListRepository.findByName(groceryListName);
        return foundGroceryList.getProducts();
    }

    public GroceryListEntity getGroceryListByName(String name) {
        return groceryListRepository.findByName(name);
    }
    //Read id
    public Optional<GroceryListEntity> getGroceryListById(Long id) {
        return groceryListRepository.findById(id);
    }

    //Read all
    public Iterable<GroceryListEntity> findAllGroceryLists() {
        return groceryListRepository.findAll();
    }

    //Update name
    public Optional<GroceryListEntity> updateName(Long id, String name) {
        Optional<GroceryListEntity> grocerylist = groceryListRepository.findById(id);
        grocerylist.get().setName(name);
        groceryListRepository.save(grocerylist.get());

        return grocerylist;
    }

    //Delete
    public void removeGroceryList(Long id) {
        Optional<GroceryListEntity> groceryList = groceryListRepository.findById(id);

        groceryListRepository.deleteById(groceryList.get().getId());
    }
}
