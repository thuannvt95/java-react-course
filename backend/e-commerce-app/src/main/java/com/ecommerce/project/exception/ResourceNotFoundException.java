package com.ecommerce.project.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fileName;
    Long fieldId;

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(String resourceName, String field, String fileName) {
        super(String.format("%s not found with %s: %s", resourceName, field, fileName));
        this.resourceName = resourceName;
        this.field = field;
        this.fileName = fileName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s: %d", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
