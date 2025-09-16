package com.baggio.dscommerce.controllers;

import com.baggio.dscommerce.dto.CategoryDTO;
import com.baggio.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public Page<CategoryDTO> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }
}
