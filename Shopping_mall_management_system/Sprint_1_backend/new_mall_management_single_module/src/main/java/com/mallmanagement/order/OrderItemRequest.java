    package com.mallmanagement.order;

    public class OrderItemRequest {
        private Long itemId;
        private Integer quantity;

        // Getters and Setters
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
    