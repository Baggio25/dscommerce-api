package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.ProductDTO;
import com.baggio.dscommerce.entities.Product;
import com.baggio.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product =  productRepository.findById(id).get();
        return new  ProductDTO(product);
    }

}
