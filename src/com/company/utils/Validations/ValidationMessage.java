package com.company.utils.Validations;

public class ValidationMessage implements Validation {
    String message;
    boolean hasValidationMessage = false;

    public ValidationMessage() {
    }

    public boolean hasValidationMessage() {
        return this.hasValidationMessage;
    }

    public void setHasValidationMessage(boolean hasValidationMessage) {
        this.hasValidationMessage = hasValidationMessage;
    }

    public String getMessage() {
        this.hasValidationMessage = false;
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.hasValidationMessage = true;
    }
}
