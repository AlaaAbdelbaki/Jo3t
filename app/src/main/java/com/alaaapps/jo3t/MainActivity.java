package com.alaaapps.jo3t;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }

        } else {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        }


    }
}
