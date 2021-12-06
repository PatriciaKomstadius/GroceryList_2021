package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    //Read id
    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }

    //Read all
    public Iterable<ProductEntity> findAllProducts(){
        return productRepository.findAll();
    }

    public ProductEntity updateProduct(ProductEntity product){
        return productRepository.save(product);
    }

    //Update price
    public Optional<ProductEntity> updatePriceOfProduct(Long id, double price){

        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        foundProduct.get().setPrice(price);
        productRepository.save(foundProduct.get());

        return foundProduct;
    }

    //Delete
    public Optional<ProductEntity> deleteProduct(Long id){

        Optional<ProductEntity> deleteProduct = productRepository.findById(id);

        productRepository.deleteById(deleteProduct.get().getId());
        return deleteProduct;
    }
}
