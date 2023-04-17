package com.troyprogramming.inventoryservice.service;

import com.troyprogramming.inventoryservice.dto.InventoryResponse;
import com.troyprogramming.inventoryservice.model.Inventory;
import com.troyprogramming.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    //@SneakyThrows
    public LinkedList<InventoryResponse> isInStock(LinkedList<String> skuCode) throws InterruptedException {
        log.info("wait started");
        Thread.sleep(10000);
        log.info("wait ended");
      return inventoryRepository.findBySkuCodeIn(skuCode).stream()
              .map(inventory ->
                InventoryResponse.builder()
                          .skuCode(inventory.getSkuCode())
                          .inStock(inventory.getQuantity() > 0)
                          .build()
              ).collect(Collectors.toCollection(LinkedList::new));

    }
}
