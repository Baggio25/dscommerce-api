package com.baggio.dscommerce.dto;

import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    //private Set<ProductDTO> products = new HashSet<ProductDTO>();

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();

        /*for(Product prod : category.getProducts()) {
            products.add(new ProductDTO(prod));
        }*/
    }

}
