package com.troyprogramming.orderservice.controller;

import com.troyprogramming.orderservice.dto.OrderRequest;
import com.troyprogramming.orderservice.dto.OrderResponse;
import com.troyprogramming.orderservice.dto.OrderlineItemsDto;
import com.troyprogramming.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod =  "fallbackMethod") //because,before placing order, order service lookup to inventory Ms
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        log.info("Order successfully created {}", orderRequest);
        return "Order successfully placed";
    }

    public String fallbackMethod(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
         return "Service is not available at the moment. Please try again later!";
    }

    @GetMapping
    public List<OrderResponse> getAllOrder() {
        log.info("Orders {}",
                orderService.getAllOrder().toString());
       return  orderService.getAllOrder();

    }

    @GetMapping("/{orderNumber}")
    public List<OrderResponse> getOrderbyOrderNumber(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("list of order and orderLineItems {}", orderService.getOrderbyOrderNumber(orderNumber));
        return orderService.getOrderbyOrderNumber(orderNumber);
    }

    @PostMapping("/update/{orderNumber}")
    public List<OrderResponse> bulkOrderUpdate(@PathVariable(value = "orderNumber") String orderNumber, @RequestBody OrderRequest orderRequest) {
        log.info("list of order and orderLineItems {}", orderService.updateBulkOrder(orderNumber, orderRequest));
        return orderService.getOrderbyOrderNumber(orderNumber);
    }
}
