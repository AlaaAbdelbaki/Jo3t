package com.alaaapps.jo3t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;
    private TextView signUp;
    private Switch remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signUp = (TextView) findViewById(R.id.signup);
        remember = (Switch) findViewById(R.id.remember);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user;
                String pwd;

                user = username.getText().toString();
                pwd = password.getText().toString();

                if (user.equals("alaa") && pwd.equals("alaa")) {
                    if (remember.isChecked()) {
                        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor Ed = sp.edit();
                        Ed.putString("login", user);
                        Ed.putString("pwd", pwd);
                        Ed.commit();
                        openActivity();
                    } else {

                        openActivity();
                    }

                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    public void openActivity() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fargment_container,new HomeFragment()).commit();
    }

    public void openSignUp() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
