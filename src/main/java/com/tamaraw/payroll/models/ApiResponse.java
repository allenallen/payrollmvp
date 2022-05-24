package com.tamaraw.payroll.models;

public class ApiResponse<T> {

    private String message;

    private T body;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
