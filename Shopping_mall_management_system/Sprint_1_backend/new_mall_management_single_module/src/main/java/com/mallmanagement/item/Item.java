    package com.mallmanagement.item;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.mallmanagement.shopowner.ShopOwner;
    import javax.persistence.*;

    @Entity
    @Table(name = "item")
    public class Item {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String description;
        private Double price;
        private Integer quantity; // Stock quantity

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "shop_owner_id", nullable = false)
        @JsonIgnore // Prevents infinite recursion when serializing
        private ShopOwner shopOwner;

        // Getters, Setters, and Constructors
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public ShopOwner getShopOwner() { return shopOwner; }
        public void setShopOwner(ShopOwner shopOwner) { this.shopOwner = shopOwner; }
    }
    