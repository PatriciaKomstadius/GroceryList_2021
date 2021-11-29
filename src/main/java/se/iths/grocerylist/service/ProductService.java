package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
}
