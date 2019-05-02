package com.example.jobapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Applicants_profile extends AppCompatActivity {

    List<Applicant_model>  modelList= new ArrayList<>();
    RecyclerView recyclerView;

    //layout manager
    RecyclerView.LayoutManager layoutManager;

    //insttance for firestore
    FirebaseFirestore db;

    ApplicantCustomAdaptor applicantCustomAdaptor;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants_profile);

        //instantiate fire store
        db = FirebaseFirestore.getInstance();

       //setting the recycler view property
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // innitializing the progress bar
        pd = new ProgressDialog(this);

        //showing data in the recycler view
        showData();

    }

    private void showData() {

        ///SETTING TITLE OF progress bar
        pd.setTitle("Loading information");
        // showing the  progress bar
        pd.show();
        db.collection("Applicant Details")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pd.dismiss();

                        //when succesful
                        for(DocumentSnapshot doc: task.getResult()){
                            Applicant_model model = new Applicant_model(doc.getString("Id"),
                                    doc.getString("Institutin"),
                                    doc.getString("studyArea"),
                                    doc.getString("studylevel"),
                                    doc.getString("experience"),
                                    doc.getString("comment"),
                                    doc.getString("gender"));

                            modelList.add(model);
                        }
                        //adapter
                        applicantCustomAdaptor = new ApplicantCustomAdaptor(Applicants_profile.this,modelList);
                        //set adapter to recycle view
                        recyclerView.setAdapter(applicantCustomAdaptor);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                // called when error encountered while retriving data
                        Toast.makeText(Applicants_profile.this ,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
