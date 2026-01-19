package com.ecommerce.project.payload;

import com.ecommerce.project.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
    private String productId;
    private String productName;
    private String description;
    private String image;
    private Double price;
    private Double specialPrice;

    private CategoryResponse category;
}
