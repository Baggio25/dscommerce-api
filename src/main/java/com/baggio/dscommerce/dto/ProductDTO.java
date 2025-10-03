package com.baggio.dscommerce.dto;

import com.baggio.dscommerce.entities.Category;
import com.baggio.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "O [nome] é obrigatório")
    @Size(min = 3, max = 80, message = "O nome deve conter ao menos 3 e no máximo 80 caracteres")
    private String name;

    @NotBlank(message = "A [descrição] é obrigatória")
    @Size(min = 10, message = "A descrição deve conter ao menos 10 caracteres")
    private String description;

    @Positive(message = "O [preço] deve ser positivo")
    private Double price;

    private String imgUrl;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();

        for(Category cat : product.getCategories()) {
            categories.add(new CategoryDTO(cat));
        }
    }

}
