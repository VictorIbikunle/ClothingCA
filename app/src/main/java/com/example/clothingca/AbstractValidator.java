package com.example.clothingca;

public abstract class AbstractValidator {
    protected AbstractValidator nextValidator;

    public void setNextValidator(AbstractValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    public boolean validate(String input) {
        if (isValid(input)) {
            if (nextValidator != null) {
                return nextValidator.validate(input);
            }
            return true; // Valid and no next validator
        }
        return false; // Not valid
    }

    abstract protected boolean isValid(String input);
}

