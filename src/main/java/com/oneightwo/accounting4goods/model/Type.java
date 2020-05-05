package com.oneightwo.accounting4goods.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "types", schema = "public")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String name;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
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
}
