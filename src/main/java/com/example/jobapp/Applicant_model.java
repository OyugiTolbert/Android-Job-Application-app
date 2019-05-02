package com.example.jobapp;

public class Applicant_model {
        String Id,Institutin,studyArea,studylevel,experience,comment,gender;

        public Applicant_model(){

        }

    public Applicant_model(String id,String institutin, String studyArea, String studylevel, String experience, String comment, String gender) {
            Id = id;
            Institutin = institutin;
        this.studyArea = studyArea;
        this.studylevel = studylevel;
        this.experience = experience;
        this.comment = comment;
        this.gender = gender;
    }
    public String getId(){
            return Id;
    }
    public void setId(String id){
            Id= id;
    }

    public String getInstitutin() {
        return Institutin;
    }

    public void setInstitutin(String institutin) {
        Institutin = institutin;
    }

    public String getStudyArea() {
        return studyArea;
    }

    public void setStudyArea(String studyArea) {
        this.studyArea = studyArea;
    }

    public String getStudylevel() {
        return studylevel;
    }

    public void setStudylevel(String studylevel) {
        this.studylevel = studylevel;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
