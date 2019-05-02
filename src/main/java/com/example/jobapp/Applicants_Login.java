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

public class Applicants_Login extends AppCompatActivity implements View.OnClickListener {
    private Button Apploginbtn;
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
        setContentView(R.layout.activity_applicants__login);

        Apploginbtn = findViewById(R.id.App_login_btn);
        singup = findViewById(R.id.apptext_log_in);
        Emaillog = findViewById(R.id.applicantlog_email);
        Passlog = findViewById(R.id.applicantlog_pass);
        progress = findViewById(R.id.progressbar1);


        Apploginbtn.setOnClickListener(this);
        singup.setOnClickListener(this);

//instantiating firebase
        mAuth=FirebaseAuth.getInstance();

    }
    private void loginUser() {
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
                  Intent intent= new Intent(Applicants_Login.this,Applicants_Actions.class);
                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
  public void onClick(View view){
        switch (view.getId()){
            case R.id.App_login_btn:
                loginUser();

                break;
            case R.id.apptext_log_in:
                startActivity(new Intent(this,Applicants_Register.class));
                break;
        }
}

}
