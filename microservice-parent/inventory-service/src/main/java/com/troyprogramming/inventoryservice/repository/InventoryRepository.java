package com.troyprogramming.inventoryservice.repository;

import com.troyprogramming.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    LinkedList<Inventory> findBySkuCodeIn(LinkedList<String> skuCode);
}
