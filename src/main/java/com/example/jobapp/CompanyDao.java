package com.example.jobapp;

public class CompanyDao {
    String CompanyName,Category,Email,Location;

    public CompanyDao(){

    }

    public CompanyDao(String companyName, String category, String email, String location) {
        CompanyName = companyName;
        Category = category;
        Email = email;
        Location = location;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getCategory() {
        return Category;
    }

    public String getEmail() {
        return Email;
    }

    public String getLocation() {
        return Location;
    }
}
