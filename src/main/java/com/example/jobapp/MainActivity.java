package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button nextcompany, nextapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companydrawerbtn();
        appdrawerbtn();
    }

    public void companydrawerbtn() {
        nextcompany = findViewById(R.id.nextcompdrawer);
        nextcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, Company_drawer.class);
                startActivity(next);


            }
        });


    }

    public void appdrawerbtn() {
        nextapp = findViewById(R.id.nextappdrawer);
        nextapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextapp = new Intent(MainActivity.this, Applicant_drawer.class);
                startActivity(nextapp);
            }
        });
    }
}
