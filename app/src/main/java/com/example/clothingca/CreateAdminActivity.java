package com.example.clothingca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateAdminActivity extends AppCompatActivity {

    EditText adminName, adminId;
    Button submitAdmin;




    public static class Admin {
        public String name;
        public String id;

        public Admin() {
            // Default constructor required for calls to DataSnapshot.getValue(Admin.class)
        }

        public Admin(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        adminName = findViewById(R.id.adminName);
        adminId = findViewById(R.id.adminId);
        submitAdmin = findViewById(R.id.submitAdmin);

        submitAdmin.setOnClickListener(view -> {
            String name = adminName.getText().toString().trim();
            String id = adminId.getText().toString().trim();

            if (!name.isEmpty() && id.matches("^[a-zA-Z]{1}\\d{4}$")) {
                // Both name and ID are valid
                Toast.makeText(CreateAdminActivity.this, "Creating Admin Account...", Toast.LENGTH_SHORT).show();

                // Reference to your Firebase Realtime Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("admins");

                // Creating a new Admin object
                Admin newAdmin = new Admin(name, id);

                // Saving the admin to the database
                // Use push() for a unique ID or set the ID as the key if you want to avoid duplicates
                databaseReference.push().setValue(newAdmin)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(CreateAdminActivity.this, "Admin account created successfully!", Toast.LENGTH_LONG).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(CreateAdminActivity.this, "Failed to create admin account.", Toast.LENGTH_LONG).show());
            } else {
                // Invalid input handling
                Toast.makeText(CreateAdminActivity.this, "Invalid input. Please check the name and ID format.", Toast.LENGTH_LONG).show();
            }
        });




    }
}
