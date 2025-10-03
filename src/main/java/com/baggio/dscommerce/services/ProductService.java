package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.dto.ProductDTO;
import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.entities.Product;
import com.baggio.dscommerce.repositories.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    //@Autowired
    //private MapperUtil mapperUtil;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com id: " + id));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        dtoToEntity(productDTO, product);
        product = productRepository.save(product);
        
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product productAtual = productRepository.getReferenceById(id);
            dtoToEntity(productDTO, productAtual);
            productAtual = productRepository.save(productAtual);
            return new ProductDTO(productAtual);
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

    private void dtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        for(CategoryDTO catDTO : productDTO.getCategories()) {
            Category cat = categoryRepository.getReferenceById(catDTO.getId());
            product.getCategories().add(cat);
        }
    }

}
