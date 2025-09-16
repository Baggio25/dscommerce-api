package com.baggio.dscommerce.controllers;

import com.baggio.dscommerce.dto.ProductDTO;
import com.baggio.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO finById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> finAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO productDTO) {
        return productService.insert(productDTO);
    }
}
