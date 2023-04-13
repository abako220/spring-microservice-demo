package com.troyprogramming.inventoryservice;

import com.troyprogramming.inventoryservice.model.Inventory;
import com.troyprogramming.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean

	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			List<Inventory> inventoryList = new ArrayList<>();
			inventoryList.add( new Inventory(1L,"iphone_13", 20));
			inventoryList.add( new Inventory(2L, "iphone_13_pro", 3));
			inventoryList.add( new Inventory(3L,"iphone_14", 0));
			inventoryRepository.saveAll(inventoryList);
		};


	}
}
