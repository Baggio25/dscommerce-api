package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.ProductDTO;
import com.baggio.dscommerce.entities.Product;
import com.baggio.dscommerce.repositories.ProductRepository;
import com.baggio.dscommerce.services.exceptions.DatabaseException;
import com.baggio.dscommerce.services.exceptions.ResourceNotFoundException;
import com.baggio.dscommerce.utils.MapperUtil;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com id: " + id));
        return mapperUtil.map(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return mapperUtil.mapList(products, ProductDTO.class);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = mapperUtil.map(productDTO, Product.class);
        product = productRepository.save(product);
        
        return mapperUtil.map(product, ProductDTO.class);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product productAtual = productRepository.getReferenceById(id);
            Product product = mapperUtil.map(productDTO, Product.class);
            product = productRepository.save(productAtual);
            return mapperUtil.map(product, ProductDTO.class);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado com id: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado com id: " + id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
