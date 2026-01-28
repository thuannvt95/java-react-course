package com.ecommerce.project.repository;

import com.ecommerce.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p " +
            " WHERE (coalesce(:productName, null) is null or p.productName LIKE %:productName%) " +
            " AND (coalesce(:categoryId, null) is null or p.category.categoryId = :categoryId) " +
            " AND (coalesce(:categoryName, null) is null or p.category.categoryName LIKE %:categoryName%)")
    Page<Product> findByKeyword(@Param("productName") String productName,
                                @Param("categoryId") String categoryId,
                                @Param("categoryName") String categoryName,
                                Pageable pageable);
}
