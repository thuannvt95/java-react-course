package com.ecommerce.project.payload;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse {
    private String message;
    public Object responseMessage;
    private Boolean status;

    public ApiResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(Object responseMessage, Boolean status) {
        this.responseMessage = responseMessage;
        this.status = status;
    }
}
