package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Applicants_Register extends AppCompatActivity {
    EditText full_name, Email, Location, Password, Re_pass;
    String fulname, email, location, pass, re_pass;
    private Button appreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants__register);
        appdetailsbtn();

        full_name = findViewById(R.id.ful_name);
        Email = findViewById(R.id.applicant_email);
        Location = findViewById(R.id.applicant_location);
        Password = findViewById(R.id.applicant_pass);
        Re_pass = findViewById(R.id.re_pass);
    }

    public void appdetailsbtn() {
        appreg = findViewById(R.id.nextcompdrawer);
        appreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Applicants_Register.this, Applicants_Details.class);
                startActivity(next);

                fulname = full_name.getText().toString();
                email = Email.getText().toString();
                location = Location.getText().toString();
                pass = Password.getText().toString();
                re_pass = Re_pass.getText().toString();

                String method = "register";
                Applicant_BackgroungTask background = new Applicant_BackgroungTask(this);
                background.execute(method, fulname, email, location, pass, re_pass);
                finish();
            }
        });
    }
}