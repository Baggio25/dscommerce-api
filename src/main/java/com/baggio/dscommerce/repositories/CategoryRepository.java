package com.baggio.dscommerce.repositories;

import com.baggio.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
