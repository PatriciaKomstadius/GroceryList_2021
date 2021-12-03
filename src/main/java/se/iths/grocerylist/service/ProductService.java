package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository ProductRepository;

    public ProductService(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    //Create
    public ProductEntity createProduct(ProductEntity Product) {
        return ProductRepository.save(Product);
    }

    //Read id
    public Optional<ProductEntity> findProductById(Long id) {
        return ProductRepository.findById(id);
    }

    //Read all
    public Iterable<ProductEntity> findAllProducts(){
        return ProductRepository.findAll();
    }

    //Update type
    public Optional<ProductEntity> updatePriceOfProduct(Long id, double price){

        Optional<ProductEntity> foundProduct = ProductRepository.findById(id);

        foundProduct.get().setPrice(price);
        ProductRepository.save(foundProduct.get());

        return foundProduct;
    }

    //Delete
    public Optional<ProductEntity> deleteProduct(Long id){

        Optional<ProductEntity> deleteProduct = ProductRepository.findById(id);

        ProductRepository.deleteById(deleteProduct.get().getId());
        return deleteProduct;
    }
}
