package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.GroceryListRepository;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;

    public GroceryListService(GroceryListRepository groceryListRepository){
        this.groceryListRepository = groceryListRepository;
    }
}
