package com.ecommerce.project.service;

import com.ecommerce.project.exception.ApiException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    ModelMapper modelMapper;

//    private List<Category> categories = new ArrayList<>();

    @Override
    public Page<CategoryResponse> getALlCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ApiException("No category created till now");
        }
        List<CategoryResponse> categoryResponses = categories.getContent()
                                                             .stream()
                                                             .map(c -> modelMapper.map(c, CategoryResponse.class))
                                                             .toList();

        return new PageImpl<>(categoryResponses, categories.getPageable(), categories.getTotalElements());
    }

    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDto) {
        Category foundCategory = categoryRepository.findFirstByCategoryName(categoryDto.getCategoryName());
        if (Objects.nonNull(foundCategory)) {
            throw new ApiException("category name already existed");
        }
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());

        categoryRepository.save(category);

        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse deleteCategory(String id) {
        Optional<Category> foundCat = categoryRepository.findById(id);

        if (foundCat.isEmpty()) {
            throw new ResourceNotFoundException("Category", "categoryId", id);
        }

        categoryRepository.delete(foundCat.get());

        return modelMapper.map(foundCat.get(), CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(String id, CategoryDTO categoryDTO) {
        Optional<Category> foundCat = categoryRepository.findById(id);

        if (foundCat.isEmpty()) {
            throw new ResourceNotFoundException("Category", "categoryId", id);
        }

        foundCat.get().setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(foundCat.get());

        return modelMapper.map(foundCat.get(), CategoryResponse.class);
    }
}
