package com.example.jobapp;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.ColorSpace;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class ApplicantCustomAdaptor extends RecyclerView.Adapter<Applicant_ViewHolder> {

    Applicants_profile listActivity;
    List<Applicant_model>  modelList;
    Context context;

    public ApplicantCustomAdaptor(Applicants_profile listActivity, List<Applicant_model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Applicant_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout
        View itemView = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.applicant_model, viewGroup,false);


       Applicant_ViewHolder applicant_viewHolder =new Applicant_ViewHolder(itemView);
//handling item click
        applicant_viewHolder.setOnClickListener(new Applicant_ViewHolder.ClickListener(){

            @Override
            public void onItemClick(View view, int position) {
// single click event

                //showing data on toast when clicked
                String instname = modelList.get(position).getInstitutin();
                String area = modelList.get(position).getStudyArea();
                String level = modelList.get(position).getStudylevel();
                String experien = modelList.get(position).getExperience();
                String com = modelList.get(position).getComment();
                String gen = modelList.get(position).getGender();

                Toast.makeText(listActivity,instname + "\n" + area + "\n" +level+"\n"+"\n"+experien+"\n"+com+ "\n"+ gen, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
// long  click event
            }
        });

        return applicant_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Applicant_ViewHolder applicant_viewHolder, int i) {
//setting data
        applicant_viewHolder.mInstitution.setText(modelList.get(i).getInstitutin());
        applicant_viewHolder.mstudyArea.setText(modelList.get(i).getStudyArea());
        applicant_viewHolder.mstudyLevel.setText(modelList.get(i).getStudylevel());
        applicant_viewHolder.mexperince.setText(modelList.get(i).getExperience());
        applicant_viewHolder.mcomment.setText(modelList.get(i).getComment());
        applicant_viewHolder.mgender.setText(modelList.get(i).getGender());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
