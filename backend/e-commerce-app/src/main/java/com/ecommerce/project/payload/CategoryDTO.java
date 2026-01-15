package com.ecommerce.project.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private String categoryId;

    @NotBlank(message = "categoryName is mandatory")
    @Size(min = 5, message = "Category name must contain at least 5 characters")
    private String categoryName;
}
