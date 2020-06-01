package com.alaaapps.jo3t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username;
        String password;
        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);
        username = sp.getString("login", null);
        password = sp.getString("pwd", null);

//        Log.d(username,password);
        if (username != null && password != null) {
            if (username.equals("alaa") && password.equals("alaa")) {
                Intent intent = new Intent(this, BaseActivity.class);
                startActivity(intent);
            }

        } else {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        }


    }
}
