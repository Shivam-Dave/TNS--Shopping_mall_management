package com.mallmanagement.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * This custom query solves the LazyInitializationException by telling JPA
     * to fetch a single Order and its associated Customer and OrderItems all at once.
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer LEFT JOIN FETCH o.orderItems WHERE o.id = :orderId")
    Optional<Order> findByIdWithDetails(Long orderId);

    /**
     * This query fetches all orders with their details in a single database call.
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer LEFT JOIN FETCH o.orderItems")
    List<Order> findAllWithDetails();
}
