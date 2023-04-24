package main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cadou {
    private String productName;
    private float price;
    private String category;
    @JsonIgnore
    private Integer quantity;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonIgnore
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
