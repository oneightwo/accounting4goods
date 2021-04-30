package com.oneightwo.accounting4goods.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String surname;
    private String name;
    private String patronymic;
    private String username;
    private String password;
    @JoinColumn(name = "role_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Role role;

    public User() {
    }

    public User(String surname, String name, String patronymic, String username, String password, Role role) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
