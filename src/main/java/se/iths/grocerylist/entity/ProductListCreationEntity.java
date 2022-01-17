package se.iths.grocerylist.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductListCreationEntity {
    private String currentGroceryList;
    private List<ProductEntity> productslist = new ArrayList<>();

    public ProductListCreationEntity() {
    }

    public ProductListCreationEntity(String currentGroceryList, List<ProductEntity> product) {
        this.currentGroceryList = currentGroceryList;
        this.productslist = product;
    }

    public String getCurrentGroceryList() {
        return currentGroceryList;
    }

    public void setCurrentGroceryList(String currentGroceryList) {
        this.currentGroceryList = currentGroceryList;
    }

    public void addList(ProductEntity product) {
        this.productslist.add(product);
    }

    public List<ProductEntity> getProductslist() {
        return productslist;
    }

    public void setProductslist(List<ProductEntity> product) {
        this.productslist = product;
    }
}
