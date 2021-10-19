package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView welcomeView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedPreferences = getSharedPreferences("lab5milestone1", Context.MODE_PRIVATE);

        welcomeView = (TextView) findViewById(R.id.welcome);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        welcomeView.setText("Welcome, " + user + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                return logout();
            case R.id.add_note:
                return add_note();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean logout() {
        Intent intent = new Intent(this, MainActivity.class);
        sharedPreferences.edit().remove("username").apply();
        startActivity(intent);
        return true;
    }

    public boolean add_note() {
        return true;
    }
}