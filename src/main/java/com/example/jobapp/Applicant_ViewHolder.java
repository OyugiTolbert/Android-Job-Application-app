package com.example.jobapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Applicant_ViewHolder extends RecyclerView.ViewHolder {

 TextView mInstitution,mstudyArea,mstudyLevel,mexperince,mcomment,mgender;
 View mview;

    public Applicant_ViewHolder(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

        // clicking the item

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistetener.onItemClick(v , getAdapterPosition());
            }
        });

        //item on long click

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mlistetener.onItemLongClick(v , getAdapterPosition());
                return true;
            }
        });

        // inialize view with model.xml

        mInstitution = itemView.findViewById(R.id.instname);
        mstudyArea = itemView.findViewById(R.id.study);
        mstudyLevel = itemView.findViewById(R.id.studylvl);
        mexperince = itemView.findViewById(R.id.experiencelevl);
        mcomment = itemView.findViewById(R.id.commenting);
        mgender = itemView.findViewById(R.id.sex);

    }
     private Applicant_ViewHolder.ClickListener  mlistetener;


    //interface for click listener
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(Applicant_ViewHolder.ClickListener  clickListener){
        mlistetener = clickListener;
    }
}
