package com.example.clothingca;

public class PhoneNumberValidator extends AbstractValidator {
    @Override
    protected boolean isValid(String input) {
        // Simplified to match a 10-digit number
        return input.matches("\\d{10}");
    }
}
