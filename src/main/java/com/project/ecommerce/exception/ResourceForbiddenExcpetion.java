package com.project.ecommerce.exception;

public class ResourceForbiddenExcpetion extends RuntimeException{
    private Integer code;
    private String message;

    public ResourceForbiddenExcpetion() {
        super();
    }

    public ResourceForbiddenExcpetion(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ResourceForbiddenExcpetion(String message) {
        super(message);
        this.message = message;
    }
}
