package com.example.clothingca;

public class CardNumberValidator extends AbstractValidator {
    @Override
    protected boolean isValid(String input) {
        // Match 1 letter followed by 4 digits
        return input.matches("^[a-zA-Z]{1}\\d{4}$");
    }
}

