package com.example.clothingca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetailsActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextUserAddress, editTextUserPhoneNumber, editTextUserCardNumber;
    private Button buttonSubmitUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAddress = findViewById(R.id.editTextUserAddress);
        editTextUserPhoneNumber = findViewById(R.id.editTextUserPhoneNumber);
        editTextUserCardNumber = findViewById(R.id.editTextUserCardNumber);
        buttonSubmitUserDetails = findViewById(R.id.buttonSubmitUserDetails);

        buttonSubmitUserDetails.setOnClickListener(view -> {
            // Initialize the validation chain
            AbstractValidator validatorChain = buildValidatorChain();

            // Collect user inputs
            String name = editTextUserName.getText().toString();
            String address = editTextUserAddress.getText().toString();
            String phoneNumber = editTextUserPhoneNumber.getText().toString();
            String cardNumber = editTextUserCardNumber.getText().toString();

            // Perform validation
            if (validatorChain.validate(name) && validatorChain.validate(address)
                    && validatorChain.validate(phoneNumber) && validatorChain.validate(cardNumber)) {
                // All inputs are valid
                Toast.makeText(UserDetailsActivity.this, "All details are valid!", Toast.LENGTH_SHORT).show();
                // Proceed with processing the valid inputs
            } else {
                // At least one input is invalid
                Toast.makeText(UserDetailsActivity.this, "Please check your details!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AbstractValidator buildValidatorChain() {
        // Setup the chain of validators
        AbstractValidator nameValidator = new NameValidator();
        AbstractValidator addressValidator = new AddressValidator();
        AbstractValidator phoneNumberValidator = new PhoneNumberValidator();
        AbstractValidator cardNumberValidator = new CardNumberValidator();

        nameValidator.setNextValidator(addressValidator);
        addressValidator.setNextValidator(phoneNumberValidator);
        phoneNumberValidator.setNextValidator(cardNumberValidator);

        return nameValidator; // Start of the chain
    }
}