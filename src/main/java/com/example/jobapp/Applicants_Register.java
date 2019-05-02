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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Applicants_Register extends AppCompatActivity implements  View.OnClickListener{
    private TextView Signin;
    private Button Appsignupbtn;
    private ProgressBar Progress;
    private EditText full_name;
    private EditText Idno;
    private EditText Email;
    private EditText Location;
    private EditText Password;
    private EditText Re_pass;
     private String fulname;
     private String idno;
     private String email;
     private String location;
     private String pass;
     private String re_pass;

       private FirebaseAuth mAuth;
        private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants__register);
        Progress = findViewById(R.id.progressbar);
        Signin = findViewById(R.id.apptext_sign_in);
        full_name = findViewById(R.id.ful_name);
        Idno = findViewById(R.id.applicant_id);
        Email = findViewById(R.id.applicant_email);
        Location = findViewById(R.id.applicant_location);
        Password = findViewById(R.id.applicant_pass);
        Re_pass = findViewById(R.id.re_pass);
        Appsignupbtn = findViewById(R.id.App_signin_btn);

        Appsignupbtn.setOnClickListener(this);
        Signin.setOnClickListener(this);

              //getting firebase auth instance
                mAuth = FirebaseAuth.getInstance();

              databaseReference = FirebaseDatabase.getInstance().getReference("Applicants");

    }
    private void  registerUser(){
        fulname = full_name.getText().toString().trim();
        idno = Idno.getText().toString().trim();
        email = Email.getText().toString().trim();
        location = Location.getText().toString().trim();
        pass = Password.getText().toString().trim();
        re_pass= Re_pass.getText().toString().trim();

// authentication of the user

        if(fulname.isEmpty()){
            full_name.setError("Name is require");
            full_name.requestFocus();
            return;
        }
         else if(idno.isEmpty()){
             Idno.setError("Id number Required");
             Idno.requestFocus();
             return;
        }
         else if(email.isEmpty()){
             Email.setError("Email required");
             Email.requestFocus();
             return;
        }
         else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
             Email.setError("Enter correct email adress");
             Email.requestFocus();
             return;
        }
         else if(location.isEmpty()){
             Location.setError("Your location require");
             Location.requestFocus();

             return;

        } else if (pass.isEmpty()) {
             Password.setError("Password required");
             Password.requestFocus();

             return;

        }
        else if(pass.length()<6 ){
            Password.setError("very weak password (6 and above)");
            Password.requestFocus();
            return;
        }

             else if (re_pass.isEmpty()){
                 Re_pass.setError("Password required");
                 Re_pass.requestFocus();
                 return;
        }

        else if(!(re_pass.equals(pass)))
        {
            Re_pass.setError("Password do not match");
            Re_pass.requestFocus();
            return;
        }
        //setting the progress bar
            Progress.setVisibility(View.VISIBLE);
        //authenticating user
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //making progress bar invisible when task is completed
                Progress.setVisibility(View.GONE);
                if (task.isSuccessful()){

                    ApplicantsDao dao = new ApplicantsDao(
                            fulname,
                            idno,
                            email,
                            location
                    );
                    //creating database
                        databaseReference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(dao).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent= new Intent(Applicants_Register.this,Applicants_details.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), "Registration is successful", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    //cheking if the user alreedy exxists in the database
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException){

                                        Intent intent= new Intent(Applicants_Register.this,Applicants_Register.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "User exists and already registered", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                        }
                    });



                }else{

                        Toast.makeText(getApplicationContext(), "User was not added to the database ", Toast.LENGTH_LONG).show();
                    }

            }
        });
    }
// creating action on button and textview
    @Override
    public void onClick(View view){

        switch(view.getId()){
            case R.id.App_signin_btn:
                registerUser();

                break;

            case  R.id.apptext_sign_in:

                startActivity(new Intent(this,Applicants_Login.class));
                break;

        }

    }

}