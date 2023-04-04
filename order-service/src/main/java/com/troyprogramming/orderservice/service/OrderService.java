package com.troyprogramming.orderservice.service;

import com.troyprogramming.orderservice.dto.OrderRequest;
import com.troyprogramming.orderservice.dto.OrderlineItemsDto;
import com.troyprogramming.orderservice.model.Order;
import com.troyprogramming.orderservice.model.OrderLineItems;
import com.troyprogramming.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .build();
        List<OrderLineItems> orderLineItems = orderRequest.getOrderlineItemsDtos().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);

    }

    public OrderLineItems mapToDto(OrderlineItemsDto orderlineItemsDto) {
        OrderLineItems orderLineItems = OrderLineItems.builder()
                .id(orderlineItemsDto.getId())
                .skuCode(orderlineItemsDto.getSkuCode())
                .price(orderlineItemsDto.getPrice())
                .quantity(orderlineItemsDto.getQuantity())
                .build();
        return orderLineItems;
    }
}
