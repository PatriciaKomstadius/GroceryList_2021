package se.iths.grocerylist.model;


import java.util.Set;

public class GroceryListModel {


    private String name;
    private Set<ProductModel> products;

    public GroceryListModel() {
    }

    public GroceryListModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductModel> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroceryListModel{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
