package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Applicants_Login extends AppCompatActivity {
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants__login);
        setLogin();
    }

    public void setLogin() {
        login = findViewById(R.id.App_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applicants_Login.this, Applicants_Actions.class);
                startActivity(intent);

            }
        });
    }
}
