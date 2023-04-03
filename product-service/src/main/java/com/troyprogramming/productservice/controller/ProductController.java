package com.troyprogramming.productservice.controller;

import com.troyprogramming.productservice.dto.ProductRequest;
import com.troyprogramming.productservice.dto.ProductResponse;
import com.troyprogramming.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
       List<ProductResponse> response = productService.getAllProduct();
       return response;
    }
}
