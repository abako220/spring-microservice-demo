package com.troyprogramming.orderservice.repository;

import com.troyprogramming.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItems, Long> {
}
