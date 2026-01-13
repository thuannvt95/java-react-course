package com.ecommerce.project.model;

import com.ecommerce.project.model.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Category extends CommonEntity {
    @Id
    private Long categoryId;
    private String categoryName;
}
