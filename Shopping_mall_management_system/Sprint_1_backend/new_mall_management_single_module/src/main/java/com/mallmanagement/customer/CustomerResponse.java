    package com.mallmanagement.customer;

    // A DTO to represent customer information in an order response.
    public class CustomerResponse {
        private Long id;
        private String name;

        // Constructor
        public CustomerResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
    