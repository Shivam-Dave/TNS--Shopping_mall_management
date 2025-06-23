package com.mallmanagement.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Order newOrder = orderService.placeOrder(orderRequest);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(orderId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // FIX: This endpoint now properly handles full updates from the main list's edit form.
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateRequest orderUpdateRequest) {
        try {
            OrderResponse updatedOrder = orderService.updateOrder(orderId, orderUpdateRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // --- FIX: ADDED new endpoints for the details page ---

    @PutMapping("/{orderId}/customer")
    public ResponseEntity<Void> updateOrderCustomer(@PathVariable Long orderId, @RequestBody Long customerId) {
        try {
            orderService.updateOrderCustomer(orderId, customerId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        try {
            orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}