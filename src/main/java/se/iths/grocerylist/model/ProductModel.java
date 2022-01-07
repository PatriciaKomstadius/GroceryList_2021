package se.iths.grocerylist.model;

public class ProductModel {
    private Long id;
    private String productName;
    private double price;
    private String category;
    private int quantity;

    public ProductModel() {
    }

    public ProductModel(Long id, String productName, double price, String category, int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
