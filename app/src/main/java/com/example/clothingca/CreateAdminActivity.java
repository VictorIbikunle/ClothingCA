package com.example.clothingca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAdminActivity extends AppCompatActivity {

    EditText adminName, adminId;
    Button submitAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        adminName = findViewById(R.id.adminName);
        adminId = findViewById(R.id.adminId);
        submitAdmin = findViewById(R.id.submitAdmin);

        submitAdmin.setOnClickListener(view -> {
            String name = adminName.getText().toString();
            String id = adminId.getText().toString();

            if (id.matches("^[a-zA-Z]{1}\\d{4}$")) {
                // ID is valid
                Toast.makeText(CreateAdminActivity.this, "Creating Admin Account...", Toast.LENGTH_SHORT).show();
                // Proceed with creating the admin account
            } else {
                // ID is invalid
                Toast.makeText(CreateAdminActivity.this, "Invalid ID format!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
