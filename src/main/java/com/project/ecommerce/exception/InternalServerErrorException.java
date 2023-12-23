package com.project.ecommerce.exception;

public class InternalServerErrorException extends RuntimeException{
    private Integer code;
    private String message;

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public InternalServerErrorException(String message) {
        super(message);
        this.message = message;
    }

}
