package com.example.ap3_m2l_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Accueil extends AppCompatActivity {

    private List<Button> ListButtons = new ArrayList<Button>();


    final MySQLiteHelper myDbHelper = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Spinner domaine = (Spinner) findViewById(R.id.s_domaine);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.domaine_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        domaine.setAdapter(adapter);

        domaine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShowFormationWithDomaineId(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
    private void ShowFormationWithDomaineId(int ID){
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);

        for (int i = 0; i < ListButtons.size(); i++) {
            lm.removeView(ListButtons.get(i));
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        String[] Formations = myDbHelper.getFormations(ID);

        //Create four
        for (int j = 0; j < Formations.length; j++) {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setId(j + 1);
            btn.setText(Formations[j]);
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            final int index = j;
            // Set click listener for button
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Log.i("TAG", "index :" + index);

                    Toast.makeText(getApplicationContext(),
                            "Clicked Button Index :" + index,
                            Toast.LENGTH_LONG).show();

                }
            });

            //Add button to LinearLayout
            ListButtons.add(btn);
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }
    }
}