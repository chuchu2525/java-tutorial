package com.example.demo.library.dto;

public class ActionResponse {
    private final boolean success;
    private final String message;

    public ActionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
