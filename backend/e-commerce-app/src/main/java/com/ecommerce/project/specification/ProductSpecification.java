package com.ecommerce.project.specification;

import com.ecommerce.project.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.isBlank())
                        ? null
                        : cb.like(
                        cb.lower(root.get("productName")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Product> hasCategoryId(String categoryId) {
        return (root, query, cb) ->
                (categoryId == null || categoryId.isBlank())
                        ? null
                        : cb.equal(root.get("category").get("categoryId"), categoryId);
    }

    public static Specification<Product> hasCategoryName(String categoryName) {
        return (root, query, cb) ->
                (categoryName == null || categoryName.isBlank())
                        ? null
                        : cb.like(
                        cb.lower(root.get("category").get("categoryName")),
                        "%" + categoryName.toLowerCase() + "%"
                );
    }
}
