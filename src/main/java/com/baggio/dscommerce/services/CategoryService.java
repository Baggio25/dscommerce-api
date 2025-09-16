package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return new  CategoryDTO(category);
    }

}
