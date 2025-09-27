package com.baggio.dscommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

}
