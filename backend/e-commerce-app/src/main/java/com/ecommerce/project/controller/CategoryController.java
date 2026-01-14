package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Category> getAllCategories() {
        return categoryService.getALlCategories();
    }

    @RequestMapping(path = "/api/public/categories", method = RequestMethod.POST)
    public String createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return "ok";
    }

    @RequestMapping(path = "/api/public/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id,
                                            @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/api/admin/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> createCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
