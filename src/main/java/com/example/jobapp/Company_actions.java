package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Company_actions extends AppCompatActivity {
    private Button jobview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_actions);

        jobview = findViewById(R.id.update_profilejob);

        viewProfile();
        viewJob();
        viewApplicant();
    }

    private void viewJob() {
    }

    private void viewProfile() {
        jobview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Company_actions.this,Job_Profile.class));
            }
        });
    }
    private void viewApplicant(){

    }
}
