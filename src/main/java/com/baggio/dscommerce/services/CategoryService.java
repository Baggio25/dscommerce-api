package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return new  CategoryDTO(category);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::new);
    }
}
