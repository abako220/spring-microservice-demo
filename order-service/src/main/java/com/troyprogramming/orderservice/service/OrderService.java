package com.troyprogramming.orderservice.service;

import com.troyprogramming.orderservice.dto.OrderRequest;
import com.troyprogramming.orderservice.dto.OrderResponse;
import com.troyprogramming.orderservice.dto.OrderResponseList;
import com.troyprogramming.orderservice.dto.OrderlineItemsDto;
import com.troyprogramming.orderservice.model.Order;
import com.troyprogramming.orderservice.model.OrderLineItems;
import com.troyprogramming.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public OrderLineItems mapToResponseDto(OrderResponseList orderResponseList) {
        OrderLineItems orderLineItems = OrderLineItems.builder()
                .id(orderResponseList.getId())
                .skuCode(orderResponseList.getSkuCode())
                .price(orderResponseList.getPrice())
                .quantity(orderResponseList.getQuantity())
                .build();
        return orderLineItems;
    }

    public List<OrderResponse> getAllOrder() {
        List<OrderResponse> orderResponseList = new ArrayList<>();
         orderRepository.findAll()
                .forEach(order -> {
                    OrderResponse orderResponse = OrderResponse.builder()
                            .orderNumber(order.getOrderNumber())
                            .orderResponseLists(
                                    this.maptoResponseDto(order.getOrderLineItemsList())

                            ).build();
                    orderResponseList.add(orderResponse);
                });
         return  orderResponseList;

    }

    public List<OrderResponseList> maptoResponseDto(List<OrderLineItems> lineItems) {
        List<OrderResponseList> orderResponseLists = new ArrayList<>();
        for(OrderLineItems orderLineItems : lineItems) {
            orderResponseLists.add(maptoResponseDto1(orderLineItems));
        }
        return orderResponseLists;
    }

    public OrderResponseList maptoResponseDto1(OrderLineItems lineItems) {
        return OrderResponseList.builder()
                .id(lineItems.getId())
                .price(lineItems.getPrice())
                .quantity(lineItems.getQuantity())
                .skuCode(lineItems.getSkuCode())
                .build();

    }

}
