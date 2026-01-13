package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return "ok";
    }
}
