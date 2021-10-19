package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("lab5milestone1", Context.MODE_PRIVATE);

        System.out.println("SHARED: " + sharedPreferences.getString("username", ""));
        if (!sharedPreferences.getString("username", "").equals("")) {
            switchToLogin(sharedPreferences.getString("username", ""));
        }
    }

    public void login(View view) {
        String user = ((EditText) findViewById(R.id.username)).getText().toString();
        switchToLogin(user);
        sharedPreferences.edit().putString("username", user).apply();
    }

    public void switchToLogin(String user) {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}