package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductEntity> findProductByName(String name) {
        return productRepository.findByProductName(name);
    }

    public Iterable<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity updateProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public Optional<ProductEntity> updatePriceOfProduct(Long id, double price) {

        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        foundProduct.get().setPrice(price);
        productRepository.save(foundProduct.get());

        return foundProduct;
    }

    public Optional<ProductEntity> deleteProduct(Long id) {

        Optional<ProductEntity> deleteProduct = productRepository.findById(id);

        productRepository.deleteById(deleteProduct.get().getId());
        return deleteProduct;
    }
}
