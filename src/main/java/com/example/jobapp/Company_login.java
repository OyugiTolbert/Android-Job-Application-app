package com.example.jobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Company_login extends AppCompatActivity implements View.OnClickListener {

    private Button Comploginbtn;
    private ProgressBar progress;
    private TextView singup;
    private EditText Emaillog;
    private EditText Passlog;
    private String emaillog;
    private String passlog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        Comploginbtn = findViewById(R.id.comp_login_btn);
        singup = findViewById(R.id.comptext_log_in);
        Emaillog = findViewById(R.id.complog_email);
        Passlog = findViewById(R.id.complog_pass);
        progress = findViewById(R.id.progressbar2);


        Comploginbtn.setOnClickListener(this);
        singup.setOnClickListener(this);

//instantiating firebase
        mAuth = FirebaseAuth.getInstance();
    }

    private void loginCompany() {
        emaillog = Emaillog.getText().toString().trim();
        passlog = Passlog.getText().toString().trim();

        if(emaillog.isEmpty()){
            Emaillog.setError("Email required");
            Emaillog.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emaillog).matches()){
            Emaillog.setError("Enter correct email adress");
            Emaillog.requestFocus();
            return;
        } else if (passlog.isEmpty()) {
            Passlog.setError("Password required");
            Passlog.requestFocus();
            return;

        }
        //setting the progress bar
        progress.setVisibility(View.VISIBLE);
        //authenticating user
        mAuth.createUserWithEmailAndPassword(emaillog,passlog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progress.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Intent intent= new Intent(Company_login.this,Company_actions.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comp_login_btn:
                loginCompany();

                break;
            case R.id.comptext_log_in:
                startActivity(new Intent(this, Company_Register.class));
                break;
        }
    }

}
