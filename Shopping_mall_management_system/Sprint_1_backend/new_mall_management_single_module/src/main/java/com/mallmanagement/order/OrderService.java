package com.mallmanagement.order;

import com.mallmanagement.customer.Customer;
import com.mallmanagement.customer.CustomerRepository;
import com.mallmanagement.customer.CustomerResponse;
import com.mallmanagement.item.Item;
import com.mallmanagement.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private CustomerRepository customerRepo;
    
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepo.findAllWithDetails().stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepo.findByIdWithDetails(orderId)
            .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + orderId));
        return mapToOrderResponse(order);
    }
    
    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {
        Customer customer = customerRepo.findById(orderRequest.getCustomerId()).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        double totalAmount = 0.0;
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            Item item = itemRepo.findById(itemRequest.getItemId()).orElseThrow(() -> new NoSuchElementException("Item not found"));
            if (item.getQuantity() < itemRequest.getQuantity()) {
                throw new IllegalStateException("Not enough stock for item: " + item.getName());
            }
            item.setQuantity(item.getQuantity() - itemRequest.getQuantity());
            itemRepo.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(item.getPrice());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            totalAmount += item.getPrice() * itemRequest.getQuantity();
        }
        order.setTotalAmount(totalAmount);
        return orderRepo.save(order);
    }

    // FIX: This method now intelligently handles partial updates. It only changes
    // the fields that are provided in the request, preventing other fields from
    // being set to null.
    @Transactional
    public OrderResponse updateOrder(Long orderId, OrderUpdateRequest request) {
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + orderId));

        // Update customer only if a new ID is provided and it's different
        if (request.getCustomerId() != null && !order.getCustomer().getId().equals(request.getCustomerId())) {
            Customer newCustomer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + request.getCustomerId()));
            order.setCustomer(newCustomer);
        }

        // Update other fields only if they are not null in the request
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
        if (request.getTotalAmount() != null) {
            order.setTotalAmount(request.getTotalAmount());
        }
        if (request.getOrderDate() != null) {
            order.setOrderDate(request.getOrderDate());
        }
        
        Order savedOrder = orderRepo.save(order);
        return mapToOrderResponse(savedOrder);
    }
    
    // --- FIX: ADDED new specific update methods for the details page ---

    @Transactional
    public void updateOrderCustomer(Long orderId, Long customerId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        order.setCustomer(customer);
        orderRepo.save(order);
    }

    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
        order.setStatus(status);
        orderRepo.save(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepo.findByIdWithDetails(orderId)
            .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + orderId));
        // Restock items when an order is deleted
        for (OrderItem orderItem : order.getOrderItems()) {
            Item item = orderItem.getItem();
            item.setQuantity(item.getQuantity() + orderItem.getQuantity());
            itemRepo.save(item);
        }
        orderRepo.delete(order);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        CustomerResponse customerResponse = new CustomerResponse(order.getCustomer().getId(), order.getCustomer().getName());
        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(item -> new OrderItemResponse(item.getId(), item.getItem().getName(), item.getQuantity(), item.getPriceAtOrder()))
                .collect(Collectors.toList());
        return new OrderResponse(order.getId(), order.getOrderDate(), order.getStatus(), order.getTotalAmount(), customerResponse, itemResponses);
    }
}