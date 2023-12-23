package com.project.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {
    private Integer code;
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
