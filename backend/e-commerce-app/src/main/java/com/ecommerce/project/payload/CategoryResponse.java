package com.ecommerce.project.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private String categoryId;
    private String categoryName;
    public String createdBy;
    public String updatedBy;
    public Date createdDate;
    public Date updatedDate;
}
