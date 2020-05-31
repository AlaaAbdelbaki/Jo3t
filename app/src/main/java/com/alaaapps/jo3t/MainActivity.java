package com.alaaapps.jo3t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
//        Toolbar toolbar= findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }
}
