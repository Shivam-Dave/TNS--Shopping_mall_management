package com.mallmanagement.order;

public class OrderItemResponse {
    private Long id; // <-- ADDED THIS FIELD
    private String itemName;
    private int quantity;
    private double priceAtOrder;

    // Updated constructor
    public OrderItemResponse(Long id, String itemName, int quantity, double priceAtOrder) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    // Getter and Setter for the new field
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    // ... existing getters and setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
