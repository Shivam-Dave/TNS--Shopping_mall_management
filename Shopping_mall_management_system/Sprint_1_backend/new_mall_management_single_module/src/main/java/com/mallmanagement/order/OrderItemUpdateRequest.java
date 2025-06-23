package com.mallmanagement.order;

// This DTO carries the new quantity for an order item update.
public class OrderItemUpdateRequest {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
