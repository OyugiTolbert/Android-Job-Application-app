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

public class Company_Register extends AppCompatActivity implements View.OnClickListener{


    private TextView Signin;
    private Button compsignupbtn;
    private ProgressBar Progress;
    private EditText Compname;
    private EditText Email;
    private EditText Category;
    private EditText Location;
    private EditText Password;
    private EditText Re_pass;
    private String compname;
    private String email;
    private String category;
    private String location;
    private String pass;
    private String re_pass;

   private FirebaseAuth mAuth;
   private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__register);

            Signin = findViewById(R.id.comptext_sign_in);
            compsignupbtn = findViewById(R.id.comp_signin_btn);
            Progress = findViewById(R.id.progressbarcomp);

            Compname =findViewById(R.id.comp_name);
            Email = findViewById(R.id.comp_email);
            Category = findViewById(R.id.comp_category);
            Location = findViewById(R.id.comp_location);
            Password = findViewById(R.id.comp_pass);
            Re_pass = findViewById(R.id.compre_pass);

            //creating listener
        compsignupbtn.setOnClickListener(this);
        Signin.setOnClickListener(this);

        //getting firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Organizations");
    }
    private void registerComponany() {

        compname= Compname.getText().toString().trim();
        category = Category.getText().toString().trim();
        email = Email.getText().toString().trim();
        location = Location.getText().toString().trim();
        pass = Password.getText().toString().trim();
        re_pass= Re_pass.getText().toString().trim();

// authentication of the user

        if(compname.isEmpty()){
            Compname.setError("Name is require");
            Compname.requestFocus();
            return;
        }
        else if(category.isEmpty()){
            Category.setError("Id number Required");
            Category.requestFocus();
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

                    CompanyDao dao = new CompanyDao(
                            compname,
                            category,
                            email,
                            location
                    );
                    //creating database
                    databaseReference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(dao).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent= new Intent(Company_Register.this,Company_actions.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "Registration is successful", Toast.LENGTH_LONG).show();
                            }
                            else{
                                //cheking if the user alreedy exxists in the database
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){

                                    Intent intent= new Intent(Company_Register.this,Company_Register.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Company exists and already registered", Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.comp_signin_btn:
                registerComponany();

                break;

            case  R.id.comptext_sign_in:

                startActivity(new Intent(this,Company_login.class));
                break;

        }
    }


}
