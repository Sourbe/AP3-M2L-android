package com.example.ap3_m2l_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Formation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        final MySQLiteHelper myDbHelper = new MySQLiteHelper(this);

        Intent intent = getIntent();
        // On suppose que tu as mis un String dans l'Intent via le putExtra()
        Bundle bundle = getIntent().getExtras();
        String IdD = bundle.getString("IdD");
        String IdF = bundle.getString("IdF");

        Log.i("idD : " + IdD ,"idF : " + IdF);
        String[] formation = myDbHelper.getFormation(Integer.parseInt(IdD), Integer.parseInt(IdF));

        TextView libelle = findViewById(R.id.formation_libelle);
        TextView description = findViewById(R.id.formation_description);

        libelle.setText(formation[0]);
        description.setText(formation[1]);

        ListView sessions = (ListView) findViewById(R.id.formation_sessions);
        ArrayAdapter sessionsContent = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myDbHelper.getSessions(Integer.parseInt(IdD), Integer.parseInt(IdF)));
        sessions.setAdapter(sessionsContent);

        Toast.makeText(getApplicationContext(),
                "IdD :" + IdD + ", IdF : " + IdF,
                Toast.LENGTH_LONG).show();


    }
}