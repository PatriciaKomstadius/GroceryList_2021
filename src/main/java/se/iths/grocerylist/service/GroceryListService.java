package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.repository.GroceryListRepository;

import java.util.Optional;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;

    public GroceryListService(GroceryListRepository groceryListRepository) {
        this.groceryListRepository = groceryListRepository;
    }


    //Create
    public GroceryListEntity createGroceryList(GroceryListEntity groceryListEntity) {
        return groceryListRepository.save(groceryListEntity);
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
