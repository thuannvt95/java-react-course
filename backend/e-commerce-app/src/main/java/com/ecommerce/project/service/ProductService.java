package com.ecommerce.project.service;

import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Page<ProductResponse> getAllProducts(String productName, String categoryId, String categoryName, Pageable pageable);
    ProductResponse createProduct(ProductDTO productDTO);
    ProductResponse updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);
    ProductResponse uploadProductImage(String id, MultipartFile image);
}