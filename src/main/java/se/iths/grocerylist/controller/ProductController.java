package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.ProductEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.exception.MethodNotAllowedException;
import se.iths.grocerylist.mapper.ProductMapper;
import se.iths.grocerylist.model.ProductModel;
import se.iths.grocerylist.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping()
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {

        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new BadRequestException("productName cannot be empty!");
        }

        ProductEntity createdProduct = productService.createProduct(productMapper.productModelToProductEntity(product));
        ProductModel response = productMapper.productEntityToProductModel(createdProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {

        Optional<ProductEntity> foundProduct = productService.findProductById(id);

        if (foundProduct.isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }

        ProductModel response = productMapper.productEntityToProductModel(foundProduct.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProductModel>> getAllProducts() {
        Iterable<ProductEntity> products = productService.findAllProducts();

        if (!products.iterator().hasNext()) {
            throw new EntityNotFoundException("There are no products registered in the database.");
        }

        Iterable<ProductModel> allProductModels = productMapper.allEntityToAllModels(products);

        return new ResponseEntity<>(allProductModels, HttpStatus.FOUND);
    }

    @PutMapping()
    public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel product) {

        if (product.getId() == null) {
            throw new MethodNotAllowedException("You need to specify ID on product to be updated");
        }
        if (productService.findProductById(product.getId()).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(product.getId()));
        }

        ProductEntity updatedProduct = productService.updateProduct(productMapper.productModelToProductEntity(product));
        ProductModel response = productMapper.productEntityToProductModel(updatedProduct);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductModel> updatePriceOfProduct(@PathVariable Long id, @RequestBody ProductModel price) {

        Optional<ProductEntity> updatedProduct = productService.updatePriceOfProduct(id, price.getPrice());

        if (updatedProduct.isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }
        ProductModel response = productMapper.productEntityToProductModel(updatedProduct.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        if (productService.findProductById(id).isEmpty()) {
            throw new EntityNotFoundException(responseMessage(id));
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String responseMessage(Long id) {
        return "There is no product with ID " + id + " in database.";
    }
}
