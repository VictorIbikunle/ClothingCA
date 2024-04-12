package com.example.clothingca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_create_admin) {
            startActivity(new Intent(this, CreateAdminActivity.class));
            return true;
        } else if (itemId == R.id.action_home) {
            // No need to restart MainActivity if already there
            return true;
        } else if (itemId == R.id.action_user_details) {
            startActivity(new Intent(this, UserDetailsActivity.class));
            return true;
        } else if (itemId == R.id.action_clothing_list) {
            // Navigate to ClothingListActivity
            startActivity(new Intent(this, ClothingListActivity.class));
            return true;
        } else if (itemId == R.id.action_clothing_item) {
            // Navigate to ClothingItemActivity (assuming this exists)
            startActivity(new Intent(this, ClothingItemActivity.class)); // Make sure this matches your actual class name
            return true;
        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_basket) {
                startActivity(new Intent(MainActivity.this, BasketActivity.class));
                return true;
            } else if (id == R.id.navigation_checkout) {
                startActivity(new Intent(MainActivity.this, Checkout.class));
                return true;
            }

            return false;
        });


    }
}