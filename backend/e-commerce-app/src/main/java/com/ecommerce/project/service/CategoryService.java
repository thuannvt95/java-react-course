package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<CategoryResponse> getALlCategories(Pageable pageable);
    CategoryResponse createCategory(CategoryDTO category);
    CategoryResponse deleteCategory(String id);
    CategoryResponse updateCategory(String id, CategoryDTO categoryDTO);
}
