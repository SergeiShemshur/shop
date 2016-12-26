package shop.model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private int category;
    private String modelName;
    private BigDecimal price;



    public Product(int category, String modelName, BigDecimal price) {
        this.category = category;
        this.modelName = modelName;
        this.price = price;
    }

    public Product() {
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (category != product.category) return false;
        if (modelName != null ? !modelName.equals(product.modelName) : product.modelName != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;

    }

    @Override
    public int hashCode() {
        int result = category;
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", modelName='" + modelName + '\'' +
                ", price=" + price.setScale(2, 2) +
                '}';
    }
}
