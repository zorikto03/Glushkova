package com.zorik.glushkova;

import android.content.Intent;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void lab1_Glushkova(View view) {
        Intent intent = new Intent(this, Activity_lab1_Glushkova.class);
        startActivity(intent);
    }


    public void Button_Pi(View view) {
        Intent intent = new Intent(this, Activity_Pi.class);
        startActivity(intent);
    }
}
