package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories(Pageable pageable) {
        return new ResponseEntity<>(categoryService.getALlCategories(pageable), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/public/categories", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/public/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id,
                                            @RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/admin/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> createCategory(@PathVariable("id") String id) {
        return new ResponseEntity<>( categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
