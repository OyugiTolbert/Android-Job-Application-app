package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Applicants_Details extends AppCompatActivity {
    private Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants__details);
        setUpload();
    }

    public void setUpload() {
        upload = findViewById(R.id.App_uploaddetails);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applicants_Details.this, Applicants_Actions.class);
                startActivity(intent);

            }
        });
    }
}
