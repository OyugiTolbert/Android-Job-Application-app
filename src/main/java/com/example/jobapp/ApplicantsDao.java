package com.example.jobapp;

public class ApplicantsDao {

    String name,idnumber,email,location;

    public ApplicantsDao() {

    }

    public ApplicantsDao(String name, String idnumber, String email, String location) {
        this.name = name;
        this.idnumber = idnumber;
        this.email = email;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }
}
