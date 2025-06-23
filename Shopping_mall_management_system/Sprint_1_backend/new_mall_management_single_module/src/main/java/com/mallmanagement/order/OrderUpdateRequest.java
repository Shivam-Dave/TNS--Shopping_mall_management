package com.mallmanagement.order;

import java.time.LocalDateTime;

// This DTO carries all the data for an order update request.
public class OrderUpdateRequest {
    private Long customerId;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;

    // --- Getters and Setters for all fields ---

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
