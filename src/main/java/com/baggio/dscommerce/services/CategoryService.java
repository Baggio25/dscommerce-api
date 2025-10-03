package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.repositories.CategoryRepository;
import com.baggio.dscommerce.services.exceptions.DatabaseException;
import com.baggio.dscommerce.services.exceptions.ResourceNotFoundException;
import com.baggio.dscommerce.utils.MapperUtil;

import com.baggio.dscommerce.utils.Messages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return new CategoryDTO(category);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::new);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        dtoToEntity(categoryDTO, category);
        categoryRepository.save(category);

        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            dtoToEntity(categoryDTO, category);
            category = categoryRepository.save(category);
            return new CategoryDTO(category);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(Messages.RECURSO_NAO_ENCONTRADO + " " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(Messages.RECURSO_NAO_ENCONTRADO + " " + id);
        }

        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(Messages.INTEGRIDADE_REFERENCIAL);
        }
    }

    private void dtoToEntity(CategoryDTO categoryDTO, Category category) {
        category.setName(categoryDTO.getName());
    }

}
