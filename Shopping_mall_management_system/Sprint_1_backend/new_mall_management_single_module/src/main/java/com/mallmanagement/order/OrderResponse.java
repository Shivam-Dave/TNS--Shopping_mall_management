package com.mallmanagement.order;

import com.mallmanagement.customer.CustomerResponse;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private String status; // <-- ADDED THIS FIELD
    private Double totalAmount;
    private CustomerResponse customer;
    private List<OrderItemResponse> orderItems;

    // --- You will need to add a new constructor that includes the status ---
    public OrderResponse(Long id, LocalDateTime orderDate, String status, Double totalAmount, CustomerResponse customer, List<OrderItemResponse> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.orderItems = orderItems;
    }
    
    // Getters and setters...
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public CustomerResponse getCustomer() { return customer; }
    public void setCustomer(CustomerResponse customer) { this.customer = customer; }
    public List<OrderItemResponse> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemResponse> orderItems) { this.orderItems = orderItems; }
}
