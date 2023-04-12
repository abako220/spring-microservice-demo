package com.troyprogramming.productservice.repository;

import com.troyprogramming.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
