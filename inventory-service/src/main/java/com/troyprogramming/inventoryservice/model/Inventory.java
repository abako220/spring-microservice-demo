package com.troyprogramming.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "t_inventory")
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String skuCode;
    private Integer quantity;

}
