package com.ecommerce.project.payload;

import com.ecommerce.project.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String productId;
    @NotBlank
    private String productName;
    private String description;
    private String image;
    private Integer quantity;
    private Double discount;
    private Double price;

    @NotNull(message = "Category cannot be null")
    private CategoryDTO categoryDTO;
}
