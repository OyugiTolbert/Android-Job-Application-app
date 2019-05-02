package com.example.jobapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Applicants_details extends AppCompatActivity {
    private RadioGroup grouping;
    private RadioButton radioButton1;
    private RadioButton getRadioButton2;
    private EditText Institution;
    private EditText StudyArea;
    private Spinner Studylevel;
    private Spinner Experience;
    private EditText Comment;
    private Button Upload;
//progress bar
    ProgressDialog  pd;

    // creating firestore instance
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants_details);

        radioButton1 =findViewById(R.id.male);
        getRadioButton2 = findViewById(R.id.female);
        grouping = findViewById(R.id.radiogroup);
        Institution = findViewById(R.id.institution);
         StudyArea = findViewById(R.id.areaof_study);
         Studylevel = findViewById(R.id.level_ofStudy);
         Experience = findViewById(R.id.experience);
         Comment = findViewById(R.id.reasoning);
         Upload = findViewById(R.id.App_uploaddetails);

         pd= new ProgressDialog(this);


         //creating database
        db = FirebaseFirestore.getInstance();


            Upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String  institution = Institution.getText().toString().trim();
                    String studyArea = StudyArea.getText().toString().trim();
                    String studylevel = Studylevel.getSelectedItem().toString();
                    String experience = Experience.getSelectedItem().toString();
                    String comment = Comment.getText().toString();
                    int selectedId = grouping.getCheckedRadioButtonId();

                    grouping = (RadioGroup)findViewById(selectedId);


                    if(!(TextUtils.isEmpty(institution) || TextUtils.isEmpty(studyArea)|| TextUtils.isEmpty(comment))){
                        setUpload(institution,studyArea,studylevel,experience,comment,selectedId);


                    }else{
                        Toast.makeText(Applicants_details.this,getString(R.string.messo),Toast.LENGTH_LONG).show();
                    }

                }
            });


    }

    private void setUpload(String institution, String studyArea, String studylevel, String experience, String comment,int selectedId) {
     pd.setTitle("Adding data to database");
     pd.show();

     // Adding random  id to the data being stored
        String id = UUID.randomUUID().toString();

        //adding components
        Map<String , Object> doc =new HashMap<>();
        doc.put("id",id);
        doc.put("Instution Name:",institution);
        doc.put("Area of Study:",studyArea);
        doc.put("Level of study:",studylevel);
        doc.put("Years of experience:",experience);
        doc.put("Reason for job:",comment);
        doc.put("Your age:",selectedId);


            db.collection("Applicant Details").document(id).set(doc)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //when data upload succesfully
                            Intent intent = new Intent(Applicants_details.this,Applicants_Actions.class);
                            startActivity(intent);
                            pd.dismiss();
                            Toast.makeText(Applicants_details.this,"you uploaded your details successfully",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Applicants_details.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });


    }


}
