package com.oneightwo.accounting4goods.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String name;
    @JoinColumn(name = "type_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Type type;
    private String description;
    private Long count;
    private Double price;
    private String manufacturer;

    public Product() {
    }

    public Product(String name, Type type, String description, Long count, Double price, String manufacturer) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.count = count;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Product(BigInteger id, String name, Type type, String description, Long count, Double price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.count = count;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
