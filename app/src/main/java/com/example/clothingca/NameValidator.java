package com.example.clothingca;

public class NameValidator extends AbstractValidator {
    @Override
    protected boolean isValid(String input) {
        return input != null && !input.trim().isEmpty();
    }
}

