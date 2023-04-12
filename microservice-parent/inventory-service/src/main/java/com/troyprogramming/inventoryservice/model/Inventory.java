package com.troyprogramming.inventoryservice.model;

import jakarta.persistence.*;
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
    @Column(nullable = false, unique = true)
    private String skuCode;
    private Integer quantity;

}
