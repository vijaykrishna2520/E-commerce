package com.project.ecommerce.exception;

public class BadRequestException extends RuntimeException {
    private Integer code;
    private String message;

    public BadRequestException() {
        super();
    }

    public BadRequestException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

}
