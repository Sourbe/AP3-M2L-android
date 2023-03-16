package com.example.ap3_m2l_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Formation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        Intent intent = getIntent();
        // On suppose que tu as mis un String dans l'Intent via le putExtra()
        String IdD = intent.getStringExtra("IdD");
        String IdF = intent.getStringExtra("IdF");

        Toast.makeText(getApplicationContext(),
                "IdD :" + IdD,
                Toast.LENGTH_LONG).show();
    }
}