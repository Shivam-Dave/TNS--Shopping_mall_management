package com.mallmanagement.shopowner;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shop_owner")
public class ShopOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String shopName;
    private LocalDate registrationDate;

    // Constructors
    public ShopOwner() {
    }

    public ShopOwner(Long id, String name, String email, String shopName, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.shopName = shopName;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
