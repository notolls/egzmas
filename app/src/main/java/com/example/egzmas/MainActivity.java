package com.example.egzmas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Įdedame pirmąjį fragmentą
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_input_container, new InputFragment())
                .commit();

        // Įdedame antrąjį fragmentą
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_chart_container, new ChartFragment())
                .commit();
    }
}
