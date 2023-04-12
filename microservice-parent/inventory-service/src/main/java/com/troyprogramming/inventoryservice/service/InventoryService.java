package com.troyprogramming.inventoryservice.service;

import com.troyprogramming.inventoryservice.model.Inventory;
import com.troyprogramming.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
      return inventoryRepository.findBySkuCode(skuCode).isPresent();

    }
}
