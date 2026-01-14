package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getALlCategories();
    void createCategory(Category category);
    void deleteCategory(String id);
    void updateCategory(String id, Category category);
}
