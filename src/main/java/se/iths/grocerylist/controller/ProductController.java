package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.service.ProductService;

import java.util.Optional;


@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //POST
    @PostMapping()
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        ProductEntity createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    //GET id
    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> getProductById(@PathVariable Long id) {
        Optional<ProductEntity> getList = productService.findProductById(id);

        return new ResponseEntity<>(getList, HttpStatus.OK);
    }

    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<ProductEntity>> getAllProducts() {
        Iterable<ProductEntity> products = productService.findAllProducts();

        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    //PUT
    @PutMapping()
    public ResponseEntity<ProductEntity>updateProduct(@RequestBody ProductEntity product){
        ProductEntity updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> updatePriceOfProduct(@PathVariable Long id, @RequestBody ProductEntity price) {
        Optional<ProductEntity> updatedProduct = productService.updatePriceOfProduct(id, price.getPrice());

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

