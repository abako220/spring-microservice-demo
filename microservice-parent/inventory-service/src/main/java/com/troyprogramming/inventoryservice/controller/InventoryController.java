package com.troyprogramming.inventoryservice.controller;

import com.troyprogramming.inventoryservice.dto.InventoryResponse;
import com.troyprogramming.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")

public class InventoryController
{

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LinkedList<InventoryResponse> isInStock(@RequestParam(name = "sku-code") LinkedList<String> skuCode) throws InterruptedException {
       return inventoryService.isInStock(skuCode);
    }
}
