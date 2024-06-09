package com.petproject.martins.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    public List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);

    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

}
