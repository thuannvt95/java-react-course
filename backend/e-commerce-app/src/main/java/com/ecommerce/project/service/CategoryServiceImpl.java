package com.ecommerce.project.service;

import com.ecommerce.project.exception.ApiException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;

//    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getALlCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ApiException("No category created till now");
        }
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category foundCategory = categoryRepository.findFirstByCategoryName(category.getCategoryName());
        if (Objects.nonNull(foundCategory)) {
            throw new ApiException("category name already existed");
        }

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        Optional<Category> foundCat = categoryRepository.findById(id);

        if (foundCat.isEmpty()) {
            throw new ResourceNotFoundException("Category", "categoryId", id);
        }

        categoryRepository.delete(foundCat.get());
    }

    @Override
    public void updateCategory(String id, Category category) {
        Optional<Category> foundCat = categoryRepository.findById(id);

        if (foundCat.isEmpty()) {
            throw new ResourceNotFoundException("Category", "categoryId", id);
        }

        foundCat.get().setCategoryName(category.getCategoryName());
        categoryRepository.save(foundCat.get());
    }
}
