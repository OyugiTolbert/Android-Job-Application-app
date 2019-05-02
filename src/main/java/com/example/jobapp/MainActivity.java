package com.example.jobapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companydrawerbtn();
        appdrawerbtn();
    }

    private void companydrawerbtn() {
        Button nextcompany = findViewById(R.id.nextcompdrawer);
        nextcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, Company_drawer.class);
                startActivity(next);


            }
        });


    }

    private void appdrawerbtn() {
        Button nextapp = findViewById(R.id.nextappdrawer);
        nextapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextapp = new Intent(MainActivity.this, Applicant_drawer.class);
                startActivity(nextapp);
            }
        });
    }
}
