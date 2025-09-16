package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.ProductDTO;
import com.baggio.dscommerce.entities.Product;
import com.baggio.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDTO::new); // products.map(product -> newProductDTO(product))
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = dtoToProduct(productDTO);
        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    private static Product dtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        return product;
    }

}
