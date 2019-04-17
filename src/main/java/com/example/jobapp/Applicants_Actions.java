package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Applicants_Actions extends AppCompatActivity {
    private Button profile, update, jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants__actions);
        viewProfile();
        updateProfile();
        viewJobs();
    }

    public void viewProfile() {
        profile = findViewById(R.id.view_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applicants_Actions.this, Applicants_profile.class);
                startActivity(intent);

            }
        });
    }

    public void updateProfile() {
        update = findViewById(R.id.update_profile);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applicants_Actions.this, Applicant_Updateprofile.class);
                startActivity(intent);
            }
        });
    }

    public void viewJobs() {
        jobs = findViewById(R.id.view_jobs);
        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applicants_Actions.this, Applicants_viewjob.class);
                startActivity(intent);
            }
        });
    }
}
