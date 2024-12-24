package com.stevencode.puppies.rest.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {

    private List<String> errors;

    public ErrorResponse(String message) {
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

}
