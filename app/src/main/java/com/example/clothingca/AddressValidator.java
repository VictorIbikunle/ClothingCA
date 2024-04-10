package com.example.clothingca;

public class AddressValidator extends AbstractValidator {
    @Override
    protected boolean isValid(String input) {
        // Simplified validation: just checking it's not empty.
        return input != null && !input.trim().isEmpty();
    }
}
