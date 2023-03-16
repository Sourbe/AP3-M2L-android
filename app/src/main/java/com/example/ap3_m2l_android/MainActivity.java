package com.example.ap3_m2l_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ztLogin = (EditText) findViewById(R.id.ztLogin);
        EditText ztPassword = (EditText) findViewById(R.id.ztPassword);
        Button btConnect = (Button) findViewById(R.id.btConnect);

        final MySQLiteHelper myDbHelper = new MySQLiteHelper(this);



        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = ztLogin.getText().toString();
                String password = ztPassword.getText().toString();

                if(login.length() == 0 || password.length() == 0){
                    Toast.makeText(MainActivity.this, "Vous devez renseigner tous les " +
                            "champs", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(myDbHelper.login(login, password)){
                        Toast.makeText(MainActivity.this, "Connecté avec succès", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ChoixDateSalle.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }
}