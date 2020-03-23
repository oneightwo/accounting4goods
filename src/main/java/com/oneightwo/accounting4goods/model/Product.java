package com.oneightwo.accounting4goods.model;

public class Product {

    private String name;
    private String type;
    private String description;
    private Long count;
    private Double price;
    private String manufacturer;

    public Product() {
    }

    public Product(String name, String type, String description, Long count, Double price, String manufacturer) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.count = count;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
