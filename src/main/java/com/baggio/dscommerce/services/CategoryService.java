package com.baggio.dscommerce.services;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.repositories.CategoryRepository;
import com.baggio.dscommerce.utils.MapperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        return mapperUtil.map(category, CategoryDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return mapperUtil.mapList(categories, CategoryDTO.class);
    }
}
