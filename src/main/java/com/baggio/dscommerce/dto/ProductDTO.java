package com.baggio.dscommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
