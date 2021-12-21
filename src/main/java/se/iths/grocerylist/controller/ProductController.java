package se.iths.grocerylist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.entity.UserEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
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
    public ResponseEntity<ProductEntity> createProduct(@ModelAttribute ("productEntity") @RequestBody ProductEntity product) {

        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new BadRequestException("productName cannot be empty!");
        }
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            throw new BadRequestException("category cannot be empty");
        }
        if (product.getPrice() <= 0) {
            throw new BadRequestException("price cannot be empty or below 0");
        }

        ProductEntity createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);

    }

    //GET id
    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> getProductById(@PathVariable Long id) {
        Optional<ProductEntity> foundProduct = productService.findProductById(id);

        if(foundProduct.isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }



    //GET all
    @GetMapping()
    public ResponseEntity<Iterable<ProductEntity>> getAllProducts() {
        Iterable<ProductEntity> products = productService.findAllProducts();

        if(!products.iterator().hasNext()){
            throw new EntityNotFoundException("There are no products registered in the database.");
        }
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    //PUT
    @PutMapping()
    public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity product) {

        if(product.getId()==null){
            throw new MethodNotAllowedException("You need to specify ID on product to be updated");
        }
        if(productService.findProductById(product.getId()).isEmpty()){
            throw new EntityNotFoundException(responseMessage(product.getId()));
        }

        ProductEntity updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    //PATCH
    @PatchMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> updatePriceOfProduct(@PathVariable Long id, @RequestBody ProductEntity price) {

        if(productService.findProductById(id).isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        Optional<ProductEntity> updatedProduct = productService.updatePriceOfProduct(id, price.getPrice());

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


    //REMOVE
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        if(productService.findProductById(id).isEmpty()){
            throw new EntityNotFoundException(responseMessage(id));
        }

        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private String responseMessage(Long id) {
        return "There is no product with ID " + id + " in database.";
    }
}

