package com.android_poc.newyorktimesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android_poc.newyorktimesproject.repository.NytRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}